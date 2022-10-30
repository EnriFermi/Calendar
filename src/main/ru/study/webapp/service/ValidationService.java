package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.model.database.*;
// FIXME красиво оформить
public class ValidationService {
    private static final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public static void isValidCalendarDatabaseModel(CalendarDatabaseModel calendarDatabaseModel){
        try{
            mapper.calendarDatabaseModelToCalendarTemplate(calendarDatabaseModel);
        } catch(ConfigurationException exception){
            throw new ConfigurationException(exception);
        }
    }
    public static void isValidYearDatabaseModel(YearDatabaseModel YearDatabaseModel){
        try{
            mapper.yearDatabaseModelToYearTemplate(YearDatabaseModel);
        } catch(ConfigurationException exception){
            throw new ConfigurationException(exception);
        }
    }
    public static void isValidMonthDatabaseModel(MonthDatabaseModel MonthDatabaseModel){
        try{
            mapper.monthDatabaseModelToMonthTemplate(MonthDatabaseModel);
        } catch(ConfigurationException exception){
            throw new ConfigurationException(exception);
        }
    }
    public static void isValidDayDatabaseModel(DayDatabaseModel DayDatabaseModel){
        try{
            mapper.dayDatabaseModelToDayTemplate(DayDatabaseModel);
        } catch(ConfigurationException exception){
            throw new ConfigurationException(exception);
        }
    }
    public static void isValidDayWorkDatabaseModel(DayWorkDatabaseModel DayWorkDatabaseModel){
        try{
            mapper.dayWorkDatabaseModelToInteger(DayWorkDatabaseModel);
        } catch(ConfigurationException exception){
            throw new ConfigurationException(exception);
        }
    }
    public static void isValidDayWorkOutDatabaseModel(DayWorkOutDatabaseModel DayWorkOutDatabaseModel){
        try{
            mapper.dayWorkOutDatabaseModelToInteger(DayWorkOutDatabaseModel);
        } catch(ConfigurationException exception){
            throw new ConfigurationException(exception);
        }
    }
}
