package ru.study.webapp.model.configuration.parsers.impl.db.jdbc;

import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums.JdbcFieldNames;
import ru.study.webapp.model.configuration.services.connectors.db.ConnectionService;
import ru.study.webapp.model.configuration.services.connectors.db.domain.ServerConnectionConfiguration;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.JdbcParsingException;

import java.sql.*;

public class JdbcConfigurationParser implements ConfigurationParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        ServerConnectionConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPath);
        Integer anchorWeekDayKey = null;
        Integer calendarIndex = 0;
        try {
            Class.forName(serverConfiguration.getDriverName());
        } catch (ClassNotFoundException e) {
            new JdbcParsingException(e);
        }
        try (Connection connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                serverConfiguration.getUserName(), serverConfiguration.getPassword())) {
            //TODO сложно читается запрос, выглядит, будто select * from columnName DONE
            PreparedStatement calendarListStatement = connection.prepareStatement("select * from "
                    + JdbcFieldNames.CALENDAR_TABLE.getFieldName());
            try (ResultSet calendarSet = calendarListStatement.executeQuery()) {
                Integer calendarID = 1;
                Boolean calendarFound = false;
                //TODO в параметр запроса
                while (calendarSet.next()) {
                    if (calendarID.equals(serverConfiguration.getCalendarId())) {
                        calendarIndex = calendarSet.getInt(JdbcFieldNames.CALENDAR_ID.getFieldName());
                        calendarTemplate.setBeginningYear(calendarSet.getInt(JdbcFieldNames.BEGINNING_YEAR.getFieldName()));
                        calendarTemplate.setEndYear(calendarSet.getInt(JdbcFieldNames.END_YEAR.getFieldName()));
                        anchorWeekDayKey = calendarSet.getInt(JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName());
                        calendarFound = true;
                        break;
                    }
                    calendarID++;
                }
                if (!calendarFound) {
                    throw new JdbcParsingException("There is no calendar with such id: "
                            + serverConfiguration.getCalendarId());
                }

            }
            PreparedStatement anchorDayStatement = connection.prepareStatement("select * from "
                    + JdbcFieldNames.DAY_TABLE.getFieldName() + " where "
                    + JdbcFieldNames.DAY_ID.getFieldName() + " = ?");
            anchorDayStatement.setInt(1, anchorWeekDayKey);
            try (ResultSet anchorDaySet = anchorDayStatement.executeQuery()) {
                anchorDaySet.next();
                calendarTemplate.setAnchorWeekDay(JdbcDayConfigurationParser.parse(anchorDaySet));
            }
            PreparedStatement yearListStatement = connection.prepareStatement("select * from "
                    + JdbcFieldNames.YEAR_TABLE.getFieldName()
                    + " where " + JdbcFieldNames.CALENDAR_ID.getFieldName()
                    + " = ?");
            yearListStatement.setInt(1, calendarIndex);
            try (ResultSet yearListSet = yearListStatement.executeQuery()) {
                Integer index;
                while (yearListSet.next()) {
                    index = yearListSet.getInt(JdbcFieldNames.YEAR_ID.getFieldName());
                    calendarTemplate.addYear(JdbcYearConfigurationParser.parse(connection, index));
                }
            }
            calendarTemplate.setWeek(JdbcWeekConfigurationParser.parse(connection, calendarIndex));
        } catch (SQLException e) {
            throw new JdbcParsingException(e);
        }
        return calendarTemplate;
    }
}
