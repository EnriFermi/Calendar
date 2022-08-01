package ru.study.calendar.item.impl;

import ru.study.calendar.config.ICalendarConfig;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.JsonCalendarConfig;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.IDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Calendar implements ICalendar {
    private final List<Month> arrayOfMonth;
    /**
     * Номер года в цикле
     */
    private final Integer indexOfYearInConfig;
    private final ICalendarConfig calendarConfig;

    //-------------------------------------
    public Calendar(int numberYear) throws Exception {
        this.calendarConfig = new JsonCalendarConfig("config\\classicCalendar.json");
        /**
         * Проверка, что введенный номер года соответствует допустимому диапазону
         */
        if (this.calendarConfig.getBeginningYear() > numberYear)
        {
            throw new Exception("NumberYear is below expected range");
        } else if (this.calendarConfig.getEndYear() < numberYear) {
            throw new Exception("NumberYear is above expected range");
        }
        //TODO почитать про static классы DONE (пока что конфиги не переведены в static)
        this.arrayOfMonth = new ArrayList<>();
        /**
         * anchor - Номер дня недели 1 числа i месяца года, по которому создается календарь
         * weekSize - Количество дней недели
         */
        this.indexOfYearInConfig = (numberYear - 1) % this.calendarConfig.getYearList().size();
        fillMonthList(numberYear);
    }

    private void fillMonthList(int numberYear) throws Exception {
        IDayTemplate day = getIndexOfFirstDay(numberYear, this.calendarConfig);
        Integer weekSize = this.calendarConfig.getWeek().getWeekDayCount();
        IYearTemplate year = this.calendarConfig.getYearList().get(this.indexOfYearInConfig);
        IMonthTemplate month;
        //TODO вынести в перемененную calendarConfig.getYearList().get(indexOfYearInConfig) DONE
        for (int i = 0; i < year.getNumberOfMonth(); i++) {
            month = year.getMonthList().get(i);
            this.arrayOfMonth.add(new Month(month, day, calendarConfig));
            day = this.calendarConfig.getWeek().getOffsetDayFrom(day, month.getDayCount());

        }
    }

    /**
     * Возвращает день недели первого месяца года под номером numberYear
     *
     * @param numberYear     - Номер года
     * @param calendarConfig - Конфиг календаря
     * @return
     */
    private IDayTemplate getIndexOfFirstDay(Integer numberYear, ICalendarConfig calendarConfig) throws Exception {
        /**
         *  anchor - Номер дня недели 1 числа 1 месяца 0 года
         *  yearListSize - Длина цикла годов
         *  weekSize - Длина недели
         *  number - Номер года в большом цикле лет
         */
        IDayTemplate anchorDay = calendarConfig.getAnchorWeekDay();
        Integer yearListSize = calendarConfig.getYearList().size();
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        Integer differenceBetweenYear = numberYear - calendarConfig.getBeginningYear();
        Integer number = differenceBetweenYear % (weekSize * yearListSize);
        Integer begin = calendarConfig.getBeginningYear()%yearListSize;
        anchorDay = calendarConfig.getWeek().getOffsetDayFrom(anchorDay, Stream.iterate(begin, iterator -> (iterator + 1) % yearListSize).limit(number)
                    .map(integer -> this.calendarConfig.getYearList().get(integer).getDayQuantity()).reduce(0,(sum, add) -> sum + add));

        //TODO почитать про stream api и перевести на него DONE
        /*
        for (int iterator = 1; iterator < number; iterator++) {
            anchor = (anchor + calendarConfig.getYearList().get(iterator % yearListSize).getDayQuantity()) % weekSize;
        }*/
        return anchorDay;
    }

    //TODO рефактор +  ругаться если вышли за предел списка DONE?
    public IDay getWeekDay(Integer intDay, String stringMonth) throws Exception {
        IYearTemplate year = this.calendarConfig.getYearList().get(this.indexOfYearInConfig);
        Integer monthIndex = year.getIndexOfMonthByName(stringMonth);
        Integer dayQuantity = year.getDayQuantity();
        if (intDay > dayQuantity) {
            throw new Exception("Quantity of days is out of range");
        }
        return this.arrayOfMonth.get(monthIndex).getDay(intDay - 1);
    }
}