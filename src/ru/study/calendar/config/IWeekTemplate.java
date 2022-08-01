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
    List<IDayTemplate> weekDayNameList();

    //TODO актуализировать коммент
    //TODO вынести в отдельный статичный класс + интерфейс, на вход подавать IWeekTemplate
    /**
     *  Пока не очень понял, как применить в существующих случаях
     */
    IDayTemplate getOffsetDayFrom(IDayTemplate startDate, Integer offset);
}
