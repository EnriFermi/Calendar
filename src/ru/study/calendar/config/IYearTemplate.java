package ru.study.calendar.config;

import java.util.List;

/**
 * Шаблон года, используется для параметризации года
 *
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IYearTemplate {
    /**
     * Количество дней в годе
     */
    //TODO самому посчитать при инициализации
    Integer getDayQuantity();

    //TODO remove
    /**
     * Количество месяцев
     */
    Integer getNumberOfMonth();

    /**
     * Параметры месяцев
     */
    List<IMonthTemplate> getMonthList();

    /**
     * Возвращаем по названию месяца его индекс
     * @param name
     * @return
     * @throws Exception
     */
    Integer getIndexOfMonthByName(String name) throws Exception;

}
