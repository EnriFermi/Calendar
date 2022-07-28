package ru.study.calendar.item.impl;

import ru.study.calendar.config.ICalendarConfig;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.impl.json.JsonCalendarConfig;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.IDay;

import java.util.ArrayList;
import java.util.List;

public class Calendar implements ICalendar {
    private final List<Month> arrayOfMonth;
    /**
     * Номер года в цикле
     */
    private final Integer indexOfYearInConfig;
    private final ICalendarConfig calendarConfig;

    //-------------------------------------
    public Calendar(int numberYear) {
        this.calendarConfig = new JsonCalendarConfig("config\\classicCalendar.json");
        //TODO почитать про static классы DONE (пока что конфиги не переведены в static)
        this.arrayOfMonth = new ArrayList<>();
        /**
         * anchor - Номер дня недели 1 числа i месяца года, по которому создается календарь
         * weekSize - Количество дней недели
         */
        this.indexOfYearInConfig = (numberYear - 1) % calendarConfig.getYearList().size();
        fillMonthList(numberYear);
    }

    private void fillMonthList(int numberYear) {
        Integer anchor = getIndexOfFirstDay(numberYear, calendarConfig);
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        IMonthTemplate month;
        IDayTemplate day;
        //TODO вынести в пермененную calendarConfig.getYearList().get(indexOfYearInConfig)
        for (int i = 0; i < calendarConfig.getYearList().get(indexOfYearInConfig).getNumberOfMonth(); i++) {
            month = calendarConfig.getYearList().get(indexOfYearInConfig).getMonthList().get(i);
            day = calendarConfig.getWeek().weekDayNameList().get(anchor);
            arrayOfMonth.add(new Month(month, day, calendarConfig));
            anchor = (anchor + month.getDayCount()) % weekSize;
        }
    }

    /**
     * Возвращает день недели первого месяца года под номером numberYear
     *
     * @param numberYear     - Номер года
     * @param calendarConfig - Конфиг календаря
     * @return
     */
    private Integer getIndexOfFirstDay(Integer numberYear, ICalendarConfig calendarConfig) {
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
        //TODO почитать про streamapi и перевести на него
        for (int iterator = 1; iterator < number; iterator++) {
            anchor = (anchor + calendarConfig.getYearList().get(iterator % yearListSize).getDayQuantity()) % weekSize;
        }
        return anchor;
    }

    //TODO рефактор +  ругаться если вышли за предел списка
    public IDay getWeekDay(Integer intDay, String stringMonth) {
        return arrayOfMonth.get(calendarConfig.getYearList().get(indexOfYearInConfig)
                                                   .getIndexOfMonthByName(stringMonth)).getDay(intDay - 1);
    }
}