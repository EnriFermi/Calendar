package ru.study.calendar.config.body.impl;

import ru.study.calendar.config.body.inter.reading.IYearTemplateForReading;
import ru.study.calendar.exceptions.ConfigurationException;

import java.util.ArrayList;
import java.util.List;


public class YearTemplate implements IYearTemplateForReading {
    /**
     * Список месяцев в году
     */
    private List<MonthTemplate> monthList;
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
    public void addMonth(MonthTemplate month) throws ConfigurationException {
        if (!monthList.contains(month)) {
            monthList.add(month);
            dayQuantity+=month.getDayCount();
        } else {
            throw new ConfigurationException("Не уникальное название месяца: " + month.getName());
        }
    }
    public void resetYearTemplate() {
        dayQuantity = 0;
        monthList.clear();
    }

    public void clone(YearTemplate yearConstructor) {
        dayQuantity = yearConstructor.getDayQuantity();
        monthList.addAll(yearConstructor.getMonthList());
    }

    @Override
    public Integer getDayQuantity() {
        return dayQuantity;
    }

    @Override
    public List<MonthTemplate> getMonthList() {
        return monthList;
    }

    public void setDayQuantity(Integer dayQuantity) {
        this.dayQuantity = dayQuantity;
    }
}
