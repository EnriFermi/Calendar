package ru.study.calendar.item;

/**
 * Хранит сущность Календарь
 */
public interface ICalendar {
    /**
     * Выдает по номеру дня и названию месяца день недели
     * @param intDay Номер дня в месяце (начинается с 1)
     * @param stringMonth Название месяца
     * @return Объект дня
     * @throws Exception
     */
    IDay getWeekDay(Integer intDay, String stringMonth) throws Exception;

}
