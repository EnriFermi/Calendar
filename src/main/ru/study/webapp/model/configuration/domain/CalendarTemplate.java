package ru.study.webapp.model.configuration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter(AccessLevel.PUBLIC)
public class CalendarTemplate {
    /**
     * День первого числа первого месяца привязочного года
     */
    @Setter(AccessLevel.PUBLIC)
    private DayTemplate anchorWeekDay;
    /**
     * Список шаблонов лет
     */
    @Setter(AccessLevel.PUBLIC)
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
     * Добавляет шаблон года к списку лет
     *
     * @param year Шаблон года
     */
    public void addYear(YearTemplate year) {
        yearList.add(year);
    }
    public CalendarTemplate() {
        yearList = new ArrayList<>();
    }
}
