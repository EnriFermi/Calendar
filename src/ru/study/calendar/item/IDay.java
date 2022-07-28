package ru.study.calendar.item;

public interface IDay {
    /**
     * Выдает день недели
     * @return
     */
    String getWeekDay();

    /**
     * Выдает рабочий день или нет
     * @return
     */
    boolean isWorkingDay();
}
