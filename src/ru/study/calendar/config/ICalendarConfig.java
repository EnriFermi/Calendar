package ru.study.calendar.config;

import java.util.List;

/**
 * Настройки календаря.
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
//TODO add comment
//TODO сделать ограничение на допустимые года, добавить 2 поля в конфиг (начала и окончания корректности работы календаря)
public interface ICalendarConfig {
    //TODO может IDayTemplate? Первый день недели в допустимом интервале
    String getAnchorWeekDay();
    List<IYearTemplate> getYearList();
    IWeekTemplate getWeek();
    Integer getAnchorYear();
}
