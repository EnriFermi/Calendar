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
    //TODO самому посчитать при инициализации DONE
    Integer getDayQuantity();

    //TODO remove DONE
    /**
     * Параметры месяцев
     */
    List<IMonthTemplate> getMonthList();
}
