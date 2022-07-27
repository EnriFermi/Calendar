package data;

import ret.*;
import template.CalendarConfig;

import java.util.ArrayList;
import java.util.List;

public class Calendar implements ICalendar {
    private List<Month> arrayOfMonth;
    /**
     * indexOfYearInConfig - Номер года в цикле
     */
    private Integer indexOfYearInConfig;
    private ICalendarConfig calendarConfig;
    //-------------------------------------
    public Calendar(int numberYear) {
        ICalendarConfig calendarConfig = new CalendarConfig("config\\classicCalendar.json");
        this.calendarConfig = calendarConfig;
        //TODO почитать про static классы DONE (пока что конфиги не переведены в static)
        this.arrayOfMonth = new ArrayList<Month>();
        //TODO  подумать как схлопнуть в 1 цикл с циклом из template.YearTemplate DONE
        /**
         * index - Номер года в цикле годов. Нужен для последующего обращения к нужному году в конфиге
         * anchor - Номер дня недели 1 числа i месяца года, по которому создается календарь
         * weekSize - Количество дней недели
         */
        Integer index = (numberYear-1) % calendarConfig.getYearList().size();
        this.indexOfYearInConfig = index;
        Integer anchor = getIndexOfFirstDay(numberYear, calendarConfig);
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        IMonthTemplate month;
        IDayTemplate day;
        for (int i=0; i<calendarConfig.getYearList().get(index).getNumberOfMonth(); i++) {
            month = calendarConfig.getYearList().get(index).getMonthList().get(i);
            day = calendarConfig.getWeek().weekDayNameList().get(anchor);
            this.arrayOfMonth.add(new Month(month, day, calendarConfig));
            anchor = (anchor + month.getDayCount()) % weekSize;
        }
    }

    /**
     * Возвращает день недели первого месяца года под номером numberYear
     * @param numberYear - Номер года
     * @param calendarConfig - Конфиг календаря
     * @return
     */
    private Integer getIndexOfFirstDay(Integer numberYear, ICalendarConfig calendarConfig){
        /**
         *  anchor - Номер дня недели 1 числа 1 месяца 0 года
         *  yearListSize - Длина цикла годов
         *  weekSize - Длина недели
         *  number - Номер года в большом цикле лет
         */
        Integer anchor = calendarConfig.getWeek().getIndexOfDayByName(calendarConfig.getAnchorWeekDay());
        Integer yearListSize = calendarConfig.getYearList().size();
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        Integer differenceBetweenYear = numberYear - calendarConfig.getAnchorYear();
        Integer number = differenceBetweenYear % (weekSize * yearListSize);
        for (int iterator = 1; iterator<number; iterator++){
            anchor = (anchor + calendarConfig.getYearList().get(iterator % yearListSize).getDayQuantity()) % weekSize;
        }
        return anchor;
    }

    public IDay getWeekDay(Integer intDay, String stringMonth) {
        return this.arrayOfMonth.get(calendarConfig.getYearList().get(indexOfYearInConfig).getIndexOfMonthByName(stringMonth)).getDay(intDay-1);
    }
}