package ru.study.calendar.config.body.impl;

import lombok.Getter;
import ru.study.calendar.config.body.inter.parsing.IDayTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IWeekTemplateForParsing;
import ru.study.calendar.errors.errorTypes.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WeekTemplate implements IWeekTemplateForParsing {
    /**
     * Количество дней в неделе
     */
    private Integer weekDayCount;
    /**
     * Список дней недели
     */
    private List<IDayTemplateForParsing> weekDayNameList;

    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    public WeekTemplate() {
        weekDayCount = 0;
        weekDayNameList = new ArrayList<>();
    }
    public void addWeekDay(IDayTemplateForParsing day) throws ConfigurationException {
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

    public void clone(IWeekTemplateForParsing weekConstructor) {
        weekDayCount = weekConstructor.getWeekDayCount();
        weekDayNameList.addAll(weekConstructor.getWeekDayNameList());
    }
}
