package ru.study.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.config.parsers.ConfigFactory;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.impl.Calendar;

public class CalendarMain {
    /**
     * @see
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            //TODO ReT вернуться к разделению обязанностей Config
            ICalendarTemplateForReading calendarConfig = ConfigFactory.getCalendarTemplate("resources\\classicCalendar.xml", "jaxb");
            ICalendar calendar = new Calendar(2022, calendarConfig);

            log.info(calendar.getWeekDay(8, "August").getWeekDay());
            log.info("В году всего " + calendar.getDayInYearCount() + " дней");
        } catch (Exception err) {
            //TODO разобраться с абстрактным throws Exception
            log.error("Error: ", err);
            throw err;
        }
    }
}
