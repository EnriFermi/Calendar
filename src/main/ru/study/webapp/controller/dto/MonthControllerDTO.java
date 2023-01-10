package ru.study.webapp.controller.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MonthControllerDTO {
    @Positive
    private Long id;

    @EqualsAndHashCode.Include
    private String name;

    private Integer dayCount;

    @Positive
    private Long yearControllerDTOId;

}
