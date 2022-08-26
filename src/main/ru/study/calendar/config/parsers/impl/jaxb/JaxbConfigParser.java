package ru.study.calendar.config.parsers.impl.jaxb;

import org.mapstruct.factory.Mappers;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JaxbParsingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbConfigParser implements ConfigParser {
    /*
    private CalendarTemplate mapper(JaxbCalendarConfig calendarConfig) throws JaxbParsingException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        DayTemplate dayTemplate = new DayTemplate();

        //TODO mapstruct DONE

        dayTemplate.setDayName(calendarConfig.getJaxbDayConfig().getDayName());
        dayTemplate.setWeekDayWorkOut(calendarConfig.getJaxbDayConfig().getWeekDayWorkOut());
        DayTemplate anchorDay = new DayTemplate();
        anchorDay.clone(dayTemplate);
        calendarTemplate.setAnchorWeekDay(anchorDay);
        dayTemplate.resetDay();

        calendarTemplate.setBeginningYear(calendarConfig.getBeginningYear());
        calendarTemplate.setEndYear(calendarConfig.getEndYear());
        for (JaxbYearConfig yearConfig: calendarConfig.getJaxbYearList().getYearConfigList()) {
            YearTemplate yearTemplate = new YearTemplate();
            for(JaxbMonthConfig monthConfig:yearConfig.getMonthList().getJaxbMonthConfigList()) {
                try {
                    MonthTemplate monthTemplate = new MonthTemplate();
                    monthTemplate.setName(monthConfig.getMonthName());
                    monthTemplate.setDayCount(monthConfig.getDayCount());
                    if(monthConfig.getDayWorkList() != null){
                        for(Integer weekDayWorkDate: monthConfig.getDayWorkList().getDateOfWorkDay()) {
                            monthTemplate.addWorkDay(weekDayWorkDate);
                        }
                    }
                    if(monthConfig.getDayWorkOutList() != null){
                        for(Integer weekDayWorkOutDate: monthConfig.getDayWorkOutList().getDateOfWorkOutDay()) {
                            monthTemplate.addWorkDay(weekDayWorkOutDate);
                        }
                    }
                    MonthTemplate month = new MonthTemplate();
                    month.clone(monthTemplate);
                    yearTemplate.addMonth(month);
                    monthTemplate.resetMonth();
                } catch (ConfigurationException e) {
                    throw new JaxbParsingException(e);
                }
            }
            YearTemplate year = new YearTemplate();
            year.clone(yearTemplate);
            calendarTemplate.addYear(year);
            yearTemplate.resetYearTemplate();
        }
        WeekTemplate weekTemplate = new WeekTemplate();
        for(JaxbDayConfig weekDayConfig: calendarConfig.getJaxbWeek().getJaxbWeekDayNameList().getJaxbWeekDayConfigList()) {
            try {
                dayTemplate.setDayName(weekDayConfig.getDayName());
                dayTemplate.setWeekDayWorkOut(weekDayConfig.getWeekDayWorkOut());
                DayTemplate day = new DayTemplate();
                day.clone(dayTemplate);
                weekTemplate.addWeekDay(day);
                dayTemplate.resetDay();
            } catch (ConfigurationException e) {
                throw new JaxbParsingException(e);
            }
        }
        calendarTemplate.setWeek(weekTemplate);
        return calendarTemplate;
    }
    */
    @Override
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException {
        JaxbCalendarConfig jaxbCalendarConfig;
        File file = new File(configPath);
        try {
            JAXBContext context = JAXBContext.newInstance(JaxbCalendarConfig.class);
            jaxbCalendarConfig = (JaxbCalendarConfig) context.createUnmarshaller().unmarshal(file);
            CalendarConfigMapper mapper
                    = Mappers.getMapper(CalendarConfigMapper.class);
            return mapper.calendarMapper(jaxbCalendarConfig);
        } catch (JAXBException e) {
            throw new JaxbParsingException(e);
        }
    }
}
