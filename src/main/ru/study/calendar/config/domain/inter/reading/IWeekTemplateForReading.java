package ru.study.calendar.config.domain.inter.reading;

import ru.study.calendar.config.domain.impl.DayTemplate;

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
    List<DayTemplate> getWeekDayNameList();

}
