package ru.study.calendar.config.body.inter.reading;

import ru.study.calendar.config.body.impl.MonthTemplate;

import java.util.List;

/**
 * Шаблон года, используется для параметризации года
 *
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IYearTemplateForReading {
    /**
     * @return Количество дней в году
     */
    Integer getDayQuantity();

    /**
     * @return Список шаблонов месяцев
     */
    List<MonthTemplate> getMonthList();
}
