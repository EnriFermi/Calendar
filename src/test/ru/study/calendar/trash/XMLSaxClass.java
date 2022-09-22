package ru.study.calendar.trash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import ru.study.webapp.model.CalendarMain;
import ru.study.webapp.model.body.domain.Calendar;
import ru.study.webapp.model.body.services.CalendarService;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.factory.ConfigurationFactory;
import ru.study.webapp.model.configuration.factory.enums.ConfigTypeEnum;

import java.io.File;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println(path.getAbsolutePath());
            CalendarTemplate calendarTemplate = ConfigurationFactory.getCalendarTemplate("resources/classicCalendar", ConfigTypeEnum.JSON);
            Calendar calendar = new Calendar(2022, calendarTemplate);
            log.info(CalendarService.getWeekDay(calendar, 10, "August").getWeekDay());

        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
