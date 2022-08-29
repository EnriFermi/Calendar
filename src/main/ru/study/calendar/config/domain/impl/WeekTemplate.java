package ru.study.calendar.config.domain.impl;

import ru.study.calendar.config.domain.inter.reading.IWeekTemplateForReading;
import ru.study.calendar.exceptions.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

public class WeekTemplate implements IWeekTemplateForReading {
    public void setWeekDayCount(Integer weekDayCount) {
        this.weekDayCount = weekDayCount;
    }

    /**
     * Количество дней в неделе
     */
    private Integer weekDayCount;
    /**
     * Список дней недели
     */
    private List<DayTemplate> weekDayNameList;

    /**
     * Конструктор недели по передаваемому JSON конфигу
     */
    public WeekTemplate() {
        weekDayCount = 0;
        weekDayNameList = new ArrayList<>();
    }
    public void addWeekDay(DayTemplate day) throws ConfigurationException {
        if (! weekDayNameList.contains(day)) {
            weekDayNameList.add(day);
            weekDayCount++;
        } else {
            throw new ConfigurationException("Не уникальное название дня недели: " + day.getDayName());
        }
    }
    public void resetWeekConfig() {
        weekDayCount=0;
        weekDayNameList.clear();
    }

    public void clone(WeekTemplate weekConstructor) {
        weekDayCount = weekConstructor.getWeekDayCount();
        weekDayNameList.addAll(weekConstructor.getWeekDayNameList());
    }

    @Override
    public Integer getWeekDayCount() {
        return weekDayCount;
    }

    @Override
    public List<DayTemplate> getWeekDayNameList() {
        return weekDayNameList;
    }
}
