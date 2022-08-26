package ru.study.calendar.config.body.impl;

import lombok.AccessLevel;
import lombok.Setter;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;

import java.util.ArrayList;
import java.util.List;

public class CalendarTemplate implements ICalendarTemplateForReading {
    /**
     * День первого числа первого месяца привязочного года
     */
    @Setter(AccessLevel.PUBLIC)
    private DayTemplate anchorWeekDay;
    /**
     * Список шаблонов лет
     */
    private List<YearTemplate> yearList;
    /**
     * Шаблон недели
     */
    @Setter(AccessLevel.PUBLIC)
    private WeekTemplate week;
    /**
     * Год начала допустимого интервала календаря
     */
    @Setter(AccessLevel.PUBLIC)
    private Integer beginningYear;
    /**
     * Год конца допустимого интервала календаря
     */
    @Setter(AccessLevel.PUBLIC)
    private Integer endYear;
    /**
     * Конструктор календаря по передаваемому пути к конфигу
     *
     * @throws Exception
     */
    public void addYear(YearTemplate year) {
        yearList.add(year);
    }

    public CalendarTemplate() {
        yearList = new ArrayList<>();
    }

    @Override
    public DayTemplate getAnchorWeekDay() {
        return anchorWeekDay;
    }

    @Override
    public List<YearTemplate> getYearList() {
        return yearList;
    }

    @Override
    public WeekTemplate getWeek() {
        return week;
    }

    @Override
    public Integer getBeginningYear() {
        return beginningYear;
    }

    @Override
    public Integer getEndYear() {
        return endYear;
    }
}
