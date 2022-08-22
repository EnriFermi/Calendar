package ru.study.calendar.config.body.inter.reading;

import ru.study.calendar.config.body.inter.parsing.IMonthTemplateForParsing;

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
    List<IMonthTemplateForParsing> getMonthList();
}
