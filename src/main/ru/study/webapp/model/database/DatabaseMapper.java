package ru.study.webapp.model.database;

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
    public abstract void updateCalendarDAO(CalendarDatabaseModel newCalendar, @MappingTarget CalendarDatabaseModel oldCalendar);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarDatabaseModel", ignore = true)
    @Mapping(target = "monthList", source = "monthList")
    public abstract void updateYearDAO(YearDatabaseModel newYear, @MappingTarget YearDatabaseModel oldYear);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "yearDatabaseModel", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList")
    @Mapping(target = "dayWorkList", source = "dayWorkList")
    public abstract void updateMonthDAO(MonthDatabaseModel newMonth, @MappingTarget MonthDatabaseModel oldMonth);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarDatabaseModel", ignore = true)
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract void updateDayDAO(DayDatabaseModel newDay, @MappingTarget DayDatabaseModel oldDay);
    public void updateDayDAOList(List<DayDatabaseModel> newDayDatabaseModelList, @MappingTarget List<DayDatabaseModel> oldDayDatabaseModelList) {
        Integer j;
        if (oldDayDatabaseModelList != null ) {
            if (newDayDatabaseModelList != null ) {
                for(int i = 0; i< newDayDatabaseModelList.size(); i++) {
                    j = oldDayDatabaseModelList.indexOf(newDayDatabaseModelList.get(i));
                    if(j >= 0){
                        updateDayDAO(newDayDatabaseModelList.get(i), oldDayDatabaseModelList.get(j));
                    } else {
                        oldDayDatabaseModelList.add(newDayDatabaseModelList.get(i));
                    }
                }
            }
            else {
                oldDayDatabaseModelList = null;
            }
        }
        else {
            if ( newDayDatabaseModelList != null ) {
                oldDayDatabaseModelList = new ArrayList<DayDatabaseModel>();
                oldDayDatabaseModelList.addAll(newDayDatabaseModelList);
            }
        }
    }
    public void updateMonthDAOList(List<MonthDatabaseModel> newMonthDatabaseModelList, @MappingTarget List<MonthDatabaseModel> oldMonthDatabaseModelList) {
        Integer j;
        if (oldMonthDatabaseModelList != null ) {
            if (newMonthDatabaseModelList != null ) {
                for(int i = 0; i< newMonthDatabaseModelList.size(); i++) {
                    j = oldMonthDatabaseModelList.indexOf(newMonthDatabaseModelList.get(i));
                    if(j >= 0){
                        updateMonthDAO(newMonthDatabaseModelList.get(i), oldMonthDatabaseModelList.get(j));
                    } else {
                        oldMonthDatabaseModelList.add(newMonthDatabaseModelList.get(i));
                    }
                }
            }
            else {
                oldMonthDatabaseModelList = null;
            }
        }
        else {
            if ( newMonthDatabaseModelList != null ) {
                oldMonthDatabaseModelList = new ArrayList<MonthDatabaseModel>();
                oldMonthDatabaseModelList.addAll(newMonthDatabaseModelList);
            }
        }
    }
    public void updateYearDAOList(List<YearDatabaseModel> newYearDatabaseModelList, @MappingTarget List<YearDatabaseModel> oldYearDatabaseModelList) {
        Integer j;
        if (oldYearDatabaseModelList != null ) {
            if (newYearDatabaseModelList != null ) {
                for(int i = 0; i< newYearDatabaseModelList.size(); i++) {
                    j = oldYearDatabaseModelList.indexOf(newYearDatabaseModelList.get(i));
                    if(j >= 0){
                        updateYearDAO(newYearDatabaseModelList.get(i), oldYearDatabaseModelList.get(j));
                    } else {
                        oldYearDatabaseModelList.add(newYearDatabaseModelList.get(i));
                    }
                }
            }
            else {
                oldYearDatabaseModelList = null;
            }
        }
        else {
            if ( newYearDatabaseModelList != null ) {
                oldYearDatabaseModelList = new ArrayList<YearDatabaseModel>();
                oldYearDatabaseModelList.addAll(newYearDatabaseModelList);
            }
        }
    }
    public void updateDayWorkDAOList(List<DayWorkDatabaseModel> newDayWorkDatabaseModelList, @MappingTarget List<DayWorkDatabaseModel> oldDayWorkDatabaseModelList) {
        Integer j;
        if (oldDayWorkDatabaseModelList != null ) {
            if (newDayWorkDatabaseModelList != null ) {
                for(int i = 0; i< newDayWorkDatabaseModelList.size(); i++) {
                    j = oldDayWorkDatabaseModelList.indexOf(newDayWorkDatabaseModelList.get(i));
                    if(j >= 0){
                        newDayWorkDatabaseModelList.get(i).setDateOfWorkDay(oldDayWorkDatabaseModelList.get(j).getDateOfWorkDay());
                    } else {
                        oldDayWorkDatabaseModelList.add(newDayWorkDatabaseModelList.get(i));
                    }
                }
            }
            else {
                oldDayWorkDatabaseModelList = null;
            }
        }
        else {
            if ( newDayWorkDatabaseModelList != null ) {
                oldDayWorkDatabaseModelList = new ArrayList<DayWorkDatabaseModel>();
                oldDayWorkDatabaseModelList.addAll(newDayWorkDatabaseModelList);
            }
        }
    }
    public void updateDayWorkOutDAOList(List<DayWorkOutDatabaseModel> newDayWorkOutDatabaseModelList, @MappingTarget List<DayWorkOutDatabaseModel> oldDayWorkOutDatabaseModelList) {
        Integer j;
        if (oldDayWorkOutDatabaseModelList != null ) {
            if (newDayWorkOutDatabaseModelList != null ) {
                for(int i = 0; i< newDayWorkOutDatabaseModelList.size(); i++) {
                    j = oldDayWorkOutDatabaseModelList.indexOf(newDayWorkOutDatabaseModelList.get(i));
                    if(j >= 0){
                        newDayWorkOutDatabaseModelList.get(i).setDateOfWorkOutDay(oldDayWorkOutDatabaseModelList.get(j).getDateOfWorkOutDay());
                    } else {
                        oldDayWorkOutDatabaseModelList.add(newDayWorkOutDatabaseModelList.get(i));
                    }
                }
            }
            else {
                oldDayWorkOutDatabaseModelList = null;
            }
        }
        else {
            if ( newDayWorkOutDatabaseModelList != null ) {
                oldDayWorkOutDatabaseModelList = new ArrayList<DayWorkOutDatabaseModel>();
                oldDayWorkOutDatabaseModelList.addAll(newDayWorkOutDatabaseModelList);
            }
        }
    }
    @AfterMapping
    public void updateAnchorDay(@MappingTarget CalendarDatabaseModel calendarDatabaseModel){
        for(int i = 0; i< calendarDatabaseModel.getDayList().size(); i++) {
            System.out.println(calendarDatabaseModel.getDayList().get(i).getDayName() + i);
            if(calendarDatabaseModel.getAnchorWeekDay().equals(calendarDatabaseModel.getDayList().get(i))) {
                calendarDatabaseModel.setAnchorWeekDay(calendarDatabaseModel.getDayList().get(i));
                break;
            }
        }
    }
}
