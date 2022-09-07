package ru.study.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.factory.ConfigFactory;
import ru.study.calendar.config.factory.enums.ConfigTypeEnum;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.item.impl.Calendar;
import ru.study.calendar.item.impl.CalendarService;

public class CalendarMain {
    /**
     * @see
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            CalendarTemplate calendarConfig = ConfigFactory.getCalendarTemplate("resources\\classicCalendar", ConfigTypeEnum.JAXB);
            Calendar calendar = new Calendar(2022, calendarConfig);


            log.info(CalendarService.getWeekDay(calendar, 10, "August").getWeekDay());
            log.info("В году всего " + CalendarService.getDayInYearCount(calendar) + " дней");
        } catch (ConfigurationException err) {
            //TODO разобраться с абстрактным throws Exception DONE
            log.error("Error: ", err);
            throw err;
        }
    }
}
