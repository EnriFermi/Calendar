package ru.study.calendar.config.impl.xml.sax;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.study.calendar.config.ICalendarTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.xml.sax.enums.XMLSaxFieldNames;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий хранение данных о конфигурации календаря
 */

@Getter
public class XMLSaxCalendarConfig implements ICalendarTemplate {

    private class CalendarConfigHandler extends DefaultHandler {
        private String nodeName = null;
        private Boolean isCalendarOpened = false;
        private Boolean isAnchorWeekDayOpened = false;
        private Boolean isDayNameOpened = false;
        private Boolean isWeekDayWorkOutOpened = false;
        private Boolean isBeginningYearOpened = false;
        private Boolean isEndYearOpened = false;
        private Boolean isYearListOpened = false;
        private Boolean isYearConfigOpened = false;
        private Boolean isMonthListOpened = false;
        private Boolean isMonthConfigOpened = false;
        private Boolean isNameOfMonthOpened = false;
        private Boolean isDayCountOpened = false;
        private Boolean isDayWorkOutListOpened = false;
        private Boolean isDayWorkListOpened = false;
        private Boolean isDateOfWorkOutDayOpened = false;
        private Boolean isDateOfWorkDayOpened = false;
        private Boolean isWeekOpened = false;
        private Boolean isWeekDayNameListOpened = false;
        private Boolean isWeekDayNameOpened = false;
        private XMLSaxYearConfig yearConstructor = new XMLSaxYearConfig();
        private XMLSaxMonthConfig monthConstructor = new XMLSaxMonthConfig();
        private XMLSaxDayConfig dayConstructor = new XMLSaxDayConfig();
        private XMLSaxWeekConfig weekConstructor = new XMLSaxWeekConfig();



        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nodeName = qName;
            if(nodeName.equals(XMLSaxFieldNames.CALENDAR_CONFIG.getFieldName())) {
                isCalendarOpened = true;
                return;
            }
            if(isCalendarOpened) {
                if(nodeName.equals(XMLSaxFieldNames.ANCHOR_WEEKDAY.getFieldName())) {
                    isAnchorWeekDayOpened = true;
                    return;
                }
                if(isAnchorWeekDayOpened) {
                    if(nodeName.equals(XMLSaxFieldNames.DAY_NAME.getFieldName())) {
                        isDayNameOpened = true;
                        return;
                    }
                    if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                        isWeekDayWorkOutOpened = true;
                        return;
                    }
                }
                if(nodeName.equals(XMLSaxFieldNames.BEGINNING_YEAR.getFieldName())) {
                    isBeginningYearOpened = true;
                    return;
                }
                if(nodeName.equals(XMLSaxFieldNames.END_YEAR.getFieldName())) {
                    isEndYearOpened = true;
                    return;
                }
                if(nodeName.equals(XMLSaxFieldNames.YEAR_LIST.getFieldName())) {
                    isYearListOpened = true;
                    return;
                }
                if(isYearListOpened) {
                    if(nodeName.equals(XMLSaxFieldNames.YEAR_CONFIG.getFieldName())) {
                        isYearConfigOpened = true;
                        return;
                    }
                    if(isYearConfigOpened) {
                        if(nodeName.equals(XMLSaxFieldNames.MONTH_LIST.getFieldName())) {
                            isMonthListOpened = true;
                            return;
                        }
                        if(isMonthListOpened) {
                            if(nodeName.equals(XMLSaxFieldNames.MONTH_CONFIG.getFieldName())) {
                                isMonthConfigOpened = true;
                                return;
                            }
                            if(isMonthConfigOpened) {
                                if(nodeName.equals(XMLSaxFieldNames.NAME_OF_MONTH.getFieldName())) {
                                    isNameOfMonthOpened = true;
                                    return;
                                }
                                if(nodeName.equals(XMLSaxFieldNames.DAY_COUNT.getFieldName())) {
                                    isDayCountOpened = true;
                                    return;
                                }
                                if(nodeName.equals(XMLSaxFieldNames.DAY_WORKOUT_LIST.getFieldName())) {
                                    isDayWorkOutListOpened = true;
                                    return;
                                }
                                if(nodeName.equals(XMLSaxFieldNames.DAY_WORK_LIST.getFieldName())) {
                                    isDayWorkListOpened = true;
                                    return;
                                }
                                if(isDayWorkOutListOpened) {
                                    if(nodeName.equals(XMLSaxFieldNames.DATE_OF_WORK_DAY.getFieldName())) {
                                        isDateOfWorkDayOpened = true;
                                        return;
                                    }
                                }
                                if(isDayWorkListOpened) {
                                    if(nodeName.equals(XMLSaxFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())) {
                                        isDateOfWorkOutDayOpened = true;
                                        return;
                                    }
                                }
                            }
                        }
                    }


                }
                if(nodeName.equals(XMLSaxFieldNames.WEEK.getFieldName())) {
                    isWeekOpened = true;
                    return;
                }
                if(isWeekOpened) {
                    if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_NAME_LIST.getFieldName())) {
                        isWeekDayNameListOpened = true;
                        return;
                    }
                    if(isWeekDayNameListOpened) {
                        if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_NAME.getFieldName())) {
                            isWeekDayNameOpened =  true;
                            return;
                        }
                        if(isWeekDayNameOpened) {
                            if(nodeName.equals(XMLSaxFieldNames.DAY_NAME.getFieldName())) {
                                isDayNameOpened = true;
                                return;
                            }
                            if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                                isWeekDayWorkOutOpened = true;
                                return;
                            }
                        }
                    }
                }

            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            nodeName = qName;
            if(nodeName.equals(XMLSaxFieldNames.CALENDAR_CONFIG.getFieldName())) {
                isCalendarOpened = false;
                return;
            }
            if(isCalendarOpened) {
                if(nodeName.equals(XMLSaxFieldNames.ANCHOR_WEEKDAY.getFieldName())) {
                    isAnchorWeekDayOpened = false;
                    anchorWeekDay.clone(dayConstructor);
                    dayConstructor.resetDay();
                    return;
                }
                if(isAnchorWeekDayOpened) {
                    if(nodeName.equals(XMLSaxFieldNames.DAY_NAME.getFieldName())) {
                        isDayNameOpened = false;
                        return;
                    }
                    if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                        isWeekDayWorkOutOpened = false;
                        return;
                    }
                }
                if(nodeName.equals(XMLSaxFieldNames.BEGINNING_YEAR.getFieldName())) {
                    isBeginningYearOpened = false;
                    return;
                }
                if(nodeName.equals(XMLSaxFieldNames.END_YEAR.getFieldName())) {
                    isEndYearOpened = false;
                    return;
                }
                if(nodeName.equals(XMLSaxFieldNames.YEAR_LIST.getFieldName())) {
                    isYearListOpened = false;
                    return;
                }
                if(isYearListOpened) {
                    if(nodeName.equals(XMLSaxFieldNames.YEAR_CONFIG.getFieldName())) {
                        isYearConfigOpened = false;
                        XMLSaxYearConfig year = new XMLSaxYearConfig();
                        year.clone(yearConstructor);
                        yearList.add(year);
                        yearConstructor.resetYearConfig();
                        return;
                    }
                    if(isYearConfigOpened) {
                        if(nodeName.equals(XMLSaxFieldNames.MONTH_LIST.getFieldName())) {
                            isMonthListOpened = false;
                            return;
                        }
                        if(isMonthListOpened) {
                            if(nodeName.equals(XMLSaxFieldNames.MONTH_CONFIG.getFieldName())) {
                                isMonthConfigOpened = false;
                                XMLSaxMonthConfig month = new XMLSaxMonthConfig();
                                month.clone(monthConstructor);
                                yearConstructor.addMonth(month);
                                monthConstructor.resetMonth();
                                return;
                            }
                            if(isMonthConfigOpened) {
                                if(nodeName.equals(XMLSaxFieldNames.NAME_OF_MONTH.getFieldName())) {
                                    isNameOfMonthOpened = false;
                                    return;
                                }
                                if(nodeName.equals(XMLSaxFieldNames.DAY_COUNT.getFieldName())) {
                                    isDayCountOpened = false;
                                    return;
                                }
                                if(nodeName.equals(XMLSaxFieldNames.DAY_WORKOUT_LIST.getFieldName())) {
                                    isDayWorkOutListOpened = false;
                                    return;
                                }
                                if(nodeName.equals(XMLSaxFieldNames.DAY_WORK_LIST.getFieldName())) {
                                    isDayWorkListOpened = false;
                                    return;
                                }
                                if(isDayWorkOutListOpened) {
                                    if(nodeName.equals(XMLSaxFieldNames.DATE_OF_WORK_DAY.getFieldName())) {
                                        isDateOfWorkDayOpened = false;
                                        return;
                                    }
                                }
                                if(isDayWorkListOpened) {
                                    if(nodeName.equals(XMLSaxFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())) {
                                        isDateOfWorkOutDayOpened = false;
                                        return;
                                    }
                                }

                            }
                        }

                    }


                }
                if(nodeName.equals(XMLSaxFieldNames.WEEK.getFieldName())) {
                    isWeekOpened = false;
                    XMLSaxWeekConfig weekBuffer = new XMLSaxWeekConfig();
                    weekBuffer.clone(weekConstructor);
                    week = weekBuffer;
                    weekConstructor.resetWeekConfig();
                    return;
                }
                if(isWeekOpened) {
                    if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_NAME_LIST.getFieldName())) {
                        isWeekDayNameListOpened = false;
                        return;
                    }
                    if(isWeekDayNameListOpened) {
                        if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_NAME.getFieldName())) {
                            isWeekDayNameOpened =  false;
                            XMLSaxDayConfig day = new XMLSaxDayConfig();
                            day.clone(dayConstructor);
                            weekConstructor.addWeekDay(day);
                            return;
                        }
                        if(isWeekDayNameOpened) {
                            if(nodeName.equals(XMLSaxFieldNames.DAY_NAME.getFieldName())) {
                                isDayNameOpened = false;
                                return;
                            }
                            if(nodeName.equals(XMLSaxFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                                isWeekDayWorkOutOpened = false;
                                return;
                            }
                            weekConstructor.addWeekDay(dayConstructor);
                            dayConstructor.resetDay();
                        }
                    }

                }

            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String data = "";
            for(Integer i=start; i<start+length; i++){
                data = data+String.valueOf(ch[i]);
            }
            if(data.equals("")) {
                return;
            }
            if(isCalendarOpened) {
                if(isAnchorWeekDayOpened) {
                    if(isDayNameOpened) {
                        dayConstructor.setDayName(data);
                        return;
                    }
                    if(isWeekDayWorkOutOpened) {
                        dayConstructor.setWeekDayWorkOut(Boolean.valueOf(data));
                        return;
                    }
                }
                if(isBeginningYearOpened) {
                    beginningYear = Integer.valueOf(data);
                }
                if(isEndYearOpened) {
                    endYear = Integer.valueOf(data);
                }
                if(isYearListOpened) {
                    if(isYearConfigOpened) {
                        if(isMonthListOpened) {
                            if(isMonthConfigOpened) {
                                if(isNameOfMonthOpened) {
                                    monthConstructor.setName(data);
                                    return;
                                }
                                if(isDayCountOpened) {
                                    monthConstructor.setDayCount(Integer.valueOf(data));
                                    return;
                                }
                                if(isDayWorkOutListOpened) {
                                    if(isDateOfWorkOutDayOpened) {
                                        monthConstructor.addWorkDay(Integer.valueOf(data));
                                        return;
                                    }
                                }
                                if(isDayWorkListOpened) {
                                    if(isDateOfWorkDayOpened) {
                                        monthConstructor.addWorkOutDay(Integer.valueOf(data));
                                        return;
                                    }
                                }
                            }
                        }
                        return;
                    }
                }
                if(isWeekOpened) {
                    if(isWeekDayNameListOpened) {
                        if(isWeekDayNameOpened) {
                            if(isDayNameOpened) {
                                dayConstructor.setDayName(data);
                            }
                            if(isWeekDayWorkOutOpened) {
                                dayConstructor.setWeekDayWorkOut(Boolean.valueOf(data));
                            }
                        }
                    }
                }

            }
        }
    }
    /**
     * День первого числа первого месяца привязочного года
     */
    private XMLSaxDayConfig anchorWeekDay = new XMLSaxDayConfig();
    /**
     * Список шаблонов лет
     */
    private final List<IYearTemplate> yearList = new ArrayList<>();
    /**
     * Шаблон недели
     */
    private IWeekTemplate week;
    /**
     * Год начала допустимого интервала календаря
     */
    private Integer beginningYear;
    /**
     * Год конца допустимого интервала календаря
     */
    private Integer endYear;

    /**
     * Конструктор календаря по передаваемому пути к конфигу
     *
     * @param path Путь к конфигу
     * @throws Exception
     */
    public XMLSaxCalendarConfig(String path) throws Exception {
        SAXParserFactory fac = SAXParserFactory.newInstance();
        SAXParser parser = fac.newSAXParser();
        CalendarConfigHandler handler = new CalendarConfigHandler();
        System.out.println(path);
        parser.parse(path, handler);
        System.out.println(yearList.get(0).getMonthList().size());

        /*
        DocumentBuilder builder = fac.newDocumentBuilder();
        Document document = builder.parse(new File(path));
        NodeList configOfCalendar = document.getFirstChild().getChildNodes();
        yearList = new ArrayList<>();
        for (Integer i = 0; i < configOfCalendar.getLength(); i++) {
            Node elementCalendar = configOfCalendar.item(i);
            if (elementCalendar.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // Получаем 1 день 1 месяца привязочного года
            if (elementCalendar.getNodeName().equals(XMLSaxFieldNames.ANCHOR_WEEKDAY.getFieldName())) {
                anchorWeekDay = new XMLSaxDayConfig(elementCalendar);
            }
            // Год начала допустимого интервала календаря
            if (elementCalendar.getNodeName().equals(XMLSaxFieldNames.BEGINNING_YEAR.getFieldName())) {
                beginningYear = Integer.valueOf(elementCalendar.getTextContent());
            }
            // Год конца допустимого интервала календаря
            if (elementCalendar.getNodeName().equals(XMLSaxFieldNames.END_YEAR.getFieldName())) {
                endYear = Integer.valueOf(elementCalendar.getTextContent());
            }
            //------------------------------------------------
            // Получаем список годов
            if (elementCalendar.getNodeName().equals(XMLSaxFieldNames.YEAR_LIST.getFieldName())) {
                NodeList yearConfigList = elementCalendar.getChildNodes();
                for (Integer j = 0; j < yearConfigList.getLength(); j++) {

                    Node yearConfig = yearConfigList.item(j);
                    if (yearConfig.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (yearConfig.getNodeName().equals(XMLSaxFieldNames.YEAR_CONFIG.getFieldName())) {
                        yearList.add(new XMLSaxYearConfig(yearConfig));
                    }
                }
            }
            // Получаем неделю
            if (elementCalendar.getNodeName().equals(XMLSaxFieldNames.WEEK.getFieldName())) {
                week = new XMLSaxWeekConfig(elementCalendar);
            }

        }
        */
    }
}
