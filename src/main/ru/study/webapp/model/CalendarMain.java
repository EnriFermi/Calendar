package ru.study.webapp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.webapp.model.body.domain.Calendar;
import ru.study.webapp.model.body.services.CalendarService;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.factory.ConfigurationFactory;
import ru.study.webapp.model.configuration.factory.enums.ConfigTypeEnum;
import ru.study.webapp.exceptions.model.ConfigurationException;

import java.io.File;

public class CalendarMain {
    /**
     * @see
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            File directory = new File("./");
            log.info(directory.getAbsolutePath());
            CalendarTemplate calendarConfig = ConfigurationFactory.getCalendarTemplate("resources\\classicCalendar", ConfigTypeEnum.JAXB);
            Calendar calendar = new Calendar(2022, calendarConfig);


            log.info(CalendarService.getWeekDay(calendar, 10, "August").getWeekDay());
        } catch (ConfigurationException err) {
            log.error("Error: ", err);
            throw err;
        }
    }
}
