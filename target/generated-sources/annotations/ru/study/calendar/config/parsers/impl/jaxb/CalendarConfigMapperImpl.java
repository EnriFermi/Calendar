package ru.study.calendar.config.parsers.impl.jaxb;

import java.util.List;
import javax.annotation.processing.Generated;
import ru.study.calendar.config.body.impl.CalendarTemplate;
import ru.study.calendar.config.body.impl.DayTemplate;
import ru.study.calendar.config.body.impl.MonthTemplate;
import ru.study.calendar.config.body.impl.WeekTemplate;
import ru.study.calendar.config.body.impl.YearTemplate;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-27T19:53:29+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class CalendarConfigMapperImpl extends CalendarConfigMapper {

    @Override
    public CalendarTemplate calendarMapper(JaxbCalendarConfig calendarConfig) {
        if ( calendarConfig == null ) {
            return null;
        }

        CalendarTemplate calendarTemplate = new CalendarTemplate();

        calendarTemplate.setAnchorWeekDay( dayMapper( calendarConfig.getJaxbDayConfig() ) );
        if ( calendarTemplate.getYearList() != null ) {
            List<YearTemplate> list = yearListMapper( calendarConfig.getJaxbYearList() );
            if ( list != null ) {
                calendarTemplate.getYearList().addAll( list );
            }
        }
        calendarTemplate.setBeginningYear( calendarConfig.getBeginningYear() );
        calendarTemplate.setEndYear( calendarConfig.getEndYear() );
        calendarTemplate.setWeek( weekMapper( calendarConfig.getJaxbWeek() ) );

        return calendarTemplate;
    }

    @Override
    public YearTemplate yearMapper(JaxbYearConfig yearConfig) {
        if ( yearConfig == null ) {
            return null;
        }

        YearTemplate yearTemplate = new YearTemplate();

        if ( yearTemplate.getMonthList() != null ) {
            List<MonthTemplate> list = monthListMapper( yearConfig.getMonthList() );
            if ( list != null ) {
                yearTemplate.getMonthList().addAll( list );
            }
        }

        setDayQuantity( yearTemplate );

        return yearTemplate;
    }

    @Override
    public MonthTemplate monthMapper(JaxbMonthConfig monthConfig) {
        if ( monthConfig == null ) {
            return null;
        }

        MonthTemplate monthTemplate = new MonthTemplate();

        monthTemplate.setName( monthConfig.getMonthName() );
        monthTemplate.setDayCount( monthConfig.getDayCount() );
        monthTemplate.setDayWorkOutList( weekDayWorkOutMapper( monthConfig.getDayWorkOutList() ) );
        monthTemplate.setDayWorkList( weekDayWorkMapper( monthConfig.getDayWorkList() ) );

        return monthTemplate;
    }

    @Override
    public DayTemplate dayMapper(JaxbDayConfig dayConfig) {
        if ( dayConfig == null ) {
            return null;
        }

        DayTemplate dayTemplate = new DayTemplate();

        dayTemplate.setDayName( dayConfig.getDayName() );
        dayTemplate.setWeekDayWorkOut( dayConfig.getWeekDayWorkOut() );

        return dayTemplate;
    }

    @Override
    public WeekTemplate weekMapper(JaxbWeek jaxbWeek) {
        if ( jaxbWeek == null ) {
            return null;
        }

        WeekTemplate weekTemplate = new WeekTemplate();

        if ( weekTemplate.getWeekDayNameList() != null ) {
            List<DayTemplate> list = weekDayListMapper( jaxbWeek.getJaxbWeekDayNameList() );
            if ( list != null ) {
                weekTemplate.getWeekDayNameList().addAll( list );
            }
        }

        setWeekDayCount( weekTemplate );

        return weekTemplate;
    }
}
