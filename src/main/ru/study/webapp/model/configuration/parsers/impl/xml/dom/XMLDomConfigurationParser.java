package ru.study.webapp.model.configuration.parsers.impl.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.IOConfigurationException;
import ru.study.webapp.model.exceptions.XmlDomParsingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLDomConfigurationParser implements ConfigurationParser {

    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = fac.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XmlDomParsingException(e);
        }
        Document document = null;
        try {
            document = builder.parse(new File(configPath));
        } catch (IOException e) {
            throw new IOConfigurationException(e);
        } catch (SAXException e) {
            throw new XmlDomParsingException(e);
        }
        NodeList configOfCalendar = document.getFirstChild().getChildNodes();
        for (Integer i = 0; i < configOfCalendar.getLength(); i++) {
            Node elementCalendar = configOfCalendar.item(i);
            if (elementCalendar.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // Получаем 1 день 1 месяца привязочного года
            if (elementCalendar.getNodeName().equals(XMLDomFieldNames.ANCHOR_WEEKDAY.getFieldName())) {

                calendarTemplate.setAnchorWeekDay(XMLDomDayConfigurationParser.parse(elementCalendar));
            }
            // Год начала допустимого интервала календаря
            if (elementCalendar.getNodeName().equals(XMLDomFieldNames.BEGINNING_YEAR.getFieldName())) {
                calendarTemplate.setBeginningYear(Integer.valueOf(elementCalendar.getTextContent()));
            }
            // Год конца допустимого интервала календаря
            if (elementCalendar.getNodeName().equals(XMLDomFieldNames.END_YEAR.getFieldName())) {
                calendarTemplate.setEndYear(Integer.valueOf(elementCalendar.getTextContent()));
            }
            //------------------------------------------------
            // Получаем список годов
            if (elementCalendar.getNodeName().equals(XMLDomFieldNames.YEAR_LIST.getFieldName())) {
                NodeList yearConfigList = elementCalendar.getChildNodes();
                for (Integer j = 0; j < yearConfigList.getLength(); j++) {
                    Node yearConfig = yearConfigList.item(j);
                    if (yearConfig.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (yearConfig.getNodeName().equals(XMLDomFieldNames.YEAR_CONFIG.getFieldName())) {
                        calendarTemplate.addYear(XMLDomYearConfigurationParser.parse(yearConfig));
                    }
                }
            }
            // Получаем неделю
            if (elementCalendar.getNodeName().equals(XMLDomFieldNames.WEEK.getFieldName())) {
                calendarTemplate.setWeek(XMLDomWeekConfigurationParser.parse(elementCalendar));
            }
        }
        return calendarTemplate;
    }
}
