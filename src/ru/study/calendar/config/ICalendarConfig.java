package ru.study.calendar.config;

import java.util.List;

/**
 * Настройки календаря.
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
//TODO add comment DONE
//TODO сделать ограничение на допустимые года, добавить 2 поля в конфиг (начала и окончания корректности работы календаря) DONE
public interface ICalendarConfig {
    //TODO может IDayTemplate? Первый день недели в допустимом интервале DONE

    /**
     * Получаем день недели первого дня первого месяца привязочного года
     * @return
     */
    IDayTemplate getAnchorWeekDay();

    /**
     * Получаем список шаблонов лет
     * @return
     */
    List<IYearTemplate> getYearList();

    /**
     * Получаем шаблон недели
     * @return
     */
    IWeekTemplate getWeek();

    /**
     * Получаем год начала допустимого интервала календаря
     * @return
     */
    Integer getBeginningYear();

    /**
     * Получаем год конца допустимого интервала календаря
     * @return
     */
    Integer getEndYear();
}
