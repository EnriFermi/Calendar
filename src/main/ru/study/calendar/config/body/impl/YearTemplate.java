package ru.study.calendar.config.body.impl;

import lombok.AccessLevel;
import lombok.Getter;
import ru.study.calendar.config.body.inter.parsing.IMonthTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IYearTemplateForParsing;
import ru.study.calendar.errors.errorTypes.ConfigurationException;

import java.util.ArrayList;
import java.util.List;


@Getter(AccessLevel.PUBLIC)
public class YearTemplate implements IYearTemplateForParsing {
    /**
     * Список месяцев в году
     */
    private List<IMonthTemplateForParsing> monthList;
    /**
     * Количество дней в году
     */
    private Integer dayQuantity;

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @throws Exception
     */
    public YearTemplate(){
        monthList = new ArrayList<>();
        dayQuantity = 0;
    }
    public void addMonth(IMonthTemplateForParsing month) throws ConfigurationException {
        if (!monthList.contains(month)) {
            monthList.add((MonthTemplate) month);
            dayQuantity+=month.getDayCount();
        } else {
            throw new ConfigurationException("Не уникальное название месяца: " + month.getName());
        }
    }
    public void resetYearTemplate() {
        dayQuantity = 0;
        monthList.clear();
    }

    public void clone(IYearTemplateForParsing yearConstructor) {
        dayQuantity = yearConstructor.getDayQuantity();
        monthList.addAll(yearConstructor.getMonthList());
    }
}
