package ru.study.calendar.config.parsers.impl.xml.jaxb;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.domain.DayTemplate;
import ru.study.calendar.config.domain.MonthTemplate;
import ru.study.calendar.config.domain.WeekTemplate;
import ru.study.calendar.config.domain.YearTemplate;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CalendarConfigMapper {

    @Mapping(target = "anchorWeekDay", source = "jaxbDayConfig")
    @Mapping(target = "yearList", source = "jaxbYearList.yearConfigList")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", source = "jaxbWeek")
    public abstract CalendarTemplate calendarMapper(JaxbCalendarConfig calendarConfig);

    @Mapping(target = "monthList", source = "monthList.jaxbMonthConfigList")
    @Mapping(target = "dayQuantity", expression = "java(yearConfig.getMonthList().getJaxbMonthConfigList().stream()"+
            ".map(monthTemplate -> monthTemplate.getDayCount()).reduce((i, j) -> i + j).orElse(0))")
    public abstract YearTemplate yearMapper(JaxbYearConfig yearConfig);

    @Mapping(target = "name", source = "monthName")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList.dateOfWorkOutDay")
    @Mapping(target = "dayWorkList", source = "dayWorkList.dateOfWorkDay")
    public abstract MonthTemplate monthMapper(JaxbMonthConfig monthConfig);

    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayTemplate dayMapper(JaxbDayConfig dayConfig);

    @Mapping(target = "weekDayNameList", source = "jaxbWeekDayNameList.jaxbWeekDayConfigList")
    @Mapping(target = "weekDayCount", expression = "java(jaxbWeek.getJaxbWeekDayNameList().getJaxbWeekDayConfigList().size())")
    public abstract WeekTemplate weekMapper(JaxbWeek jaxbWeek);

}
