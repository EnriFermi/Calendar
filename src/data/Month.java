package data;

import ret.*;


import java.util.ArrayList;
import java.util.List;
//TODO привести имена полей в порядок DONE
public class Month implements IMonth {
    Month(IMonthTemplate month, IDayTemplate weekDay, ICalendarConfig calendarConfig) {
        this.monthName = month.getName();
        this.dayQuantity = month.getDayCount();
        arrayOfDays = new ArrayList<Day>();
        Integer begin = calendarConfig.getWeek().getIndexOfDayByName(weekDay.getDayName());
        IDayTemplate day;
        List<Integer> dayWorkOutList = month.getDayWorkOutList();
        List<Integer> dayWorkList = month.getDayWorkList();
        boolean isWorkDay;
        for(int date = 0; date < dayQuantity; date++) {
            //TODO 9. подумать как уменьшить количество кода
            day = calendarConfig.getWeek().weekDayNameList().get((date + begin) % calendarConfig.getWeek().getWeekDayCount());
            isWorkDay = day.getWeekDayWorkOut();
            if(dayWorkOutList.contains(date)){
                isWorkDay = false;
            } else if (dayWorkOutList.contains(date)) {
                isWorkDay = true;
            }
            this.arrayOfDays.add(new Day(day.getDayName(), isWorkDay));
        }
    }
    private String monthName;
    private List<Day> arrayOfDays;
    private int dayQuantity; // количество дней в месяцев
    /*

    public String getMonthName() {
        return monthName;
    }
    public int getDayQuantity() {
        return dayQuantity;
    }*/
    public IDay getDay(int date) {
        //TODO 8 Подумать о поведении системы если не найдется дня или кривой входной параметр
        return arrayOfDays.get(date);
    }
}
