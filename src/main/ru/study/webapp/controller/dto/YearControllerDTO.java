package ru.study.webapp.controller.dto;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class YearControllerDTO {
    // TODO прочитать про @Valid DONE
    private Long id;

    @Positive
    private Long calendarControllerDTOId;
}
