package ru.study.calendar.trash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.impl.Calendar;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            ICalendar calendar = new Calendar(2021, "xml", "src\\test\\resources\\normalList");
            System.out.println(calendar.getWeekDay(9, "August").getWeekDay());
        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
