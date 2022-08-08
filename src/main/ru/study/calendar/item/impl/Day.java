package ru.study.calendar.item.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.study.calendar.item.IDay;

@Getter
@AllArgsConstructor
public class Day implements IDay {
    /**
     * Название дня недели
     */
    private final String weekDay;
    /**
     * Булевская переменная отвечающая за рабочий ли день
     */
    private final boolean isWorkingDay;
}
