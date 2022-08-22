package ru.study.calendar.config.body.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.study.calendar.config.body.inter.parsing.ICalendarTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IDayTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IWeekTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IYearTemplateForParsing;

import java.util.ArrayList;
import java.util.List;
@Getter

public class CalendarTemplate implements ICalendarTemplateForParsing {
    /**
     * День первого числа первого месяца привязочного года
     */
    @Setter(AccessLevel.PUBLIC)
    private IDayTemplateForParsing anchorWeekDay;
    /**
     * Список шаблонов лет
     */
    private List<IYearTemplateForParsing> yearList;
    /**
     * Шаблон недели
     */
    @Setter(AccessLevel.PUBLIC)
    private IWeekTemplateForParsing week;
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
     * @param path Путь к конфигу
     * @throws Exception
     */
    public void addYear(IYearTemplateForParsing year) {
        yearList.add(year);
    }

    public CalendarTemplate() {
        yearList = new ArrayList<>();
    }
}
