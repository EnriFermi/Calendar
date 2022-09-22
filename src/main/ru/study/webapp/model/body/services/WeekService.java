package ru.study.webapp.model.body.services;

import ru.study.webapp.model.configuration.domain.DayTemplate;
import ru.study.webapp.model.configuration.domain.WeekTemplate;

public class WeekService {
    /**
     * Выдает номер дня недели по объекту дня
     *
     * @param startDay Шаблон исходного дня
     * @param offset Смещение на которое нужно сместить день в неделе
     * @param week Шаблон дня недели
     * @return День смещенный относительно введенного дня на смещение offset
     */
     public static DayTemplate getOffsetDayFrom(DayTemplate startDay, Integer offset, WeekTemplate week) {
        Integer anchor = week.getWeekDayNameList().indexOf(startDay);
        return week.getWeekDayNameList().get((anchor + offset) % week.getWeekDayCount());
    }

}
