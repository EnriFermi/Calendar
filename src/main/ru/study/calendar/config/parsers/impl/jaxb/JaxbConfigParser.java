package ru.study.calendar.config.parsers.impl.jaxb;

import ru.study.calendar.config.body.impl.*;
import ru.study.calendar.config.body.inter.parsing.ICalendarTemplateForParsing;
import ru.study.calendar.config.body.inter.parsing.IDayTemplateForParsing;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.errors.errorTypes.ConfigurationException;
import ru.study.calendar.errors.errorTypes.JaxbParsingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbConfigParser implements ConfigParser {
    public JaxbConfigParser(){

    }
    private ICalendarTemplateForParsing mapper(JaxbCalendarConfig calendarConfig) throws JaxbParsingException {
        ICalendarTemplateForParsing calendarTemplate = new CalendarTemplate();
        IDayTemplateForParsing dayTemplate = new DayTemplate();

        dayTemplate.setDayName(calendarConfig.getJaxbDayConfig().getDayName());
        dayTemplate.setWeekDayWorkOut(calendarConfig.getJaxbDayConfig().getWeekDayWorkOut());
        calendarTemplate.setAnchorWeekDay(dayTemplate);
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
                    for(Integer weekDayWorkDate: monthConfig.getDayWorkList().getDateOfWorkDay()) {
                        monthTemplate.addWorkDay(weekDayWorkDate);
                    }
                    for(Integer weekDayWorkOutDate: monthConfig.getDayWorkOutList().getDateOfWorkOutDay()) {
                        monthTemplate.addWorkDay(weekDayWorkOutDate);
                    }
                    yearTemplate.addMonth(monthTemplate);
                    monthTemplate.resetMonth();
                } catch (ConfigurationException e) {
                    throw new JaxbParsingException(e);
                }
            }
            calendarTemplate.addYear(yearTemplate);
            yearTemplate.resetYearTemplate();
        }
        WeekTemplate weekTemplate = new WeekTemplate();
        for(JaxbDayConfig weekDayConfig: calendarConfig.getJaxbWeek().getJaxbWeekDayNameList().getJaxbWeekDayConfigList()) {
            try {
                dayTemplate.setDayName(weekDayConfig.getDayName());
                dayTemplate.setWeekDayWorkOut(weekDayConfig.getWeekDayWorkOut());
                weekTemplate.addWeekDay(dayTemplate);
                dayTemplate.resetDay();
            } catch (ConfigurationException e) {
                throw new JaxbParsingException(e);
            }
        }
        calendarTemplate.setWeek(weekTemplate);
        return calendarTemplate;
    }
    @Override
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException {
        JaxbCalendarConfig jaxbCalendarConfig;
        File file = new File(configPath);
        try {
            JAXBContext context = JAXBContext.newInstance(JaxbCalendarConfig.class);
            jaxbCalendarConfig = (JaxbCalendarConfig) context.createUnmarshaller().unmarshal(file);
            return mapper(jaxbCalendarConfig);
        } catch (JAXBException e) {
            throw new JaxbParsingException(e);
        }
    }
}
