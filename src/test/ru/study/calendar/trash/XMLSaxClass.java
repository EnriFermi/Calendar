package ru.study.calendar.trash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigFactory;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.impl.Calendar;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            ICalendarTemplateForReading calendarConfig = ConfigFactory.getCalendarTemplate("resources\\serverConfiguration", "jdbc.xml");
            ICalendar calendar = new Calendar(2022, calendarConfig);

            log.info(calendar.getWeekDay(8, "August").getWeekDay());
            log.info("В году всего " + calendar.getDayInYearCount() + " дней");

        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
