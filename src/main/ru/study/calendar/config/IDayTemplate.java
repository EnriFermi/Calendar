package ru.study.calendar.config;

/**
 * Параметры дня недели
 */

//TODO что будет если назовем день в неделе,и добавим переменную номер дня в неделе (for what?)
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
