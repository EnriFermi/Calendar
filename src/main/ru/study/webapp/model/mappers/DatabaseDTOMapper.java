package ru.study.webapp.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.study.webapp.controller.dto.*;
import ru.study.webapp.model.database.*;
import ru.study.webapp.repository.entity.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class DatabaseDTOMapper {

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "yearList", ignore = true)
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "dayList", ignore = true)
    public abstract CalendarEntity calendarControllerDTOToCalendarEntity(CalendarControllerDTO calendarConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "monthList", ignore = true)
    @Mapping(target = "calendarEntity", expression = "java(new CalendarEntity(yearConfig" +
            ".getCalendarControllerDTOId()))")
    public abstract YearEntity yearControllerDTOToYearEntity(YearControllerDTO yearConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkList", ignore = true)
    @Mapping(target = "dayWorkOutList", ignore = true)
    @Mapping(target = "yearEntity", expression = "java(new YearEntity(monthConfig" +
            ".getYearControllerDTOId()))")
    public abstract MonthEntity monthControllerDTOToMonthEntity(MonthControllerDTO monthConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    @Mapping(target = "calendarEntity", expression = "java(new CalendarEntity(dayConfig" +
            ".getCalendarControllerDTOId()))")
    public abstract DayEntity dayControllerDTOToDayEntity(DayControllerDTO dayConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "dateOfWorkOutDay", source = "dateOfWorkOutDay")
    @Mapping(target = "monthEntity", expression = "java(new MonthEntity(dayWorkOutConfig" +
            ".getMonthControllerDTOId()))")
    public abstract DayWorkOutEntity dayWorkOutControllerDTOToDayWorkOutEntity(DayWorkOutControllerDTO dayWorkOutConfig);
    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "dateOfWorkDay", source = "dateOfWorkDay")
    @Mapping(target = "monthEntity", expression = "java(new MonthEntity(dayWorkConfig" +
            ".getMonthControllerDTOId()))")
    public abstract DayWorkEntity dayWorkControllerDTOToDayWorkEntity(DayWorkControllerDTO dayWorkConfig);
//------------------------------------------------------------------------------------

    @Mapping(target = "id", source = "id")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "version", source = "version")
    public abstract CalendarControllerDTO calendarEntityToCalendarControllerDTO(CalendarEntity calendarConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "calendarControllerDTOId", source = "calendarEntity.id")
    public abstract YearControllerDTO yearEntityToYearControllerDTO(YearEntity yearConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "yearControllerDTOId", source = "yearEntity.id")
    public abstract MonthControllerDTO monthEntityToMonthControllerDTO(MonthEntity monthConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    @Mapping(target = "calendarControllerDTOId", source = "calendarEntity.id")
    public abstract DayControllerDTO dayEntityToDayControllerDTO(DayEntity dayConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateOfWorkOutDay", source = "dateOfWorkOutDay")
    @Mapping(target = "monthControllerDTOId", source = "monthEntity.id")
    public abstract DayWorkOutControllerDTO dayWorkOutEntityToDayWorkOutControllerDTO(DayWorkOutEntity dayWorkOutDAO);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateOfWorkDay", source = "dateOfWorkDay")
    @Mapping(target = "monthControllerDTOId", source = "monthEntity.id")
    public abstract DayWorkControllerDTO dayWorkEntityToDayWorkControllerDTO(DayWorkEntity dayWorkDAO);
}
