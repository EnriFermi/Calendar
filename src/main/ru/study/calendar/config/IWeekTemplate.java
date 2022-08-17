package ru.study.calendar.config;

import java.util.List;
/**
 * Шаблон недели, используется для параметризации недели
 *
 */
public interface IWeekTemplate {
    /**
     * @return Количество дней в неделе
     */
    Integer getWeekDayCount();

    //TODO везде return DONE (надеюсь)
    /**
     * @return Список дней недели
     */
    List<IDayTemplate> getWeekDayNameList();

}
