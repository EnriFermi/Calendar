package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.config.service.db.connection.ConnectionService;
import ru.study.calendar.config.service.db.connection.ServerConfiguration;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import java.sql.*;

public class JdbcConfigParser implements ConfigParser {
    //TODO на вход требуется ИД календаря + сделать проверки от дурака
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        ServerConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPath);
        Integer anchorWeekDayKey = null;
        Integer calendarIndex = 0;
        try (Connection connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                serverConfiguration.getUserName(), serverConfiguration.getPassword())) {
            PreparedStatement calendarListStatement = connection.prepareStatement("select * from "
                    + JdbcFieldNames.CALENDAR_LIST.getFieldName());
            try(ResultSet calendarSet = calendarListStatement
                    .executeQuery()) {
                calendarSet.next();
                calendarIndex = calendarSet.getInt(JdbcFieldNames.CALENDAR_ID.getFieldName());
                calendarTemplate.setBeginningYear(calendarSet.getInt(JdbcFieldNames.BEGINNING_YEAR.getFieldName()));
                calendarTemplate.setEndYear(calendarSet.getInt(JdbcFieldNames.END_YEAR.getFieldName()));
                anchorWeekDayKey = calendarSet.getInt(JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName());
            }
            PreparedStatement anchorDayStatement = connection.prepareStatement("select * from "
                    + JdbcFieldNames.DAY_LIST.getFieldName() + " where "
                    + JdbcFieldNames.DAY_ID.getFieldName() + " = ?");
            anchorDayStatement.setInt(1, anchorWeekDayKey);
            try(ResultSet anchorDaySet = anchorDayStatement.executeQuery()) {
                anchorDaySet.next();
                calendarTemplate.setAnchorWeekDay(JdbcDayConfigParser.parse(anchorDaySet));
            }
            PreparedStatement yearListStatement = connection.prepareStatement("select * from "
                    + JdbcFieldNames.YEAR_LIST.getFieldName()
                    + " where " + JdbcFieldNames.CALENDAR_ID.getFieldName()
                    + " = ?");
            yearListStatement.setInt(1, calendarIndex);
            try(ResultSet yearListSet = yearListStatement.executeQuery()) {
                Integer index;
                while (yearListSet.next()) {
                    index = yearListSet.getInt(JdbcFieldNames.YEAR_ID.getFieldName());
                    calendarTemplate.addYear(JdbcYearConfigParser.parse(connection, index));
                }
            }
            calendarTemplate.setWeek(JdbcWeekConfigParser.parse(connection, calendarIndex));
        } catch (SQLException e) {
            throw new JdbcParsingException(e);
        }
        return calendarTemplate;
    }
}
