package ru.study.webapp.model.configuration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.study.webapp.exceptions.model.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
public class WeekTemplate {
    @Setter(AccessLevel.PUBLIC)
    /**
     * Количество дней в неделе
     */
    private Integer weekDayCount;

    public WeekTemplate(List<DayTemplate> weekDayNameList) throws ConfigurationException {
        this.weekDayNameList = new ArrayList<>();
        weekDayCount = 0;
        setWeekDayNameList(weekDayNameList);
    }

    public void setWeekDayNameList(List<DayTemplate> weekDayNameList) throws ConfigurationException {
        for(DayTemplate day: weekDayNameList){
            addWeekDay(day);
        }
    }

    /**
     * Список дней недели
     */
    private List<DayTemplate> weekDayNameList;
    public WeekTemplate() {
        weekDayCount = 0;
        weekDayNameList = new ArrayList<>();
    }
    /**
     * Добавляет день недели
     *
     * @param day Шаблон дня недели
     */
    public void addWeekDay(DayTemplate day) throws ConfigurationException {
        if (! weekDayNameList.contains(day)) {
            weekDayNameList.add(day);
            weekDayCount++;
        } else {
            throw new ConfigurationException("Не уникальное название дня недели: " + day.getDayName());
        }
    }
}
