package ru.study.webapp.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.study.webapp.exceptions.ValidationException;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter(AccessLevel.PUBLIC)
public class CalendarControllerDTO {

    //TODO по хорошему надо сообщать информацию о странице. Посмотреть Page<>
    @Setter(AccessLevel.PUBLIC)
    //TODO попробовать через final
    private Long id;

    @PositiveOrZero
    private Integer beginningYear = -1;

    @Positive
    private Integer endYear = -1;

    //TODO проверить создание через конструктор
    public void setBeginningYear(Integer beginningYear) {
        if(endYear != -1 && beginningYear >= endYear){
            throw new ValidationException(CalendarControllerDTO.class,
                    this.id, "Не корректный диапазон года: beginningYear > endYear");
        }
        this.beginningYear = beginningYear;
    }

    public void setEndYear(Integer endYear) {
        if(beginningYear != -1 && endYear <= beginningYear){
            throw new ValidationException(CalendarControllerDTO.class,
                    this.id, "Не корректный диапазон года: beginningYear > endYear");
        }
        this.endYear = endYear;
    }
}
