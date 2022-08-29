package ru.study.calendar.service;

import ru.study.calendar.config.domain.inter.reading.IDayTemplateForReading;
import ru.study.calendar.config.domain.inter.reading.IWeekTemplateForReading;

public interface WeekService {
    /**
     * @param startDay Шаблон исходного дня
     * @param offset Смещение на которое нужно сместить день в неделе
     * @param week Шаблон дня недели
     * @return День смещенный относительно введенного дня на смещение offset
     */
    static IDayTemplateForReading getOffsetDayFrom(IDayTemplateForReading startDay, Integer offset, IWeekTemplateForReading week) {
        Integer anchor = week.getWeekDayNameList().indexOf(startDay);
        return week.getWeekDayNameList().get((anchor + offset) % week.getWeekDayCount());
    }

}
