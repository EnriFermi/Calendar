package ru.study.calendar.config.parsers.impl.jdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.study.calendar.config.body.impl.CalendarTemplate;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class JdbcConfigParser implements ConfigParser {
    @Override
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        ServerConfiguration serverConfiguration = parseServerConfig(configPath);
        try {
            Class.forName(serverConfiguration.getDriverName());
        } catch (ClassNotFoundException e) {
            throw new JdbcParsingException(e);
        }
        try (Connection connection = DriverManager.getConnection(serverConfiguration.getConnectionURL(),
                serverConfiguration.getUserName(), serverConfiguration.getPassword())) {
            Statement calendarStatement = connection.createStatement();
            ResultSet calendarSet = calendarStatement.executeQuery("select * from calendarlist");
            calendarSet.next();
            calendarTemplate.setBeginningYear(calendarSet.getInt(JdbcFieldNames.BEGINNING_YEAR.getFieldName()));
            calendarTemplate.setEndYear(calendarSet.getInt(JdbcFieldNames.END_YEAR.getFieldName()));
            Integer anchorWeekDayKey = calendarSet.getInt(JdbcFieldNames.ANCHOR_WEEKDAY_KEY.getFieldName());
            calendarSet.close();

            ResultSet anchorDaySet = calendarStatement.executeQuery("select * from daylist where dayid = "
                    + anchorWeekDayKey);
            anchorDaySet.next();
            calendarTemplate.setAnchorWeekDay(JdbcDayConfigParser.parse(anchorDaySet));
            anchorDaySet.close();

            ResultSet yearListSet = calendarStatement.executeQuery("select * from yearList");
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
    private ServerConfiguration parseServerConfig(String configPath) throws JdbcParsingException {
        ServerConfiguration serverConfiguration = new ServerConfiguration();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(configPath));
            NodeList configOfConnection = document.getFirstChild().getChildNodes();
            for (Integer i = 0; i < configOfConnection.getLength(); i++) {
                Node elementCalendar = configOfConnection.item(i);
                if (elementCalendar.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (elementCalendar.getNodeName().equals(JdbcFieldNames.USER_NAME.getFieldName())) {
                    serverConfiguration.setUserName(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(JdbcFieldNames.PASSWORD.getFieldName())) {
                    serverConfiguration.setPassword(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(JdbcFieldNames.CONNECTION_URL.getFieldName())) {
                    serverConfiguration.setConnectionURL(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(JdbcFieldNames.DRIVER_NAME.getFieldName())) {
                    serverConfiguration.setDriverName(elementCalendar.getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new JdbcParsingException(e);
        }
        return serverConfiguration;
    }
}
