package ru.study.webapp.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.study.webapp.model.configuration.domain.*;
import ru.study.webapp.model.database.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {WeekTemplate.class})

public abstract class DatabaseMapper {

    @Mapping(target = "anchorWeekDay", source = "anchorWeekDay")
    @Mapping(target = "yearList", source = "yearList")
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "week", expression = "java(new WeekTemplate(calendarConfig.getDayList()" +
            ".stream().map(day -> dayEntityToDayTemplate(day)).toList()))")
    public abstract CalendarTemplate calendarEntityToCalendarTemplate(CalendarEntity calendarConfig);
    @Mapping(target = "monthList", source = "monthList")
    @Mapping(target = "dayQuantity", ignore = true)
    public abstract YearTemplate yearEntityToYearTemplate(YearEntity yearConfig);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList")
    @Mapping(target = "dayWorkList", source = "dayWorkList")
    public abstract MonthTemplate monthEntityToMonthTemplate(MonthEntity monthConfig);

    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayTemplate dayEntityToDayTemplate(DayEntity dayConfig);

    public Integer dayWorkOutEntityToInteger(DayWorkOutEntity dayWorkOutDAO){
        return dayWorkOutDAO.getDateOfWorkOutDay();
    }
    public Integer dayWorkEntityToInteger(DayWorkEntity dayWorkDAO){
        return dayWorkDAO.getDateOfWorkDay();
    }

//---------------------------
    @Mapping(target = "anchorWeekDay", ignore = true)
    @Mapping(target = "yearList", ignore = true)
    @Mapping(target = "beginningYear", source = "beginningYear")
    @Mapping(target = "endYear", source = "endYear")
    @Mapping(target = "dayList", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract CalendarEntity calendarTemplateToCalendarEntity(CalendarTemplate calendarConfig);
    @Mapping(target = "monthList", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarEntity", ignore = true)
    public abstract YearEntity yearTemplateToYearEntity(YearTemplate yearConfig);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "dayCount", source = "dayCount")
    @Mapping(target = "yearEntity", ignore = true)
    @Mapping(target = "dayWorkOutList", ignore = true)
    @Mapping(target = "dayWorkList", ignore = true)
    public abstract MonthEntity monthTemplateToMonthEntity(MonthTemplate monthConfig);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarEntity", ignore = true)
    @Mapping(target = "dayName", source = "dayName")
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut")
    public abstract DayEntity dayTemplateToDayEntity(DayTemplate dayConfig);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfWorkOutDay",source = "dayWorkOut")
    @Mapping(target = "monthEntity", ignore = true)
    public abstract DayWorkOutEntity integerToDayWorkOutEntity(Integer dayWorkOut);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfWorkDay",source = "dayWork")
    @Mapping(target = "monthEntity", ignore = true)
    public abstract DayWorkEntity integerDayWorkEntity(DayWorkEntity dayWork);
    /*
    public abstract class DatabaseMapperDecorator extends DatabaseDTOMapper{
        private final DatabaseDTOMapper delegate;
        public DatabaseMapperDecorator(DatabaseDTOMapper delegate) {
            this.delegate = delegate;
        }
        @Override
        public CalendarControllerDTO calendarTemplateToCalendarEntity(CalendarTemplate calendarConfig){
            CalendarControllerDTO calendarEntity = delegate
                    .calendarTemplateToCalendarEntity(calendarConfig);
            for(YearControllerDTO year: calendarEntity.getYearList()){
                year.setCalendarControllerDTO(calendarEntity);
            }
            for(DayControllerDTO day: calendarEntity.getDayList()){
                day.setCalendarControllerDTO(calendarEntity);
            }

        }

        @Override
        public YearControllerDTO yearTemplateToYearEntity(YearTemplate yearConfig){

        }
        @Override
        public MonthControllerDTO monthTemplateToMonthEntity(MonthTemplate monthConfig){

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
    public abstract void updateCalendarEntity(CalendarEntity newCalendar, @MappingTarget CalendarEntity oldCalendar);

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "calendarEntity", expression = "java(newYear.getCalendarEntity())", ignore = false)
    @Mapping(target = "monthList", source = "monthList", ignore = true)
    public abstract void updateYearEntity(YearEntity newYear, @MappingTarget YearEntity oldYear);

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "yearEntity", expression = "java(newMonth.getYearEntity())" , ignore = false)
    @Mapping(target = "name", source = "name", ignore = false)
    @Mapping(target = "dayCount", source = "dayCount", ignore = false)
    @Mapping(target = "dayWorkOutList", source = "dayWorkOutList", ignore = true)
    @Mapping(target = "dayWorkList", source = "dayWorkList", ignore = true)
    public abstract void updateMonthEntity(MonthEntity newMonth, @MappingTarget MonthEntity oldMonth);


    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "calendarEntity", expression = "java(newDay.getCalendarEntity())", ignore = false)
    @Mapping(target = "dayName", source = "dayName", ignore = false)
    @Mapping(target = "weekDayWorkOut", source = "weekDayWorkOut", ignore = false)
    public abstract void updateDayEntity(DayEntity newDay, @MappingTarget DayEntity oldDay);

    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "dateOfWorkOutDay", source = "dateOfWorkOutDay", ignore = false)
    @Mapping(target = "monthEntity", expression = "java(newDayWorkOut.getMonthEntity())", ignore = false)
    public abstract void updateDayWorkOutEntity(DayWorkOutEntity newDayWorkOut,
                                                       @MappingTarget DayWorkOutEntity oldDayWorkOut);
    @Mapping(target = "id", source = "id", ignore = false)
    @Mapping(target = "dateOfWorkDay", source = "dateOfWorkDay", ignore = false)
    @Mapping(target = "monthEntity", expression = "java(newDayWork.getMonthEntity())", ignore = false)
    public abstract void updateDayWorkEntity(DayWorkEntity newDayWork,
                                                    @MappingTarget DayWorkEntity oldDayWork);
    /*
    public void updateDayDAOList(List<DayControllerDTO> newDayEntityList, @MappingTarget List<DayControllerDTO> oldDayEntityList) {
        Integer j;
        if (oldDayEntityList != null ) {
            if (newDayEntityList != null ) {
                for(int i = 0; i< newDayEntityList.size(); i++) {
                    j = oldDayEntityList.indexOf(newDayEntityList.get(i));
                    if(j >= 0){
                        updateDayDAO(newDayEntityList.get(i), oldDayEntityList.get(j));
                    } else {
                        oldDayEntityList.add(newDayEntityList.get(i));
                    }
                }
            }
            else {
                oldDayEntityList = null;
            }
        }
        else {
            if ( newDayEntityList != null ) {
                oldDayEntityList = new ArrayList<DayControllerDTO>();
                oldDayEntityList.addAll(newDayEntityList);
            }
        }
    }
    public void updateMonthDAOList(List<MonthControllerDTO> newMonthEntityList, @MappingTarget List<MonthControllerDTO> oldMonthEntityList) {
        Integer j;
        if (oldMonthEntityList != null ) {
            if (newMonthEntityList != null ) {
                for(int i = 0; i< newMonthEntityList.size(); i++) {
                    j = oldMonthEntityList.indexOf(newMonthEntityList.get(i));
                    if(j >= 0){
                        updateMonthDAO(newMonthEntityList.get(i), oldMonthEntityList.get(j));
                    } else {
                        oldMonthEntityList.add(newMonthEntityList.get(i));
                    }
                }
            }
            else {
                oldMonthEntityList = null;
            }
        }
        else {
            if ( newMonthEntityList != null ) {
                oldMonthEntityList = new ArrayList<MonthControllerDTO>();
                oldMonthEntityList.addAll(newMonthEntityList);
            }
        }
    }
    public void updateYearDAOList(List<YearControllerDTO> newYearEntityList, @MappingTarget List<YearControllerDTO> oldYearEntityList) {
        Integer j;
        if (oldYearEntityList != null ) {
            if (newYearEntityList != null ) {
                for(int i = 0; i< newYearEntityList.size(); i++) {
                    j = oldYearEntityList.indexOf(newYearEntityList.get(i));
                    if(j >= 0){
                        updateYearDAO(newYearEntityList.get(i), oldYearEntityList.get(j));
                    } else {
                        oldYearEntityList.add(newYearEntityList.get(i));
                    }
                }
            }
            else {
                oldYearEntityList = null;
            }
        }
        else {
            if ( newYearEntityList != null ) {
                oldYearEntityList = new ArrayList<YearControllerDTO>();
                oldYearEntityList.addAll(newYearEntityList);
            }
        }
    }
    public void updateDayWorkDAOList(List<DayWorkControllerDTO> newDayWorkEntityList, @MappingTarget List<DayWorkControllerDTO> oldDayWorkEntityList) {
        Integer j;
        if (oldDayWorkEntityList != null ) {
            if (newDayWorkEntityList != null ) {
                for(int i = 0; i< newDayWorkEntityList.size(); i++) {
                    j = oldDayWorkEntityList.indexOf(newDayWorkEntityList.get(i));
                    if(j >= 0){
                        newDayWorkEntityList.get(i).setDateOfWorkDay(oldDayWorkEntityList.get(j).getDateOfWorkDay());
                    } else {
                        oldDayWorkEntityList.add(newDayWorkEntityList.get(i));
                    }
                }
            }
            else {
                oldDayWorkEntityList = null;
            }
        }
        else {
            if ( newDayWorkEntityList != null ) {
                oldDayWorkEntityList = new ArrayList<DayWorkControllerDTO>();
                oldDayWorkEntityList.addAll(newDayWorkEntityList);
            }
        }
    }
    public void updateDayWorkOutDAOList(List<DayWorkOutControllerDTO> newDayWorkOutEntityList, @MappingTarget List<DayWorkOutControllerDTO> oldDayWorkOutEntityList) {
        Integer j;
        if (oldDayWorkOutEntityList != null ) {
            if (newDayWorkOutEntityList != null ) {
                for(int i = 0; i< newDayWorkOutEntityList.size(); i++) {
                    j = oldDayWorkOutEntityList.indexOf(newDayWorkOutEntityList.get(i));
                    if(j >= 0){
                        newDayWorkOutEntityList.get(i).setDateOfWorkOutDay(oldDayWorkOutEntityList.get(j).getDateOfWorkOutDay());
                    } else {
                        oldDayWorkOutEntityList.add(newDayWorkOutEntityList.get(i));
                    }
                }
            }
            else {
                oldDayWorkOutEntityList = null;
            }
        }
        else {
            if ( newDayWorkOutEntityList != null ) {
                oldDayWorkOutEntityList = new ArrayList<DayWorkOutControllerDTO>();
                oldDayWorkOutEntityList.addAll(newDayWorkOutEntityList);
            }
        }
    }
    @AfterMapping
    public void updateAnchorDay(@MappingTarget CalendarControllerDTO calendarEntity){
        if(calendarEntity.getDayList()!=null){
            for(int i = 0; i< calendarEntity.getDayList().size(); i++) {
                System.out.println(calendarEntity.getDayList().get(i).getDayName() + i);
                if(calendarEntity.getAnchorWeekDay().equals(calendarEntity.getDayList().get(i))) {
                    calendarEntity.setAnchorWeekDay(calendarEntity.getDayList().get(i));
                    break;
                }
            }
        }
    }*/
}
