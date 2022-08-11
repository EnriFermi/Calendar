package ru.study.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.errors.errorHandler.ErrorHandler;
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
            ICalendar resultDay = new Calendar(2022, "json", "resources\\classicCalendar.json");
            log.info(resultDay.getWeekDay(8, "August").getWeekDay());
            //TODO как получить всего кол-во дней log.info("В году всего " + resultDay.);
        } catch (Exception e) {
            //TODO выпилить  ErrorHandler
            ErrorHandler.run(e);
            throw e;
        }
    }
}
