package ru.study.calendar.config;

/**
 * Параметры дня недели
 */
public interface IDayTemplate {
    /**
     * @return Название дня недели
     */
    String getDayName();
    /**
     * @return Выходной ли день
     */
    Boolean isDefaultDayWorkOut();
}
