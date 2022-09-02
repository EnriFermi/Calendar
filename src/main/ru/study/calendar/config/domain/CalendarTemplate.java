package ru.study.calendar.config.domain;

import lombok.AccessLevel;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
public class CalendarTemplate {
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

    public DayTemplate getAnchorWeekDay() {
        return anchorWeekDay;
    }
    public List<YearTemplate> getYearList() {
        return yearList;
    }

    public WeekTemplate getWeek() {
        return week;
    }

    public Integer getBeginningYear() {
        return beginningYear;
    }

    public Integer getEndYear() {
        return endYear;
    }
}
