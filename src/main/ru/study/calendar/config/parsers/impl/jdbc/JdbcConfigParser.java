package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.domain.impl.CalendarTemplate;
import ru.study.calendar.config.domain.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.config.service.db.connection.ConnectionService;
import ru.study.calendar.config.service.db.connection.ServerConfiguration;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import java.sql.*;

public class JdbcConfigParser implements ConfigParser {
    @Override
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        ServerConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPath);
        try {
            Class.forName(serverConfiguration.getDriverName());
        } catch (ClassNotFoundException e) {
            throw new JdbcParsingException(e);
        }
        try (Connection connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                serverConfiguration.getUserName(), serverConfiguration.getPassword())) {
            Statement calendarStatement = connection.createStatement();
            ResultSet calendarSet = calendarStatement.executeQuery("select * from " + JdbcFieldNames.CALENDAR_LIST.getFieldName());
            //TODO использовать try with, чтобы не вызывать close
            calendarSet.next();
            calendarTemplate.setBeginningYear(calendarSet.getInt(JdbcFieldNames.BEGINNING_YEAR.getFieldName()));
            calendarTemplate.setEndYear(calendarSet.getInt(JdbcFieldNames.END_YEAR.getFieldName()));
            Integer anchorWeekDayKey = calendarSet.getInt(JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName());
            calendarSet.close();

        ResultSet anchorDaySet = calendarStatement.executeQuery("select * from " + JdbcFieldNames.DAY_LIST.getFieldName()
                + " where " + JdbcFieldNames.DAY_ID.getFieldName() + " = " + anchorWeekDayKey);
            anchorDaySet.next();
            calendarTemplate.setAnchorWeekDay(JdbcDayConfigParser.parse(anchorDaySet));
            anchorDaySet.close();
            ResultSet yearListSet = calendarStatement.executeQuery("select * from " + JdbcFieldNames.YEAR_LIST.getFieldName());
            Integer index;
            while (yearListSet.next()) {
                index = yearListSet.getInt(JdbcFieldNames.YEAR_ID.getFieldName());
                calendarTemplate.addYear(JdbcYearConfigParser.parse(connection, index));
            }
            yearListSet.close();


            calendarTemplate.setWeek(JdbcWeekConfigParser.parse(calendarStatement));

        } catch (SQLException e) {
            throw new JdbcParsingException(e);
        }
        return calendarTemplate;
    }
}
