package ru.study.calendar;

import ru.study.calendar.item.impl.Calendar;

public class CalendarMain {

    public static void main(String[] args) {
        Calendar x = new Calendar(2015);
        //TODO заменить system.out на slf4j https://www.baeldung.com/slf4j-with-log4j2-logback
        System.out.println(x.getWeekDay(16, "May").isWorkingDay());
    }
}
