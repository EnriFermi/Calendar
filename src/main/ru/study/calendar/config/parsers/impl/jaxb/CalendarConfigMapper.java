/*package ru.study.calendar.config.parsers.impl.jaxb;

import org.mapstruct.Mapping;
import ru.study.calendar.config.body.impl.CalendarTemplate;

@org.mapstruct.Mapper
public abstract class CalendarConfigMapper {
    @Mapping(target = "anchorWeekDay", source = "jaxbDayConfig")
    @Mapping(target = "yearList", source = "jaxbYearList")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", source = "axbWeek")
    public abstract CalendarTemplate calendarMapper(JaxbCalendarConfig calendarConfig);


    /*
    MonthTemplate monthMapper(JaxbMonthConfig monthConfig);

    YearTemplate yearMapper(JaxbYearConfig yearConfig);

    DayTemplate dayMapper(JaxbDayConfig dayConfig);

    DayTemplate weekMapper(JaxbWeek week);

}
*/