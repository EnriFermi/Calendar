package ru.study.calendar.config.body.inter.parsing;

import ru.study.calendar.config.body.inter.reading.IMonthTemplateForReading;
import ru.study.calendar.errors.errorTypes.ConfigurationException;

/**
 * Параметры месяца
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IMonthTemplateForParsing extends IMonthTemplateForReading {
    /**
     * @return Имя месяца
     */
    public void setName(String name);

    /**
     * @return Количество дней в месяце
     */
    public void setDayCount(Integer dayCount);

    /**
     * Дополнительные не рабочие
     * @return Список дополнительных нерабочих дней
     */
    public void addWorkDay(Integer date) throws ConfigurationException;

    /**
     * Дополнительные рабочие
     * @return Список дополнительных рабочих дней
     */
    public void addWorkOutDay(Integer date) throws ConfigurationException;
    public void resetMonth();
    public void clone(IMonthTemplateForParsing month);
}
