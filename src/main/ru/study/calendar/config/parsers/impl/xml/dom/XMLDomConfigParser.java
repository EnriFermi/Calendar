package ru.study.calendar.config.parsers.impl.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.study.calendar.config.body.impl.CalendarTemplate;
import ru.study.calendar.config.body.inter.parsing.ICalendarTemplateForParsing;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.IOConfigurationException;
import ru.study.calendar.exceptions.XmlDomParsingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLDomConfigParser implements ConfigParser {
    public XMLDomConfigParser(){

    }
    @Override
    public ICalendarTemplateForParsing parse(String configPath) throws ConfigurationException {
        ICalendarTemplateForParsing calendarTemplate = new CalendarTemplate();
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

                calendarTemplate.setAnchorWeekDay(XMLDomDayConfigParser.parse(elementCalendar));
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
                        calendarTemplate.addYear(XMLDomYearConfigParser.parse(yearConfig));
                    }
                }
            }
            // Получаем неделю
            if (elementCalendar.getNodeName().equals(XMLDomFieldNames.WEEK.getFieldName())) {
                calendarTemplate.setWeek(XMLDomWeekConfigParser.parse(elementCalendar));
            }
        }
        return calendarTemplate;
    }
}
