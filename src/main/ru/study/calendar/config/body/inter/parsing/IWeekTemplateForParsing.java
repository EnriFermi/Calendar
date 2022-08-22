package ru.study.calendar.config.body.inter.parsing;

import ru.study.calendar.config.body.inter.reading.IWeekTemplateForReading;
import ru.study.calendar.exceptions.ConfigurationException;

/**
 * Шаблон недели, используется для параметризации недели
 *
 */
public interface IWeekTemplateForParsing extends IWeekTemplateForReading {
    /**
     * @return Количество дней в неделе
     */
    public void addWeekDay(IDayTemplateForParsing day) throws ConfigurationException;

    /**
     * @return Список дней недели
     */
    public void resetWeekConfig();
    public void clone(IWeekTemplateForParsing weekConstructor);

}
