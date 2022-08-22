package ru.study.calendar.config.body.inter.reading;

/**
 * Параметры дня недели
 */
public interface IDayTemplateForReading {
    /**
     * @return Название дня недели
     */
    String getDayName();
    /**
     * @return Выходной ли день
     */
    Boolean isDefaultDayWorkOut();
}
