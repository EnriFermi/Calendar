package ru.study.calendar.item;
/**
 * Хранит сущность Месяц
 */
public interface IMonth {
    /**
     * По номеру дня выдает объект типа день
     * @param number Номер дня в месяце
     * @return Объект дня
     */
    IDay getDayByNumberInMonth(int number);
}
