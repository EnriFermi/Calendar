package ru.study.calendar.trash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.domain.impl.CalendarTemplate;
import ru.study.calendar.config.factory.ConfigFactory;
import ru.study.calendar.config.factory.enums.ConfigTypesEnum;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.impl.Calendar;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            CalendarTemplate calendarTemplate = ConfigFactory.getCalendarTemplate("resources/classicCalendar", ConfigTypesEnum.JSON);
            ICalendar calendar = new Calendar(2022, calendarTemplate);
            log.info(calendar.getWeekDay(8, "August").getWeekDay());

        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
