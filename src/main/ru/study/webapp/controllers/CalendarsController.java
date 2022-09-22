package ru.study.webapp.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.study.webapp.model.body.domain.Calendar;
import ru.study.webapp.model.configuration.domain.*;
import ru.study.webapp.model.configuration.factory.ConfigurationFactory;
import ru.study.webapp.model.configuration.factory.enums.ConfigTypeEnum;
import ru.study.webapp.model.configuration.services.converters.impl.ToDbConverter;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.OutOfBoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/calendar")
public class CalendarsController {
    @GetMapping()
    public String getCalendar(HttpServletRequest request, Model model){
        Integer year = Integer.valueOf(request.getParameter("year"));
        Calendar calendar;
        ConfigTypeEnum configTypeEnum = ConfigTypeEnum.valueOf(request.getParameter("configType"));
        try {
            File file = new ClassPathResource("/").getFile();
            String configPath = file.getAbsolutePath()+request.getParameter("configPath");
            CalendarTemplate calendarTemplate = ConfigurationFactory.getCalendarTemplate(configPath, configTypeEnum);;
            calendar = new Calendar(year, calendarTemplate);
            model.addAttribute("calendar",calendar);
        } catch (ConfigurationException | OutOfBoundException | IOException e) {
            model.addAttribute("exception", e);
            return "calendar/exception";
        }
        model.addAttribute("calendar",calendar);
        return "calendar";
    }
    @GetMapping("/request")
    public String initialRequest(Model model){
        model.addAttribute("request", new CalendarRequest());

        return "calendar/request";
    }
    @PostMapping()
    public String calendarRedirect(@ModelAttribute("request") CalendarRequest request, RedirectAttributes attributes) {
        attributes.addAttribute("year", request.getYear());
        attributes.addAttribute("configType", request.getConfigType());
        attributes.addAttribute("configPath", request.getConfigPath());
        return "redirect:/calendar";
    }
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------

    // WeekDay
    @GetMapping("/new/addWeekDay")
    public String weekDayList(Model model) {
        model.addAttribute("dayTemplate", day);
        return "calendar/newDay";
    }
    @PostMapping("/new/addWeekDay")
    public String weekDayRedirect(@ModelAttribute DayTemplate dayTemplate) {
        day = dayTemplate;
        try {
            calendar.getWeek().addWeekDay(day);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/calendar/new";
    }
    //Save
    @PostMapping("/new/savePage")
    public String saveConfiguration() {
        ToDbConverter converter = new ToDbConverter();
        try {
            File file = new ClassPathResource("/").getFile();
            String configPath = file.getAbsolutePath()+"\\serverConfiguration.xml";
            System.out.println(configPath);
            converter.convertFromTemplate(calendar, configPath);
        } catch (ConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
    //Calendar
    @GetMapping("/initialNew")
    public String initialCalendarTemplateReading(RedirectAttributes attributes) {
        calendar = new CalendarTemplate();
        calendar.setWeek(new WeekTemplate());
        return "redirect:/calendar/new";
    }
    //===================
    CalendarTemplate calendar;
    YearTemplate year;

    MonthTemplate month;
    DayTemplate day;
    @GetMapping("/new")
    public String calendarTemplateReading(Model model) {
        System.out.println(calendar.getBeginningYear());
        model.addAttribute("calendarTemplate", calendar);
        return "calendar/newCalendar";
    }

    @PostMapping("/new")
    public String calendarRedirect(@ModelAttribute CalendarTemplate calendarTemplate){
        calendarTemplate.setYearList(calendar.getYearList());
        calendarTemplate.setWeek(calendar.getWeek());
        calendar = calendarTemplate;
        return "redirect:/calendar/new";
    }

    //===================
    @PostMapping("/new/addYearInitializer")
    public String yearInitializerRedirect(){
        year = new YearTemplate();
        return "redirect:/calendar/new/addYear";
    }
    @PostMapping("/new/addWeekDayInitializer")
    public String weekDayInitializerRedirect(){
        day = new DayTemplate();
        return "redirect:/calendar/new/addWeekDay";
    }

    //Year
    @GetMapping("/new/addYear")
    public String yearList() {
        System.out.println("Year: " + calendar.getBeginningYear());
        return "calendar/newYear";
    }
    @PostMapping("/new/addMonthRedirect")
    public String yearRedirect(){
        month = new MonthTemplate();
        return "redirect:/calendar/new/addMonth";

    }
    @PostMapping("/new/calendarRedirect")
    public String calendarFromYearRedirect(){
        calendar.addYear(year);
        return "redirect:/calendar/new";
    }

    //Month
    @GetMapping("/new/addMonth")
    public String month(Model model) {
        System.out.println("Month: " + calendar.getBeginningYear());
        model.addAttribute("monthTemplate", month);
        return "calendar/newMonth";
    }

    @PostMapping("/new/addMonth")
    public String monthRedirect(@ModelAttribute MonthTemplate monthTemplate){
        month = monthTemplate;
        try {
            year.addMonth(month);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/calendar/new/addYear";
    }

}
