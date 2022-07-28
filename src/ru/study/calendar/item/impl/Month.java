package ru.study.calendar.item.impl;

import lombok.Getter;
import ru.study.calendar.config.ICalendarConfig;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.item.IDay;
import ru.study.calendar.item.IMonth;


import java.util.ArrayList;
import java.util.List;
@Getter
public class Month implements IMonth {
    private String monthName;
    private List<Day> arrayOfDays;
    private int dayQuantity;

    Month(IMonthTemplate month, IDayTemplate weekDay, ICalendarConfig calendarConfig) {
        this.monthName = month.getName();
        this.dayQuantity = month.getDayCount();
        this.arrayOfDays = new ArrayList<>();
        fillDaysList(month, weekDay, calendarConfig);
    }

    private void fillDaysList(IMonthTemplate month, IDayTemplate weekDay, ICalendarConfig calendarConfig) {
        Integer begin = calendarConfig.getWeek().getIndexOfDayByName(weekDay.getDayName());
        List<Integer> dayWorkOutList = month.getDayWorkOutList();
        List<Integer> dayWorkList = month.getDayWorkList();
        boolean isWorkDay;
        IDayTemplate day;
        for(int date = 0; date < dayQuantity; date++) {
            //TODO перевести на getOffsetDayFrom
            day = calendarConfig.getWeek().weekDayNameList().get((date + begin) % calendarConfig.getWeek().getWeekDayCount());
            //TODO меньшить число строк
            isWorkDay = day.isDefaultDayWorkOut();
            //TODO защита от дурака, что день есть и в списке рабочих и  выходных
            if(dayWorkOutList.contains(date)){
                isWorkDay = false;
            } else if (dayWorkList.contains(date)) {
                isWorkDay = true;
            }
            arrayOfDays.add(new Day(day.getDayName(), isWorkDay));
        }
    }

    public IDay getDay(int date) {
        //TODO 8 Подумать о поведении системы если не найдется дня или кривой входной параметр
        return arrayOfDays.get(date);
    }
}
