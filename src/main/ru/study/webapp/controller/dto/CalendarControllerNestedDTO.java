package ru.study.webapp.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CalendarControllerNestedDTO {
    public CalendarControllerDTO calendarControllerDTO;
    public List<YearControllerDTO> yearControllerDTOList;
    public List<MonthControllerDTO> monthControllerDTOList;
    public List<DayControllerDTO> dayControllerDTOList;
    public List<DayWorkControllerDTO> dayWorkControllerDTOList;
    public List<DayWorkOutControllerDTO> dayWorkOutControllerDTOList;
    public Integer anchorDayId;

    public CalendarControllerNestedDTO(CalendarControllerDTO calendarControllerDTO,
                                       List<YearControllerDTO> yearControllerDTOList,
                                       List<MonthControllerDTO> monthControllerDTOList,
                                       List<DayControllerDTO> dayControllerDTOList,
                                       List<DayWorkControllerDTO> dayWorkControllerDTOList,
                                       List<DayWorkOutControllerDTO> dayWorkOutControllerDTOList,
                                       Integer anchorDayId) {
        this.calendarControllerDTO = calendarControllerDTO;
        this.yearControllerDTOList = yearControllerDTOList;
        this.monthControllerDTOList = monthControllerDTOList;
        this.dayControllerDTOList = dayControllerDTOList;
        this.dayWorkControllerDTOList = dayWorkControllerDTOList;
        this.dayWorkOutControllerDTOList = dayWorkOutControllerDTOList;
        this.anchorDayId = anchorDayId;
    }

    public CalendarControllerNestedDTO() {
    }
}
