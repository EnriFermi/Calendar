package ru.study.calendar.config.body.inter.reading;

import ru.study.calendar.config.body.inter.parsing.IDayTemplateForParsing;

import java.util.List;
/**
 * Шаблон недели, используется для параметризации недели
 *
 */
public interface IWeekTemplateForReading {
    /**
     * @return Количество дней в неделе
     */
    Integer getWeekDayCount();

    /**
     * @return Список дней недели
     */
    List<IDayTemplateForParsing> getWeekDayNameList();

}
