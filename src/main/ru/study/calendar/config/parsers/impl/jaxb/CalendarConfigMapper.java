package ru.study.calendar.config.parsers.impl.jaxb;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.study.calendar.config.domain.impl.*;

import java.util.ArrayList;
import java.util.List;

//TODO почитать про параметры , всегда советую использовать ReportingPolicy.ERROR
@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class CalendarConfigMapper {

    @Mapping(target = "anchorWeekDay", source = "jaxbDayConfig")
    //TODO аналогично переделать, и заменить название метода
    @Mapping(target = "yearList", source = "jaxbYearList.yearConfigList")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", source = "jaxbWeek")
    public abstract CalendarTemplate calendarMapper(JaxbCalendarConfig calendarConfig);

    @Mapping(target = "monthList", source = "monthList")
    public abstract YearTemplate yearMapper(JaxbYearConfig yearConfig);

    public List<MonthTemplate> monthListMapper(JaxbMonthList jaxbMonthList) {
        List<MonthTemplate> monthTemplateList = new ArrayList<>();
        jaxbMonthList.getJaxbMonthConfigList().forEach(monthConfig -> monthTemplateList.add(monthMapper(monthConfig)));
        return monthTemplateList;
    }

    @Mapping(target = "name", source = "monthName")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList")
    @Mapping(target = "dayWorkList", source = "dayWorkList")
    public abstract MonthTemplate monthMapper(JaxbMonthConfig monthConfig);

    public List<Integer> weekDayWorkOutMapper(JaxbDayWorkOutList jaxbDayWorkOutList) {
        List<Integer> weekDayWorkOutList = new ArrayList<>();
        if (jaxbDayWorkOutList != null) {
            jaxbDayWorkOutList.getDateOfWorkOutDay()
                              .forEach(dateOfWorkOutDay -> weekDayWorkOutList.add(dateOfWorkOutDay));
        }
        return weekDayWorkOutList;
    }

    public List<Integer> weekDayWorkMapper(JaxbDayWorkList jaxbDayWorkList) {
        List<Integer> weekDayWorkList = new ArrayList<>();
        if (jaxbDayWorkList != null) {
            jaxbDayWorkList.getDateOfWorkDay().forEach(dateOfWorkOutDay -> weekDayWorkList.add(dateOfWorkOutDay));
        }
        return weekDayWorkList;
    }

    @AfterMapping
    protected void setDayQuantity(@MappingTarget YearTemplate yearTemplate) {
        yearTemplate.setDayQuantity(yearTemplate.getMonthList().stream().
                                                map(monthTemplate -> monthTemplate.getDayCount())
                                                .reduce((i, j) -> i + j).orElse(0));
    }

    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayTemplate dayMapper(JaxbDayConfig dayConfig);

    @Mapping(target = "weekDayNameList", source = "jaxbWeekDayNameList")
    public abstract WeekTemplate weekMapper(JaxbWeek jaxbWeek);

    @AfterMapping
    protected void setWeekDayCount(@MappingTarget WeekTemplate weekTemplate) {
        weekTemplate.setWeekDayCount(weekTemplate.getWeekDayNameList().size());
    }

    public List<DayTemplate> weekDayListMapper(JaxbWeekDayNameList jaxbWeekDayNameList) {
        List<DayTemplate> dayTemplateList = new ArrayList<>();
        jaxbWeekDayNameList.getJaxbWeekDayConfigList().forEach(dayConfig -> dayTemplateList.add(dayMapper(dayConfig)));
        return dayTemplateList;
    }
}
