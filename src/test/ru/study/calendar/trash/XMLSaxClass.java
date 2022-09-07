package ru.study.calendar.trash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.factory.ConfigFactory;
import ru.study.calendar.config.factory.enums.ConfigTypeEnum;
import ru.study.calendar.item.impl.Calendar;
import ru.study.calendar.item.impl.CalendarService;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            CalendarTemplate calendarTemplate = ConfigFactory.getCalendarTemplate("resources/classicCalendar", ConfigTypeEnum.JSON);
            Calendar calendar = new Calendar(2022, calendarTemplate);
            log.info(CalendarService.getWeekDay(calendar, 10, "August").getWeekDay());

        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
