package ru.study.calendar.config.body.inter.reading;

import ru.study.calendar.config.body.inter.parsing.IDayTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IWeekTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IYearTemplateForParsing;

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
    IDayTemplateForParsing getAnchorWeekDay();

    /**
     * Получаем список шаблонов лет
     * @return Список шаблонов лет
     */
    List<IYearTemplateForParsing> getYearList();

    /**
     * Получаем шаблон недели
     * @return Шаблон недели
     */
    IWeekTemplateForParsing getWeek();

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
