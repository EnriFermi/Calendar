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

    //TODO убрать метод и почитать про аннотации, например:
    //TODO подумать о пользе метода, на вход день и количество дней сдвига, на выходе результирующий день DONE

    /**
     *  Пока не очень понял, как применить в существующих случаях
     */
    IDayTemplate getOffsetDayFrom(IDayTemplate startDate, Integer offset);
}
