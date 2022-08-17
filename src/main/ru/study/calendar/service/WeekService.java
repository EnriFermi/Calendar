package ru.study.calendar.service;

import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;

public interface WeekService {
    /**
     * @param startDay Шаблон исходного дня
     * @param offset Смещение на которое нужно сместить день в неделе
     * @param week Шаблон дня недели
     * @return День смещенный относительно введенного дня на смещение offset
     */
    static IDayTemplate getOffsetDayFrom(IDayTemplate startDay, Integer offset, IWeekTemplate week) {
        Integer anchor = week.getWeekDayNameList().indexOf(startDay);
        return week.getWeekDayNameList().get((anchor + offset) % week.getWeekDayCount());
    }

}
