package ru.study.webapp.model.configuration.parsers.impl.db.jdbc;

import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums.JdbcFieldNames;
import ru.study.webapp.model.configuration.services.connectors.db.ConnectionService;
import ru.study.webapp.model.configuration.services.connectors.db.domain.ServerConnectionConfiguration;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.JaxbParsingException;
import ru.study.webapp.exceptions.model.JdbcParsingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcConfigurationParser implements ConfigurationParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        ServerConnectionConfiguration serverConfiguration = ConnectionService.parseServerConfig(configPath);
        CalendarTemplate calendarTemplate = null;
        try {
            Class.forName(serverConfiguration.getDriverName());
        } catch (ClassNotFoundException e) {
            new JdbcParsingException(e);
        }
        try (Connection connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                serverConfiguration.getUserName(), serverConfiguration.getPassword())) {
            calendarTemplate = parse(serverConfiguration.getCalendarId(), connection);
        } catch (SQLException e) {
            throw new JdbcParsingException(e);
        }
        return calendarTemplate;
    }
    public List<CalendarTemplate> parse(Connection connection) throws SQLException, JdbcParsingException, JaxbParsingException {
        List<CalendarTemplate> calendarTemplateList  = new ArrayList<>();
        PreparedStatement calendarListStatement = connection.prepareStatement("select * from "
                + JdbcFieldNames.CALENDAR_TABLE.getFieldName());
        try (ResultSet calendarSet = calendarListStatement.executeQuery()) {
            //TODO в параметр запроса ?)
            while (calendarSet.next()) {
                calendarTemplateList.add(getCalendarTemplate(calendarSet, connection));
            }
        }
        return  calendarTemplateList;
    }
    public CalendarTemplate parse(Integer calendarId, Connection connection) throws SQLException, JdbcParsingException, JaxbParsingException {
        CalendarTemplate calendarTemplate = null;
        PreparedStatement calendarListStatement = connection.prepareStatement("select * from "
                + JdbcFieldNames.CALENDAR_TABLE.getFieldName() + " where "
                + JdbcFieldNames.CALENDAR_ID.getFieldName() + " = " +calendarId.toString());
        try (ResultSet calendarSet = calendarListStatement.executeQuery()) {
            //TODO в параметр запроса DONE
            calendarTemplate = getCalendarTemplate(calendarSet, connection);
        } catch (SQLException e){
            throw new JdbcParsingException(e);
        }
        return calendarTemplate;
    }

    private static CalendarTemplate getCalendarTemplate(ResultSet calendarSet,Connection connection) throws SQLException, JdbcParsingException, JaxbParsingException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        Integer anchorWeekDayKey = null;
        Integer calendarIndex = 0;
        calendarIndex = calendarSet.getInt(JdbcFieldNames.CALENDAR_ID.getFieldName());
        calendarTemplate.setBeginningYear(calendarSet.getInt(JdbcFieldNames.BEGINNING_YEAR.getFieldName()));
        calendarTemplate.setEndYear(calendarSet.getInt(JdbcFieldNames.END_YEAR.getFieldName()));
        anchorWeekDayKey = calendarSet.getInt(JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName());
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
        return calendarTemplate;
    }
}
