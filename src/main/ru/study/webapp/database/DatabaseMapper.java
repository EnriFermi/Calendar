package ru.study.webapp.database;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DatabaseMapper {
/*
    @Mapping(target = "anchorWeekDay", source = "anchorWeekDay")
    @Mapping(target = "yearList", source = "yearList")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", source = "dayList")
    public abstract CalendarTemplate calendarDAOToCalendarTemplate(CalendarDAO calendarConfig);

    public WeekTemplate dayDAOListToWeekTemplate(List<DayDAO> dayDAOList) {
        WeekTemplate weekTemplate = new WeekTemplate();
        dayDAOList.forEach(dayDAO -> {
            try {
                weekTemplate.addWeekDay(dayDAOToDayTemplate(dayDAO));
            } catch (ConfigurationException e) {
                //FIXME заглушка
                throw new RuntimeException(e);
            }
        });
        return weekTemplate;
    }
    public YearTemplate yearDAOToYearTemplate(YearDAO yearConfig) {
        YearTemplate yearTemplate = new YearTemplate();
        yearConfig.getMonthList().forEach(monthDAO -> {
            try {
                yearTemplate.addMonth(monthDAOToMonthTemplate(monthDAO));
            } catch (ConfigurationException e) {
                //FIXME заглушка
                throw new RuntimeException(e);
            }
        });
        return yearTemplate;
    }

    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList")
    @Mapping(target = "dayWorkList", source = "dayWorkList")
    public abstract MonthTemplate monthDAOToMonthTemplate(MonthDAO monthConfig);

    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayTemplate dayDAOToDayTemplate(DayDAO dayConfig);

    public Integer dayWorkOutDAOToInteger(DayWorkOutDAO dayWorkOutDAO){
        return dayWorkOutDAO.getDateOfWorkOutDay();
    }
    public Integer dayWorkDAOToInteger(DayWorkDAO dayWorkDAO){
        return dayWorkDAO.getDateOfWorkDay();
    }*/
//---------------------------

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "anchorWeekDay", expression = "java(newCalendar.getAnchorWeekDay())")
    @Mapping(target = "yearList", source = "yearList", ignore = false)
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "dayList", source = "dayList")
    public abstract void updateCalendarDAO(CalendarDAO newCalendar,@MappingTarget CalendarDAO oldCalendar);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarDAO", ignore = true)
    @Mapping(target = "monthList", source = "monthList")
    public abstract void updateYearDAO(YearDAO newYear,@MappingTarget YearDAO oldYear);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "yearDAO", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList")
    @Mapping(target = "dayWorkList", source = "dayWorkList")
    public abstract void updateMonthDAO(MonthDAO newMonth,@MappingTarget MonthDAO oldMonth);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarDAO", ignore = true)
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract void updateDayDAO(DayDAO newDay,@MappingTarget DayDAO oldDay);
    public void updateDayDAOList(List<DayDAO> newDayDAOList,@MappingTarget List<DayDAO> oldDayDAOList) {
        Integer j;
        if (oldDayDAOList != null ) {
            if (newDayDAOList != null ) {
                for(int i=0; i<newDayDAOList.size(); i++) {
                    j = oldDayDAOList.indexOf(newDayDAOList.get(i));
                    if(j >= 0){
                        updateDayDAO(newDayDAOList.get(i), oldDayDAOList.get(j));
                    } else {
                        oldDayDAOList.add(newDayDAOList.get(i));
                    }
                }
            }
            else {
                oldDayDAOList = null;
            }
        }
        else {
            if ( newDayDAOList != null ) {
                oldDayDAOList = new ArrayList<DayDAO>();
                oldDayDAOList.addAll(newDayDAOList);
            }
        }
    }
    public void updateMonthDAOList(List<MonthDAO> newMonthDAOList,@MappingTarget List<MonthDAO> oldMonthDAOList) {
        Integer j;
        if (oldMonthDAOList != null ) {
            if (newMonthDAOList != null ) {
                for(int i=0; i<newMonthDAOList.size(); i++) {
                    j = oldMonthDAOList.indexOf(newMonthDAOList.get(i));
                    if(j >= 0){
                        updateMonthDAO(newMonthDAOList.get(i), oldMonthDAOList.get(j));
                    } else {
                        oldMonthDAOList.add(newMonthDAOList.get(i));
                    }
                }
            }
            else {
                oldMonthDAOList = null;
            }
        }
        else {
            if ( newMonthDAOList != null ) {
                oldMonthDAOList = new ArrayList<MonthDAO>();
                oldMonthDAOList.addAll(newMonthDAOList);
            }
        }
    }
    public void updateYearDAOList(List<YearDAO> newYearDAOList,@MappingTarget List<YearDAO> oldYearDAOList) {
        Integer j;
        if (oldYearDAOList != null ) {
            if (newYearDAOList != null ) {
                for(int i=0; i<newYearDAOList.size(); i++) {
                    j = oldYearDAOList.indexOf(newYearDAOList.get(i));
                    if(j >= 0){
                        updateYearDAO(newYearDAOList.get(i), oldYearDAOList.get(j));
                    } else {
                        oldYearDAOList.add(newYearDAOList.get(i));
                    }
                }
            }
            else {
                oldYearDAOList = null;
            }
        }
        else {
            if ( newYearDAOList != null ) {
                oldYearDAOList = new ArrayList<YearDAO>();
                oldYearDAOList.addAll(newYearDAOList);
            }
        }
    }
    public void updateDayWorkDAOList(List<DayWorkDAO> newDayWorkDAOList,@MappingTarget List<DayWorkDAO> oldDayWorkDAOList) {
        Integer j;
        if (oldDayWorkDAOList != null ) {
            if (newDayWorkDAOList != null ) {
                for(int i=0; i<newDayWorkDAOList.size(); i++) {
                    j = oldDayWorkDAOList.indexOf(newDayWorkDAOList.get(i));
                    if(j >= 0){
                        newDayWorkDAOList.get(i).setDateOfWorkDay(oldDayWorkDAOList.get(j).getDateOfWorkDay());
                    } else {
                        oldDayWorkDAOList.add(newDayWorkDAOList.get(i));
                    }
                }
            }
            else {
                oldDayWorkDAOList = null;
            }
        }
        else {
            if ( newDayWorkDAOList != null ) {
                oldDayWorkDAOList = new ArrayList<DayWorkDAO>();
                oldDayWorkDAOList.addAll(newDayWorkDAOList);
            }
        }
    }
    public void updateDayWorkOutDAOList(List<DayWorkOutDAO> newDayWorkOutDAOList, @MappingTarget List<DayWorkOutDAO> oldDayWorkOutDAOList) {
        Integer j;
        if (oldDayWorkOutDAOList != null ) {
            if (newDayWorkOutDAOList != null ) {
                for(int i=0; i<newDayWorkOutDAOList.size(); i++) {
                    j = oldDayWorkOutDAOList.indexOf(newDayWorkOutDAOList.get(i));
                    if(j >= 0){
                        newDayWorkOutDAOList.get(i).setDateOfWorkOutDay(oldDayWorkOutDAOList.get(j).getDateOfWorkOutDay());
                    } else {
                        oldDayWorkOutDAOList.add(newDayWorkOutDAOList.get(i));
                    }
                }
            }
            else {
                oldDayWorkOutDAOList = null;
            }
        }
        else {
            if ( newDayWorkOutDAOList != null ) {
                oldDayWorkOutDAOList = new ArrayList<DayWorkOutDAO>();
                oldDayWorkOutDAOList.addAll(newDayWorkOutDAOList);
            }
        }
    }
    @AfterMapping
    public void updateAnchorDay(@MappingTarget CalendarDAO calendarDAO){
        for(int i=0; i< calendarDAO.getDayList().size(); i++) {
            System.out.println(calendarDAO.getDayList().get(i).getDayName() + i);
            if(calendarDAO.getAnchorWeekDay().equals(calendarDAO.getDayList().get(i))) {
                calendarDAO.setAnchorWeekDay(calendarDAO.getDayList().get(i));
                break;
            }
        }
    }
}
