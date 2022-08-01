package ru.study.calendar.config;

/**
 * Параметры дня недели
 */

public interface IDayTemplate {
    /**
     * Название дня недели
     */
    String getDayName();
    /**
     * ! Выходные дни недели !
     */
    Boolean isDefaultDayWorkOut();

}
