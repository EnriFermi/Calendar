package ru.study.calendar.service;

import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;

public interface WeekService {
    /**
     * Возвращает день смещенный относительно введенного дня на смещение offset
     */
    static IDayTemplate getOffsetDayFrom(IDayTemplate startDay, Integer offset, IWeekTemplate week) {

        Integer anchor = week.getWeekDayNameList().indexOf(startDay);
        return week.getWeekDayNameList().get((anchor + offset) % week.getWeekDayCount());
    }
}
