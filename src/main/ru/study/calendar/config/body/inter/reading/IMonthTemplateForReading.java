package ru.study.calendar.config.body.inter.reading;

import java.util.List;

/**
 * Параметры месяца
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IMonthTemplateForReading {
    /**
     * @return Имя месяца
     */
    String getName();

    /**
     * @return Количество дней в месяце
     */
    Integer getDayCount();

    /**
     * Дополнительные не рабочие
     * @return Список дополнительных нерабочих дней
     */
    List<Integer> getDayWorkOutList();

    /**
     * Дополнительные рабочие
     * @return Список дополнительных рабочих дней
     */
    List<Integer> getDayWorkList();

}
