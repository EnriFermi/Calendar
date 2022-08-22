package ru.study.calendar.config.body.inter.parsing;

import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;

/**
 * Настройки календаря.
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface ICalendarTemplateForParsing extends ICalendarTemplateForReading {

    /**
     * Получаем день недели первого дня первого месяца привязочного года
     * @return День недели 1 дня 1 месяца привязочного года
     */
    public void setAnchorWeekDay(IDayTemplateForParsing day);

    /**
     * Получаем список шаблонов лет
     * @return Список шаблонов лет
     */
    public void addYear(IYearTemplateForParsing year);

    /**
     * Получаем шаблон недели
     * @return Шаблон недели
     */
    public void setWeek(IWeekTemplateForParsing week);

    /**
     * Получаем год начала допустимого интервала календаря
     * @return Начальный год из допустимого диапазона(он же привязочный год)
     */
    public void setBeginningYear(Integer beginningYear);

    /**
     * Получаем год конца допустимого интервала календаря
     * @return Последний год из допустимого диапазона
     */
    public void setEndYear(Integer endYear);
}
