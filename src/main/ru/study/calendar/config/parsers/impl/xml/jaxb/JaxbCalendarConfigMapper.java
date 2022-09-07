package ru.study.calendar.config.parsers.impl.xml.jaxb;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.study.calendar.config.domain.*;
import ru.study.calendar.config.parsers.impl.xml.xsd.*;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class JaxbCalendarConfigMapper {

    @Mapping(target = "anchorWeekDay", source = "anchorWeekDay")
    @Mapping(target = "yearList", source = "yearList.yearConfig")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", source = "week")
    public abstract CalendarTemplate calendarMapper(CalendarConfig calendarConfig);

    @Mapping(target = "monthList", source = "monthList.monthConfig")
    @Mapping(target = "dayQuantity", expression = "java(yearConfig.getMonthList().getMonthConfig().stream()"+
            ".map(monthTemplate -> monthTemplate.getDayCount()).reduce((i, j) -> i + j).orElse(0))")
    public abstract YearTemplate yearMapper(YearConfigType yearConfig);

    @Mapping(target = "name", source = "nameOfMonth")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList.dateOfWorkOutDay")
    @Mapping(target = "dayWorkList", source = "dayWorkList.dateOfWorkDay")
    public abstract MonthTemplate monthMapper(MonthConfigType monthConfig);

    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayTemplate dayMapper(WeekDayNameType dayConfig);
    @Mapping(target = "weekDayNameList", source = "weekDayNameList.weekDayName")
    @Mapping(target = "weekDayCount", expression = "java(weekConfig.getWeekDayNameList().getWeekDayName().size())")
    public abstract WeekTemplate weekMapper(WeekType weekConfig);

}
