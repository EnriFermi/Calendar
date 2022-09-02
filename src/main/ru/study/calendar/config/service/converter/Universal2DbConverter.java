package ru.study.calendar.config.service.converter;

import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.domain.DayTemplate;
import ru.study.calendar.config.domain.MonthTemplate;
import ru.study.calendar.config.domain.YearTemplate;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.config.service.ConfigConverter;
import ru.study.calendar.config.service.db.connection.ConnectionService;
import ru.study.calendar.config.service.db.connection.ServerConfiguration;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import java.sql.*;

public class Universal2DbConverter implements ConfigConverter {

    public void convert(ConfigParser configParser, String configPathFrom, String configPathDb) throws ConfigurationException {
        CalendarTemplate calendarTemplate = configParser.parse(configPathFrom);
        ServerConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPathDb);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                    serverConfiguration.getUserName(), serverConfiguration.getPassword());
            connection.setAutoCommit(false);

            PreparedStatement calendarStatement = connection
                    .prepareStatement("insert into " + JdbcFieldNames.CALENDAR_LIST.getFieldName()
                    + "("+ JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName()+ ","
                    + JdbcFieldNames.BEGINNING_YEAR.getFieldName() + ","
                    + JdbcFieldNames.END_YEAR.getFieldName() + ") value (null , ? , ? );",
                            new String[] { JdbcFieldNames.CALENDAR_ID.getFieldName() });
            calendarStatement.setInt(1, calendarTemplate.getBeginningYear());
            calendarStatement.setInt(2, calendarTemplate.getEndYear());

            calendarStatement.executeUpdate();
            Integer calendarIndex = getIndex(calendarStatement);

            PreparedStatement yearStatement = connection.prepareStatement(
                    "insert into " + JdbcFieldNames.YEAR_LIST.getFieldName()
                    + "(" + JdbcFieldNames.CALENDAR_ID.getFieldName()
                    + ") value ( ? );",
                    new String[] { JdbcFieldNames.YEAR_ID.getFieldName() });
            yearStatement.setInt(1, calendarIndex);
            for (YearTemplate yearTemplate: calendarTemplate.getYearList()) {
                setYearConfig(connection, yearStatement, yearTemplate);
            }

            Integer anchorDayIndex;
            PreparedStatement dayStatement = connection.prepareStatement(
                    "insert into " + JdbcFieldNames.DAY_LIST.getFieldName()
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
                            "update " + JdbcFieldNames.CALENDAR_LIST.getFieldName() + " set "
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

    private static Integer setDayConfig(PreparedStatement preparedStatement, DayTemplate dayTemplate) throws SQLException {
        preparedStatement.setString(2, dayTemplate.getDayName());
        preparedStatement.setBoolean(3, dayTemplate.isDefaultDayWorkOut());
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
                "insert into " + JdbcFieldNames.MONTH_LIST.getFieldName()
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
                "insert into " + JdbcFieldNames.DAY_WORK_LIST.getFieldName()
                + "(" + JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName() + ","
                + JdbcFieldNames.MONTH_ID.getFieldName() + ","
                + ") value ( ?, ? );");
        PreparedStatement workOutListStatement = connection.prepareStatement(
                "insert into " + JdbcFieldNames.DAY_WORKOUT_LIST.getFieldName()
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
