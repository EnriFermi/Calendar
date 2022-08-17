package ru.study.calendar.config;

import java.util.List;

/**
 * Шаблон года, используется для параметризации года
 *
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IYearTemplate {
    /**
     * @return Количество дней в году
     */
    Integer getDayQuantity();

    /**
     * @return Список шаблонов месяцев
     */
    List<IMonthTemplate> getMonthList();
}
