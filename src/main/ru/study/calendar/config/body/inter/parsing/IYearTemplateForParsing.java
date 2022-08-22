package ru.study.calendar.config.body.inter.parsing;

import ru.study.calendar.config.body.inter.reading.IYearTemplateForReading;
import ru.study.calendar.errors.errorTypes.ConfigurationException;

/**
 * Шаблон года, используется для параметризации года
 *
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IYearTemplateForParsing extends IYearTemplateForReading {
    /**
     * @return Количество дней в году
     */
    public void addMonth(IMonthTemplateForParsing month) throws ConfigurationException;

    /**
     * @return Список шаблонов месяцев
     */
    public void clone(IYearTemplateForParsing yearConstructor) ;
    public void resetYearTemplate();
}
