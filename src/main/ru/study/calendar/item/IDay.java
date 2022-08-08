package ru.study.calendar.item;

/**
 * Хранит сущность День
 */
public interface IDay {
    /**
     * Выдает день недели
     * @return Название дня недели
     */
    String getWeekDay();

    /**
     * Выдает рабочий день или нет
     * @return Рабочий ли день
     */
    boolean isWorkingDay();
}
