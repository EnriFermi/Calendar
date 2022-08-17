package ru.study.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            ICalendar calendar = new Calendar(2022, "xml", "resources\\classicCalendar");
            log.info(calendar.getWeekDay(8, "August").getWeekDay());
            log.info("В году всего " + calendar.getDayInYearCount() + " дней");
            //TODO как получить всего кол-во дней log.info("В году всего " + resultDay.); DONE
        } catch (Exception err) {
            //TODO выпилить  ErrorHandler DONE
            log.error("Error: ", err);
            throw err;
        }
    }
}
