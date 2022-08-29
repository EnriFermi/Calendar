package ru.study.calendar.config.service.converter;

import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.body.inter.reading.IDayTemplateForReading;
import ru.study.calendar.config.body.inter.reading.IMonthTemplateForReading;
import ru.study.calendar.config.body.inter.reading.IYearTemplateForReading;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.config.service.db.connection.ConnectionService;
import ru.study.calendar.config.service.db.connection.ServerConfiguration;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import java.sql.*;

public class Template2DbConverter {
    public static void convert(ICalendarTemplateForReading calendarTemplate, String configPathDb)
            throws ConfigurationException {

        ServerConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPathDb);
        try {
            Class.forName(serverConfiguration.getDriverName());
        } catch (ClassNotFoundException e) {
            throw new JdbcParsingException(e);
        }
        try (Connection connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                serverConfiguration.getUserName(), serverConfiguration.getPassword())) {
            Statement calendarStatement = connection.createStatement();

            calendarStatement.execute("insert into " + JdbcFieldNames.CALENDAR_LIST.getFieldName()
            + "(" + JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName()+ ","
                    + JdbcFieldNames.BEGINNING_YEAR.getFieldName() + ","
                    + JdbcFieldNames.END_YEAR.getFieldName() + ") value (null , "
                    + calendarTemplate.getBeginningYear() + ","
                    + calendarTemplate.getEndYear() + ");");

            Integer calendarIndex = getLastIndex(JdbcFieldNames.CALENDAR_LIST.getFieldName(),
                    JdbcFieldNames.CALENDAR_ID.getFieldName(), calendarStatement);
            for (IYearTemplateForReading yearTemplate: calendarTemplate.getYearList()) {
                setYearConfig(calendarStatement, calendarIndex, yearTemplate);
            }
            Integer anchorDayIndex = 0;
            for (IDayTemplateForReading dayTemplate: calendarTemplate.getWeek().getWeekDayNameList()) {
                setDayConfig (calendarStatement, calendarIndex, dayTemplate);
                if(dayTemplate.equals(calendarTemplate.getAnchorWeekDay())) {
                    anchorDayIndex = getLastIndex(JdbcFieldNames.DAY_LIST.getFieldName(),
                            JdbcFieldNames.DAY_ID.getFieldName(), calendarStatement);
                    calendarStatement.execute("update " + JdbcFieldNames.CALENDAR_LIST.getFieldName() + " set "
                            + JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName()
                            + " = "
                            + anchorDayIndex
                            + " where "
                            + JdbcFieldNames.CALENDAR_ID.getFieldName()
                            + " = "
                            + calendarIndex
                            + ";"
                    );
                }
            }

        } catch (SQLException e) {
            throw new JdbcParsingException(e);
        }
    }

    private static void setDayConfig(Statement calendarStatement, Integer calendarIndex, IDayTemplateForReading dayTemplate) throws SQLException {
        calendarStatement.execute("insert into " + JdbcFieldNames.DAY_LIST.getFieldName()
                + "(" + JdbcFieldNames.CALENDAR_ID.getFieldName() + ","
                + JdbcFieldNames.DAY_NAME.getFieldName() + ","
                + JdbcFieldNames.WEEKDAY_WORKOUT.getFieldName()
                + ") value ("
                + calendarIndex + ", '"
                + dayTemplate.getDayName() + "' ,"
                + dayTemplate.isDefaultDayWorkOut()
                + ");");
    }

    private static void setYearConfig(Statement calendarStatement, Integer calendarIndex,  IYearTemplateForReading yearTemplate) throws SQLException {
        Integer yearIndex;
        calendarStatement.execute("insert into " + JdbcFieldNames.YEAR_LIST.getFieldName()
                + "(" + JdbcFieldNames.CALENDAR_ID.getFieldName()
                + ") value ("
                + calendarIndex + ");");

        yearIndex = getLastIndex(JdbcFieldNames.YEAR_LIST.getFieldName(),
                JdbcFieldNames.YEAR_ID.getFieldName(), calendarStatement);
        ;

        for (IMonthTemplateForReading monthTemplate: yearTemplate.getMonthList()) {
            setMonthConfig(calendarStatement, yearIndex, monthTemplate);
        }
    }

    private static void setMonthConfig(Statement calendarStatement, Integer yearIndex, IMonthTemplateForReading monthTemplate) throws SQLException {
        Integer monthIndex = 0;
        calendarStatement.execute("insert into " + JdbcFieldNames.MONTH_LIST.getFieldName()
                + "(" + JdbcFieldNames.DAY_COUNT.getFieldName() + ","
                + JdbcFieldNames.NAME_OF_MONTH.getFieldName() + ","
                + JdbcFieldNames.YEAR_ID.getFieldName()
                + " ) value ( "
                + monthTemplate.getDayCount() + ", '"
                + monthTemplate.getName() + "' ,"
                + yearIndex
                + " );");

        if(!monthTemplate.getDayWorkList().isEmpty()) {
            monthIndex = getLastIndex(JdbcFieldNames.MONTH_LIST.getFieldName(),
                    JdbcFieldNames.MONTH_ID.getFieldName(), calendarStatement);
        }
        for (Integer dateWork: monthTemplate.getDayWorkList()) {
            calendarStatement.execute("insert into " + JdbcFieldNames.DAY_WORK_LIST.getFieldName()
                    + "(" + JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName() + ","
                    + JdbcFieldNames.MONTH_ID.getFieldName() + ","
                    + ") value ("
                    + dateWork + ","
                    + monthIndex
                    + ");");
        }
        for (Integer dateWorkOut: monthTemplate.getDayWorkOutList()) {
            calendarStatement.execute("insert into " + JdbcFieldNames.DAY_WORKOUT_LIST.getFieldName()
                    + "(" + JdbcFieldNames.DATE_OF_WORKOUT_DAY.getFieldName() + ","
                    + JdbcFieldNames.MONTH_ID.getFieldName() + ","
                    + ") value ("
                    + dateWorkOut + ","
                    + monthIndex
                    + ");");
        }
    }


    private static Integer getLastIndex(String list, String id, Statement statement) throws SQLException {
        ResultSet set = statement.executeQuery("select " + id
                + " from " + list);
        Integer index = 0;
        while(set.next()) {
            index = set.getInt(id);
        }
        set.close();
        return index;
    }
}
