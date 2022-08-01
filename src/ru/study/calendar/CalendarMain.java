package ru.study.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.item.IDay;
import ru.study.calendar.item.impl.Calendar;

public class CalendarMain {
    public static void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            IDay resultDay = new Calendar(2022).getWeekDay(15, "May");
            log.info(resultDay.getWeekDay() + " " + resultDay.isWorkingDay());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
