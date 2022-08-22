package ru.study.calendar.config.body.inter.parsing;

import ru.study.calendar.config.body.inter.reading.IDayTemplateForReading;

/**
 * Параметры дня недели
 */
public interface IDayTemplateForParsing extends IDayTemplateForReading {
    /**
     * @return Название дня недели
     */
    public void setDayName(String name);
    /**
     * @return Выходной ли день
     */
    public void setWeekDayWorkOut(Boolean isWorking);
    public void resetDay();
    public void clone(IDayTemplateForParsing day);
}
