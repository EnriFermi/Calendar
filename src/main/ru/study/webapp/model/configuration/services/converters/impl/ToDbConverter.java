package ru.study.webapp.model.configuration.services.converters.impl;

import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.domain.DayTemplate;
import ru.study.webapp.model.configuration.domain.MonthTemplate;
import ru.study.webapp.model.configuration.domain.YearTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums.JdbcFieldNames;
import ru.study.webapp.model.configuration.services.connectors.db.ConnectionService;
import ru.study.webapp.model.configuration.services.connectors.db.domain.ServerConnectionConfiguration;
import ru.study.webapp.model.configuration.services.converters.ConfigConverter;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.JdbcParsingException;

import java.sql.*;

public class ToDbConverter implements ConfigConverter {
    public void convertFromTemplate(CalendarTemplate calendarTemplate, String configPathDb) throws ConfigurationException {
        ServerConnectionConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPathDb);
        System.out.println(serverConfiguration.getConnectionURL());
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                    serverConfiguration.getUserName(), serverConfiguration.getPassword());
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(2);
            PreparedStatement calendarStatement = connection
                    .prepareStatement("insert into " + JdbcFieldNames.CALENDAR_TABLE.getFieldName()
                                    + "("+ JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName()+ ","
                                    + JdbcFieldNames.BEGINNING_YEAR.getFieldName() + ","
                                    + JdbcFieldNames.END_YEAR.getFieldName() + ") value (null , ? , ? );",
                            new String[] { JdbcFieldNames.CALENDAR_ID.getFieldName() });
            calendarStatement.setInt(1, calendarTemplate.getBeginningYear());
            calendarStatement.setInt(2, calendarTemplate.getEndYear());

            calendarStatement.executeUpdate();
            Integer calendarIndex = getIndex(calendarStatement);

            PreparedStatement yearStatement = connection.prepareStatement(
                    "insert into " + JdbcFieldNames.YEAR_TABLE.getFieldName()
                            + "(" + JdbcFieldNames.CALENDAR_ID.getFieldName()
                            + ") value ( ? );",
                    new String[] { JdbcFieldNames.YEAR_ID.getFieldName() });
            yearStatement.setInt(1, calendarIndex);
            for (YearTemplate yearTemplate: calendarTemplate.getYearList()) {
                setYearConfig(connection, yearStatement, yearTemplate);
            }

            Integer anchorDayIndex;
            PreparedStatement dayStatement = connection.prepareStatement(
                    "insert into " + JdbcFieldNames.DAY_TABLE.getFieldName()
                            + "(" + JdbcFieldNames.CALENDAR_ID.getFieldName() + ","
                            + JdbcFieldNames.DAY_NAME.getFieldName() + ","
                            + JdbcFieldNames.WEEKDAY_WORKOUT.getFieldName()
                            + ") value (?, ?, ?);",
                    new String[] { JdbcFieldNames.DAY_ID.getFieldName() });

            dayStatement.setInt(1, calendarIndex);
            for (DayTemplate dayTemplate: calendarTemplate.getWeek().getWeekDayNameList()) {
                anchorDayIndex = setDayConfig (dayStatement, dayTemplate);
                if(dayTemplate.equals(calendarTemplate.getAnchorWeekDay())) {
                    PreparedStatement anchorDayStatement = connection.prepareStatement(
                            "update " + JdbcFieldNames.CALENDAR_TABLE.getFieldName() + " set "
                                    + JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName()
                                    + " = ? where "
                                    + JdbcFieldNames.CALENDAR_ID.getFieldName()
                                    + " = ? ;");
                    anchorDayStatement.setInt(1, anchorDayIndex);
                    anchorDayStatement.setInt(2, calendarIndex);
                    anchorDayStatement.executeUpdate();
                }
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.setNextException(ex);
            }
            throw new JdbcParsingException(e);
        }
    }
    public void convert(ConfigurationParser configParser, String configPathFrom, String configPathDb) throws ConfigurationException {
        CalendarTemplate calendarTemplate = configParser.parse(configPathFrom);
        convertFromTemplate(calendarTemplate, configPathDb);
    }

    private static Integer setDayConfig(PreparedStatement preparedStatement, DayTemplate dayTemplate) throws SQLException {
        preparedStatement.setString(2, dayTemplate.getDayName());
        preparedStatement.setBoolean(3, dayTemplate.getWeekDayWorkOut());
        preparedStatement.executeUpdate();
        return getIndex(preparedStatement);
    }

    private static Integer getIndex(PreparedStatement preparedStatement) throws SQLException {
        Integer index;
        try(ResultSet ResultSet = preparedStatement.getGeneratedKeys()){
            ResultSet.next();
            index = ResultSet.getInt(1);
        }
        return index;
    }

    private static void setYearConfig(Connection connection, PreparedStatement yearStatement,  YearTemplate yearTemplate) throws SQLException {
        yearStatement.executeUpdate();
        Integer yearIndex = getIndex(yearStatement);
        PreparedStatement monthStatement = connection.prepareStatement(
                "insert into " + JdbcFieldNames.MONTH_TABLE.getFieldName()
                + "(" + JdbcFieldNames.DAY_COUNT.getFieldName() + ","
                + JdbcFieldNames.NAME_OF_MONTH.getFieldName() + ","
                + JdbcFieldNames.YEAR_ID.getFieldName()
                + " ) value ( ?, ?, ?);",
                new String[] { JdbcFieldNames.MONTH_ID.getFieldName() });
        monthStatement.setInt(3, yearIndex);
        for (MonthTemplate monthTemplate: yearTemplate.getMonthList()) {
            setMonthConfig(connection, monthStatement, monthTemplate);
        }
    }

    private static void setMonthConfig(Connection connection, PreparedStatement monthStatement, MonthTemplate monthTemplate) throws SQLException {
        monthStatement.setInt(1, monthTemplate.getDayCount());
        monthStatement.setString(2, monthTemplate.getName());
        monthStatement.executeUpdate();
        Integer monthIndex = 0;
        if(!monthTemplate.getDayWorkList().isEmpty()) {
            monthIndex = getIndex(monthStatement);
        }
        PreparedStatement workListStatement = connection.prepareStatement(
                "insert into " + JdbcFieldNames.DAY_WORK_TABLE.getFieldName()
                + "(" + JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName() + ","
                + JdbcFieldNames.MONTH_ID.getFieldName() + ","
                + ") value ( ?, ? );");
        PreparedStatement workOutListStatement = connection.prepareStatement(
                "insert into " + JdbcFieldNames.DAY_WORKOUT_TABLE.getFieldName()
                        + "(" + JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName() + ","
                        + JdbcFieldNames.MONTH_ID.getFieldName() + ","
                        + ") value ( ?, ? );");
        workListStatement.setInt(2, monthIndex);
        workOutListStatement.setInt(2, monthIndex);
        for (Integer dateWork: monthTemplate.getDayWorkList()) {
            workListStatement.setInt(1,dateWork);
            workListStatement.executeUpdate();
        }
        workOutListStatement.setInt(2, monthIndex);
        for (Integer dateWorkOut: monthTemplate.getDayWorkOutList()) {
            workListStatement.setInt(1,dateWorkOut);
            workListStatement.executeUpdate();
        }
    }

}
