package ru.study.calendar.config.parsers.impl.xml.sax;

import lombok.Getter;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.study.calendar.config.domain.impl.*;
import ru.study.calendar.config.domain.inter.reading.ICalendarTemplateForReading;
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
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException {
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

        private CalendarTemplate calendarTemplate = new CalendarTemplate();

        private YearTemplate yearConstructor = new YearTemplate();
        private MonthTemplate monthConstructor = new MonthTemplate();
        private DayTemplate dayConstructor = new DayTemplate();
        private WeekTemplate weekConstructor = new WeekTemplate();
        private String information;
        private String nodeName;

        public ICalendarTemplateForReading getCalendarTemplate() {
            return calendarTemplate;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            nodeName = qName;
            if (nodeName.equals(XMLSaxFieldNames.ANCHOR_WEEKDAY.getFieldName())) {
                //TODO попытаться в список положить объект, а потом использовать new
                DayTemplate day = new DayTemplate();
                day.clone(dayConstructor);
                calendarTemplate.setAnchorWeekDay(day);
                //TODO вместо reset использовать new
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
                YearTemplate year = new YearTemplate();
                year.clone( yearConstructor);
                calendarTemplate.addYear(year);
                yearConstructor.resetYearTemplate();
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.MONTH_CONFIG.getFieldName())) {
                try {
                    MonthTemplate month = new MonthTemplate();
                    month.clone(monthConstructor);
                    yearConstructor.addMonth(month);
                } catch (ConfigurationException e) {
                    throw new SAXException(e);
                }
                monthConstructor.resetMonth();
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
                WeekTemplate week = new WeekTemplate();
                week.clone(weekConstructor);
                calendarTemplate.setWeek(week);
                weekConstructor.resetWeekConfig();
                return;
            }
            if (nodeName.equals(XMLSaxFieldNames.WEEKDAY_NAME.getFieldName())) {
                try {
                    DayTemplate day = new DayTemplate();
                    day.clone(dayConstructor);
                    weekConstructor.addWeekDay(day);
                } catch (ConfigurationException e) {
                    throw new SAXException(e);
                }
                dayConstructor.resetDay();
                return;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //TODO Перевести на new String, что есть плохого в String
            information = "";
            for (Integer i = start; i < start + length; i++) {
                information = information + String.valueOf(ch[i]);
            }
            if (information.equals("")) {
                return;
            }
        }
    }

}
