package ru.study.webapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import ru.study.webapp.exceptions.ValidationException;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter(AccessLevel.PUBLIC)
public class CalendarControllerDTO {

    private final Long id;

    private final Long version;

    @PositiveOrZero
    private final Integer beginningYear;

    @Positive
    private final Integer endYear;

    public CalendarControllerDTO(@JsonProperty("id") Long id,
                                 @JsonProperty("version") Long version,
                                 @JsonProperty("beginningYear") Integer beginningYear,
                                 @JsonProperty("endYear") Integer endYear) {
        if (beginningYear > endYear) {
            throw new ValidationException(CalendarControllerDTO.class,
                    id, "Не корректный диапазон года: beginningYear > endYear");
        }
        this.id = id;
        this.version = version;
        this.beginningYear = beginningYear;
        this.endYear = endYear;
    }
}
