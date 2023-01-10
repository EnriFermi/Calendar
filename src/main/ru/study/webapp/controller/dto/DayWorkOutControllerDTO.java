package ru.study.webapp.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class DayWorkOutControllerDTO {
    @Positive
    private Long id;

    private Integer dateOfWorkOutDay;

    @Positive
    private Long monthControllerDTOId;
}
