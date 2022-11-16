package ru.study.webapp.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.study.webapp.controller.dto.*;
import ru.study.webapp.model.database.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class DatabaseDTOMapper {

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "yearList", ignore = true)
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "dayList", ignore = true)
    public abstract CalendarDatabaseModel calendarControllerDTOToCalendarDatabaseModel(CalendarControllerDTO calendarConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "monthList", ignore = true)
    @Mapping(target = "calendarDatabaseModel", expression = "java(new CalendarDatabaseModel(yearConfig" +
            ".getCalendarControllerDTOId()))")
    public abstract YearDatabaseModel yearControllerDTOToYearDatabaseModel(YearControllerDTO yearConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkList", ignore = true)
    @Mapping(target = "dayWorkOutList", ignore = true)
    @Mapping(target = "yearDatabaseModel", expression = "java(new YearDatabaseModel(monthConfig" +
            ".getYearControllerDTOId()))")
    public abstract MonthDatabaseModel monthControllerDTOToMonthDatabaseModel(MonthControllerDTO monthConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    @Mapping(target = "calendarDatabaseModel", expression = "java(new CalendarDatabaseModel(dayConfig" +
            ".getCalendarControllerDTOId()))")
    public abstract DayDatabaseModel dayControllerDTOToDayDatabaseModel(DayControllerDTO dayConfig);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "dateOfWorkOutDay", source = "dateOfWorkOutDay")
    @Mapping(target = "monthDatabaseModel", expression = "java(new MonthDatabaseModel(dayWorkOutConfig" +
            ".getMonthControllerDTOId()))")
    public abstract DayWorkOutDatabaseModel dayWorkOutControllerDTOToDayWorkOutDatabaseModel(DayWorkOutControllerDTO dayWorkOutConfig);
    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "dateOfWorkDay", source = "dateOfWorkDay")
    @Mapping(target = "monthDatabaseModel", expression = "java(new MonthDatabaseModel(dayWorkConfig" +
            ".getMonthControllerDTOId()))")
    public abstract DayWorkDatabaseModel dayWorkControllerDTOToDayWorkDatabaseModel(DayWorkControllerDTO dayWorkConfig);
//------------------------------------------------------------------------------------

    @Mapping(target = "id", source = "id")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    public abstract CalendarControllerDTO calendarDatabaseModelToCalendarControllerDTO(CalendarDatabaseModel calendarConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "calendarControllerDTOId", source = "calendarDatabaseModel.id")
    public abstract YearControllerDTO yearDatabaseModelToYearControllerDTO(YearDatabaseModel yearConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "yearControllerDTOId", source = "yearDatabaseModel.id")
    public abstract MonthControllerDTO monthDatabaseModelToMonthControllerDTO(MonthDatabaseModel monthConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    @Mapping(target = "calendarControllerDTOId", source = "calendarDatabaseModel.id")
    public abstract DayControllerDTO dayDatabaseModelToDayControllerDTO(DayDatabaseModel dayConfig);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateOfWorkOutDay", source = "dateOfWorkOutDay")
    @Mapping(target = "monthControllerDTOId", source = "monthDatabaseModel.id")
    public abstract DayWorkOutControllerDTO dayWorkOutDatabaseModelToDayWorkOutControllerDTO(DayWorkOutDatabaseModel dayWorkOutDAO);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateOfWorkDay", source = "dateOfWorkDay")
    @Mapping(target = "monthControllerDTOId", source = "monthDatabaseModel.id")
    public abstract DayWorkControllerDTO dayWorkDatabaseModelToDayWorkControllerDTO(DayWorkDatabaseModel dayWorkDAO);
}
