package ru.study.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.item.impl.Calendar;

public class CalendarMain {
    public static void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        Calendar x = null;
        try {
            x = new Calendar(2022);
            log.info(x.getWeekDay(15, "May").getWeekDay());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        //TODO заменить system.out на slf4j https://www.baeldung.com/slf4j-with-log4j2-logback DONE
    }
}
