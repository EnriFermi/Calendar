package ru.study.calendar.item.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.study.calendar.item.IDay;

@Getter
@AllArgsConstructor
public class Day implements IDay {
    private final String weekDay;
    private final boolean isWorkingDay;

}
