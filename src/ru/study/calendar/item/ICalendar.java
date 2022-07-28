package ru.study.calendar.item;

public interface ICalendar {
    /**
     *   Выдает по номеру дня и названию месяца день недели
     */
    IDay getWeekDay(Integer intDay, String stringMonth);

}
