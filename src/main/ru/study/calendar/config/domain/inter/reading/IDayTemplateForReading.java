package ru.study.calendar.config.domain.inter.reading;

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
