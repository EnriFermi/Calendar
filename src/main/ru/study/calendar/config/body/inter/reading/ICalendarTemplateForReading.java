package ru.study.calendar.config.body.inter.reading;


import ru.study.calendar.config.body.impl.DayTemplate;
import ru.study.calendar.config.body.impl.WeekTemplate;
import ru.study.calendar.config.body.impl.YearTemplate;

import java.util.List;

/**
 * Настройки календаря.
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface ICalendarTemplateForReading {

    /**
     * Получаем день недели первого дня первого месяца привязочного года
     * @return День недели 1 дня 1 месяца привязочного года
     */
    DayTemplate getAnchorWeekDay();

    /**
     * Получаем список шаблонов лет
     * @return Список шаблонов лет
     */
    List<YearTemplate> getYearList();

    /**
     * Получаем шаблон недели
     * @return Шаблон недели
     */
    WeekTemplate getWeek();

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
