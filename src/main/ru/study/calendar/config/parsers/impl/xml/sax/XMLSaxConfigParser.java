package ru.study.calendar.config.parsers.impl.xml.sax;

import lombok.Getter;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.domain.DayTemplate;
import ru.study.calendar.config.domain.MonthTemplate;
import ru.study.calendar.config.domain.WeekTemplate;
import ru.study.calendar.config.domain.YearTemplate;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.xml.sax.enums.XMLSaxFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.IOConfigurationException;
import ru.study.calendar.exceptions.XmlSaxParsingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Класс реализующий хранение данных о конфигурации календаря
 */

@Getter
public class XMLSaxConfigParser implements ConfigParser {

    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        SAXParserFactory fac = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = fac.newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new XmlSaxParsingException();
        } catch (SAXException e) {
            throw new XmlSaxParsingException(e);
        }
        CalendarConfigHandler handler = new CalendarConfigHandler();
        try {
            parser.parse(configPath, handler);
        } catch (SAXException e) {
            throw new XmlSaxParsingException(e);
        } catch (IOException e) {
            throw new IOConfigurationException(e);
        }
        return handler.getCalendarTemplate();
    }

    private class CalendarConfigHandler extends DefaultHandler {

        private final CalendarTemplate calendarTemplate = new CalendarTemplate();

        private YearTemplate yearConstructor = new YearTemplate();
        private MonthTemplate monthConstructor = new MonthTemplate();
        private DayTemplate dayConstructor = new DayTemplate();
        private WeekTemplate weekConstructor = new WeekTemplate();
        private String information;
        private String nodeName;

        public CalendarTemplate getCalendarTemplate() {
            return calendarTemplate;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            nodeName = qName;
            if (nodeName.equals(XMLSaxFieldNames.ANCHOR_WEEKDAY.getFieldName())) {
                calendarTemplate.setAnchorWeekDay(dayConstructor);
                dayConstructor = new DayTemplate();
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.DAY_NAME.getFieldName())) {
                dayConstructor.setDayName(information);
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                dayConstructor.setWeekDayWorkOut(Boolean.valueOf(information));
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.BEGINNING_YEAR.getFieldName())) {
                calendarTemplate.setBeginningYear(Integer.valueOf(information));
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.END_YEAR.getFieldName())) {
                calendarTemplate.setEndYear(Integer.valueOf(information));
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.YEAR_CONFIG.getFieldName())) {
                calendarTemplate.addYear(yearConstructor);
                yearConstructor = new YearTemplate();
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.MONTH_CONFIG.getFieldName())) {
                try {
                    yearConstructor.addMonth(monthConstructor);
                    monthConstructor = new MonthTemplate();
                } catch (ConfigurationException e) {
                    throw new SAXException(e);
                }
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.NAME_OF_MONTH.getFieldName())) {
                monthConstructor.setName(information);
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.DAY_COUNT.getFieldName())) {
                monthConstructor.setDayCount(Integer.valueOf(information));
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.DATE_OF_WORK_DAY.getFieldName())) {
                try {
                    monthConstructor.addWorkDay(Integer.valueOf(information));
                } catch (ConfigurationException e) {
                    throw new SAXException(e);
                }
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())) {
                try {
                    monthConstructor.addWorkOutDay(Integer.valueOf(information));
                } catch (ConfigurationException e) {
                    throw new SAXException(e);
                }
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.WEEK.getFieldName())) {
                calendarTemplate.setWeek(weekConstructor);
                weekConstructor = new WeekTemplate();
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.WEEKDAY_NAME.getFieldName())) {
                try {
                    weekConstructor.addWeekDay(dayConstructor);
                    dayConstructor = new DayTemplate();
                } catch (ConfigurationException e) {
                    throw new SAXException(e);
                }
                return;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            information = new String(ch, start, length);
        }
    }

}
