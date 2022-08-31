package ru.study.calendar.config.parsers.impl.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ru.study.calendar.config.domain.impl.CalendarTemplate;
import ru.study.calendar.config.domain.impl.DayTemplate;
import ru.study.calendar.config.domain.impl.MonthTemplate;
import ru.study.calendar.config.domain.impl.WeekTemplate;
import ru.study.calendar.config.domain.impl.YearTemplate;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-31T18:10:51+0300",
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
        List<JaxbYearConfig> yearConfigList = calendarConfigJaxbYearListYearConfigList( calendarConfig );
        if ( calendarTemplate.getYearList() != null ) {
            List<YearTemplate> list = jaxbYearConfigListToYearTemplateList( yearConfigList );
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

        List<JaxbMonthConfig> jaxbMonthConfigList = yearConfigMonthListJaxbMonthConfigList( yearConfig );
        if ( yearTemplate.getMonthList() != null ) {
            List<MonthTemplate> list = jaxbMonthConfigListToMonthTemplateList( jaxbMonthConfigList );
            if ( list != null ) {
                yearTemplate.getMonthList().addAll( list );
            }
        }

        yearTemplate.setDayQuantity( yearConfig.getMonthList().getJaxbMonthConfigList().stream().map(monthTemplate -> monthTemplate.getDayCount()).reduce((i, j) -> i + j).orElse(0) );

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
        List<Integer> dateOfWorkOutDay = monthConfigDayWorkOutListDateOfWorkOutDay( monthConfig );
        List<Integer> list = dateOfWorkOutDay;
        if ( list != null ) {
            monthTemplate.setDayWorkOutList( new ArrayList<Integer>( list ) );
        }
        List<Integer> dateOfWorkDay = monthConfigDayWorkListDateOfWorkDay( monthConfig );
        List<Integer> list1 = dateOfWorkDay;
        if ( list1 != null ) {
            monthTemplate.setDayWorkList( new ArrayList<Integer>( list1 ) );
        }

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

        List<JaxbDayConfig> jaxbWeekDayConfigList = jaxbWeekJaxbWeekDayNameListJaxbWeekDayConfigList( jaxbWeek );
        if ( weekTemplate.getWeekDayNameList() != null ) {
            List<DayTemplate> list = jaxbDayConfigListToDayTemplateList( jaxbWeekDayConfigList );
            if ( list != null ) {
                weekTemplate.getWeekDayNameList().addAll( list );
            }
        }

        weekTemplate.setWeekDayCount( jaxbWeek.getJaxbWeekDayNameList().getJaxbWeekDayConfigList().size() );

        return weekTemplate;
    }

    private List<JaxbYearConfig> calendarConfigJaxbYearListYearConfigList(JaxbCalendarConfig jaxbCalendarConfig) {
        if ( jaxbCalendarConfig == null ) {
            return null;
        }
        JaxbYearList jaxbYearList = jaxbCalendarConfig.getJaxbYearList();
        if ( jaxbYearList == null ) {
            return null;
        }
        List<JaxbYearConfig> yearConfigList = jaxbYearList.getYearConfigList();
        if ( yearConfigList == null ) {
            return null;
        }
        return yearConfigList;
    }

    protected List<YearTemplate> jaxbYearConfigListToYearTemplateList(List<JaxbYearConfig> list) {
        if ( list == null ) {
            return null;
        }

        List<YearTemplate> list1 = new ArrayList<YearTemplate>( list.size() );
        for ( JaxbYearConfig jaxbYearConfig : list ) {
            list1.add( yearMapper( jaxbYearConfig ) );
        }

        return list1;
    }

    private List<JaxbMonthConfig> yearConfigMonthListJaxbMonthConfigList(JaxbYearConfig jaxbYearConfig) {
        if ( jaxbYearConfig == null ) {
            return null;
        }
        JaxbMonthList monthList = jaxbYearConfig.getMonthList();
        if ( monthList == null ) {
            return null;
        }
        List<JaxbMonthConfig> jaxbMonthConfigList = monthList.getJaxbMonthConfigList();
        if ( jaxbMonthConfigList == null ) {
            return null;
        }
        return jaxbMonthConfigList;
    }

    protected List<MonthTemplate> jaxbMonthConfigListToMonthTemplateList(List<JaxbMonthConfig> list) {
        if ( list == null ) {
            return null;
        }

        List<MonthTemplate> list1 = new ArrayList<MonthTemplate>( list.size() );
        for ( JaxbMonthConfig jaxbMonthConfig : list ) {
            list1.add( monthMapper( jaxbMonthConfig ) );
        }

        return list1;
    }

    private List<Integer> monthConfigDayWorkOutListDateOfWorkOutDay(JaxbMonthConfig jaxbMonthConfig) {
        if ( jaxbMonthConfig == null ) {
            return null;
        }
        JaxbDayWorkOutList dayWorkOutList = jaxbMonthConfig.getDayWorkOutList();
        if ( dayWorkOutList == null ) {
            return null;
        }
        List<Integer> dateOfWorkOutDay = dayWorkOutList.getDateOfWorkOutDay();
        if ( dateOfWorkOutDay == null ) {
            return null;
        }
        return dateOfWorkOutDay;
    }

    private List<Integer> monthConfigDayWorkListDateOfWorkDay(JaxbMonthConfig jaxbMonthConfig) {
        if ( jaxbMonthConfig == null ) {
            return null;
        }
        JaxbDayWorkList dayWorkList = jaxbMonthConfig.getDayWorkList();
        if ( dayWorkList == null ) {
            return null;
        }
        List<Integer> dateOfWorkDay = dayWorkList.getDateOfWorkDay();
        if ( dateOfWorkDay == null ) {
            return null;
        }
        return dateOfWorkDay;
    }

    private List<JaxbDayConfig> jaxbWeekJaxbWeekDayNameListJaxbWeekDayConfigList(JaxbWeek jaxbWeek) {
        if ( jaxbWeek == null ) {
            return null;
        }
        JaxbWeekDayNameList jaxbWeekDayNameList = jaxbWeek.getJaxbWeekDayNameList();
        if ( jaxbWeekDayNameList == null ) {
            return null;
        }
        List<JaxbDayConfig> jaxbWeekDayConfigList = jaxbWeekDayNameList.getJaxbWeekDayConfigList();
        if ( jaxbWeekDayConfigList == null ) {
            return null;
        }
        return jaxbWeekDayConfigList;
    }

    protected List<DayTemplate> jaxbDayConfigListToDayTemplateList(List<JaxbDayConfig> list) {
        if ( list == null ) {
            return null;
        }

        List<DayTemplate> list1 = new ArrayList<DayTemplate>( list.size() );
        for ( JaxbDayConfig jaxbDayConfig : list ) {
            list1.add( dayMapper( jaxbDayConfig ) );
        }

        return list1;
    }
}
