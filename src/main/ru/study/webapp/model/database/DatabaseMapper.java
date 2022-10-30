package ru.study.webapp.model.database;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.study.webapp.model.configuration.domain.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {WeekTemplate.class})

public abstract class DatabaseMapper {

    @Mapping(target = "anchorWeekDay", source = "anchorWeekDay")
    @Mapping(target = "yearList", source = "yearList")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", expression = "java(new WeekTemplate(calendarConfig.getDayList()" +
            ".stream().map(day -> dayDatabaseModelToDayTemplate(day)).toList()))")
    public abstract CalendarTemplate calendarDatabaseModelToCalendarTemplate(CalendarDatabaseModel calendarConfig);
    @Mapping(target = "monthList", source = "monthList")
    @Mapping(target = "dayQuantity", ignore = true)
    public abstract YearTemplate yearDatabaseModelToYearTemplate(YearDatabaseModel yearConfig);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList")
    @Mapping(target = "dayWorkList", source = "dayWorkList")
    public abstract MonthTemplate monthDatabaseModelToMonthTemplate(MonthDatabaseModel monthConfig);

    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayTemplate dayDatabaseModelToDayTemplate(DayDatabaseModel dayConfig);

    public Integer dayWorkOutDatabaseModelToInteger(DayWorkOutDatabaseModel dayWorkOutDAO){
        return dayWorkOutDAO.getDateOfWorkOutDay();
    }
    public Integer dayWorkDatabaseModelToInteger(DayWorkDatabaseModel dayWorkDAO){
        return dayWorkDAO.getDateOfWorkDay();
    }

//---------------------------
    @Mapping(target = "anchorWeekDay", ignore = true)
    @Mapping(target = "yearList", ignore = true)
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "dayList", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract CalendarDatabaseModel calendarTemplateToCalendarDatabaseModel(CalendarTemplate calendarConfig);
    @Mapping(target = "monthList", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarDatabaseModel", ignore = true)
    public abstract YearDatabaseModel yearTemplateToYearDatabaseModel(YearTemplate yearConfig);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "yearDatabaseModel", ignore = true)
    @Mapping(target = "dayWorkOutList", ignore = true)
    @Mapping(target = "dayWorkList", ignore = true)
    public abstract MonthDatabaseModel monthTemplateToMonthDatabaseModel(MonthTemplate monthConfig);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarDatabaseModel", ignore = true)
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayDatabaseModel dayTemplateToDayDatabaseModel(DayTemplate dayConfig);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfWorkOutDay",source = "dayWorkOut")
    @Mapping(target = "monthDatabaseModel", ignore = true)
    public abstract DayWorkOutDatabaseModel integerToDayWorkOutDatabaseModel(Integer dayWorkOut);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfWorkDay",source = "dayWork")
    @Mapping(target = "monthDatabaseModel", ignore = true)
    public abstract DayWorkDatabaseModel integerDayWorkDatabaseModel(DayWorkDatabaseModel dayWork);
    /*
    public abstract class DatabaseMapperDecorator extends DatabaseMapper{
        private final DatabaseMapper delegate;
        public DatabaseMapperDecorator(DatabaseMapper delegate) {
            this.delegate = delegate;
        }
        @Override
        public CalendarDatabaseModel calendarTemplateToCalendarDatabaseModel(CalendarTemplate calendarConfig){
            CalendarDatabaseModel calendarDatabaseModel = delegate
                    .calendarTemplateToCalendarDatabaseModel(calendarConfig);
            for(YearDatabaseModel year: calendarDatabaseModel.getYearList()){
                year.setCalendarDatabaseModel(calendarDatabaseModel);
            }
            for(DayDatabaseModel day: calendarDatabaseModel.getDayList()){
                day.setCalendarDatabaseModel(calendarDatabaseModel);
            }

        }

        @Override
        public YearDatabaseModel yearTemplateToYearDatabaseModel(YearTemplate yearConfig){

        }
        @Override
        public MonthDatabaseModel monthTemplateToMonthDatabaseModel(MonthTemplate monthConfig){

        }

    }

     */
// --------------------------

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "anchorWeekDay", expression = "java(newCalendar.getAnchorWeekDay())", ignore = true)
    @Mapping(target = "yearList", source = "yearList", ignore = true)
    @Mapping(target = "beginningYear", source = "beginningYear", ignore = false)
    @Mapping(target = "endYear", source = "endYear", ignore = false)
    @Mapping(target = "dayList", source = "dayList", ignore = true)
    public abstract void updateCalendarDatabaseModel(CalendarDatabaseModel newCalendar, @MappingTarget CalendarDatabaseModel oldCalendar);

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "calendarDatabaseModel", expression = "java(newYear.getCalendarDatabaseModel())", ignore = false)
    @Mapping(target = "monthList", source = "monthList", ignore = true)
    public abstract void updateYearDatabaseModel(YearDatabaseModel newYear, @MappingTarget YearDatabaseModel oldYear);

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "yearDatabaseModel", expression = "java(newMonth.getYearDatabaseModel())" , ignore = false)
    @Mapping(target = "name", source = "name", ignore = false)
    @Mapping(target = "dayCount", source = "dayCount", ignore = false)
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList", ignore = true)
    @Mapping(target = "dayWorkList", source = "dayWorkList", ignore = true)
    public abstract void updateMonthDatabaseModel(MonthDatabaseModel newMonth, @MappingTarget MonthDatabaseModel oldMonth);


    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "calendarDatabaseModel", expression = "java(newDay.getCalendarDatabaseModel())", ignore = false)
    @Mapping(target = "dayName", source = "dayName", ignore = false)
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut", ignore = false)
    public abstract void updateDayDatabaseModel(DayDatabaseModel newDay, @MappingTarget DayDatabaseModel oldDay);

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "dateOfWorkOutDay", source = "dateOfWorkOutDay", ignore = false)
    @Mapping(target = "monthDatabaseModel", expression = "java(newDayWorkOut.getMonthDatabaseModel())", ignore = false)
    public abstract void updateDayWorkOutDatabaseModel(DayWorkOutDatabaseModel newDayWorkOut,
                                                    @MappingTarget DayWorkOutDatabaseModel oldDayWorkOut);
    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "dateOfWorkDay", source = "dateOfWorkDay", ignore = false)
    @Mapping(target = "monthDatabaseModel", expression = "java(newDayWork.getMonthDatabaseModel())", ignore = false)
    public abstract void updateDayWorkDatabaseModel(DayWorkDatabaseModel newDayWork,
                                                    @MappingTarget DayWorkDatabaseModel oldDayWork);
    /*
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
        if(calendarDatabaseModel.getDayList()!=null){
            for(int i = 0; i< calendarDatabaseModel.getDayList().size(); i++) {
                System.out.println(calendarDatabaseModel.getDayList().get(i).getDayName() + i);
                if(calendarDatabaseModel.getAnchorWeekDay().equals(calendarDatabaseModel.getDayList().get(i))) {
                    calendarDatabaseModel.setAnchorWeekDay(calendarDatabaseModel.getDayList().get(i));
                    break;
                }
            }
        }
    }*/
}
