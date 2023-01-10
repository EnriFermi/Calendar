package ru.study.webapp.controller.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DayControllerDTO {
    @Positive
    private Long id;

    @Positive
    private Long calendarControllerDTOId;

    private Long calendarControllerDTOVersion;

    @EqualsAndHashCode.Include
    private String dayName;

    private Boolean weekDayWorkOut;
}