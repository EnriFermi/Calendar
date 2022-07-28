package ru.study.calendar.item.impl;

import lombok.Getter;
import ru.study.calendar.item.IDay;

@Getter
public class Day implements IDay {
    Day(String weekDay, boolean isWorkingDay) {
        this.weekDay = weekDay;
        this.isWorkingDay = isWorkingDay;
    }
    private String weekDay;
    private boolean isWorkingDay;

}
