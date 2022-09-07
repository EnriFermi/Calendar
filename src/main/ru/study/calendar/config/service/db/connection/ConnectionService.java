package ru.study.calendar.config.service.db.connection;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.study.calendar.config.service.db.connection.enums.ConnectionFieldNames;
import ru.study.calendar.exceptions.JdbcParsingException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public interface ConnectionService {
    public static ServerConnectionConfiguration parseServerConfig(String configPath) throws JdbcParsingException {
        ServerConnectionConfiguration serverConfiguration = new ServerConnectionConfiguration();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(configPath));
            NodeList configOfConnection = document.getFirstChild().getChildNodes();
            for (Integer i = 0; i < configOfConnection.getLength(); i++) {
                Node elementCalendar = configOfConnection.item(i);
                if (elementCalendar.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (elementCalendar.getNodeName().equals(ConnectionFieldNames.USER_NAME.getFieldName())) {
                    serverConfiguration.setUserName(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(ConnectionFieldNames.PASSWORD.getFieldName())) {
                    serverConfiguration.setPassword(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(ConnectionFieldNames.CONNECTION_URL.getFieldName())) {
                    serverConfiguration.setConnectionURL(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(ConnectionFieldNames.DRIVER_NAME.getFieldName())) {
                    serverConfiguration.setDriverName(elementCalendar.getTextContent());
                }
                if (elementCalendar.getNodeName().equals(ConnectionFieldNames.CALENDAR_ID.getFieldName())) {
                    serverConfiguration.setCalendarId(Integer.valueOf(elementCalendar.getTextContent()));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new JdbcParsingException(e);
        }
        return serverConfiguration;
    }
}
