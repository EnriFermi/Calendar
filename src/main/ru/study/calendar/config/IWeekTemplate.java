package ru.study.calendar.config;

import java.util.List;
/**
 * Шаблон недели, используется для параметризации недели
 *
 */
public interface IWeekTemplate {
    /**
     * Количество дней в неделе
     */
    Integer getWeekDayCount();

    /**
     * Параметры дней недели
     */
    List<IDayTemplate> getWeekDayNameList();

    //TODO актуализировать коммент DONE
    //TODO вынести в отдельный статичный класс + интерфейс, на вход подавать IWeekTemplate DONE
}
