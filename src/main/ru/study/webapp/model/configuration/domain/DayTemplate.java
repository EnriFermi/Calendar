package ru.study.webapp.model.configuration.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DayTemplate {
    /**
     * Название дня недели
     */

    @EqualsAndHashCode.Include
    private String dayName;

    /**
     * Указание рабочий ли день
     */
    private Boolean weekDayWorkOut;
}