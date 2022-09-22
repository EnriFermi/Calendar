package ru.study.webapp.model.body.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class Day {
    /**
     * Название дня недели
     */
    private final String weekDay;
    /**
     * Булевская переменная отвечающая за рабочий ли день
     */
    private final boolean isWorkingDay;
}
