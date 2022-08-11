package ru.study.calendar.config;

import java.util.List;

/**
 * Настройки календаря.
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface ICalendarTemplate {

    /**
     * Получаем день недели первого дня первого месяца привязочного года
     * @return День недели 1 дня 1 месяца привязочного года
     */
    IDayTemplate getAnchorWeekDay();

    /**
     * Получаем список шаблонов лет
     * @return Список шаблонов лет
     */
    List<IYearTemplate> getYearList();

    /**
     * Получаем шаблон недели
     * @return Шаблон недели
     */
    IWeekTemplate getWeek();

    /**
     * Получаем год начала допустимого интервала календаря
     * @return Начальный год из допустимого диапазона(он же привязочный год)
     */
    Integer getBeginningYear();

    /**
     * Получаем год конца допустимого интервала календаря
     * @return Последний год из допустимого диапазона
     */
    Integer getEndYear();
}
