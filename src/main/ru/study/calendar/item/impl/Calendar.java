package ru.study.calendar.item.impl;

import lombok.AccessLevel;
import lombok.Getter;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.domain.DayTemplate;
import ru.study.calendar.config.domain.MonthTemplate;
import ru.study.calendar.config.domain.YearTemplate;
import ru.study.calendar.exceptions.OutOfBoundException;
import ru.study.calendar.item.ICalendar;

import java.util.ArrayList;
import java.util.List;
@Getter(AccessLevel.PACKAGE)
public class Calendar implements ICalendar {
    /**
     * Список месяцев в календаре
     */
    private final List<Month> arrayOfMonth;

    /**
     * Номер года в цикле
     */
    private final Integer dayQuantity;
    //private final Integer indexOfYearInConfig;
    /**
     * Конфиг календаря
     */
    //private final CalendarTemplate calendarConfig;

    //-------------------------------------

    /**
     * Конструктор календаря по номеру года из допустимого интервала
     *
     * @param numberYear Номер года
     * @throws Exception
     */
    public Calendar(int numberYear, CalendarTemplate calendarConfig) throws Exception {
        /*
         * Проверка, что введенный номер года соответствует допустимому диапазону
         */
        checkForValid(numberYear, calendarConfig);
        this.arrayOfMonth = new ArrayList<>();
        /*
         * anchor - Номер дня недели 1 числа i месяца года, по которому создается календарь
         * weekSize - Количество дней недели
         */
        this.dayQuantity = fillMonthList(numberYear, calendarConfig);
    }

    /**
     * Проверяет на корректность введенный номер года (должен попадать в указанный в конфиге интервал)
     *
     * @param numberYear Проверяемый номер года
     * @throws Exception
     */
    private void checkForValid(int numberYear, CalendarTemplate calendarConfig) throws OutOfBoundException {
        if ((calendarConfig.getBeginningYear() > numberYear) && (calendarConfig.getEndYear() < numberYear)) {
            throw new OutOfBoundException("Номер года вне границ разрешенного диапазона");
        }
    }

    /**
     * Заполняет список месяцев, получая данные из конфига
     *
     * @param numberYear Номер года
     * @throws Exception
     */
    private Integer fillMonthList(int numberYear, CalendarTemplate calendarConfig) {
        Integer indexOfYearInConfig = (numberYear - 1) % calendarConfig.getYearList().size();
        DayTemplate day = getIndexOfFirstDay(numberYear, calendarConfig);
        YearTemplate year = calendarConfig.getYearList().get(indexOfYearInConfig);
        MonthTemplate month;
        for (int i = 0; i < year.getMonthList().size(); i++) {
            month = year.getMonthList().get(i);
            this.arrayOfMonth.add(new Month(month, day, calendarConfig.getWeek()));
            day = WeekService.getOffsetDayFrom(day, month.getDayCount(), calendarConfig.getWeek());
        }
        return year.getDayQuantity();
    }

    /**
     * Возвращает день недели первого месяца года под номером numberYear
     *
     * @param numberYear     Номер года
     * @param calendarConfig Конфиг календаря
     * @return Шаблон первого дня первого месяца года под номером numberYear
     */
    private DayTemplate getIndexOfFirstDay(Integer numberYear, CalendarTemplate calendarConfig) {
        /*
         *  firstDayInFirstYear - День недели 1 числа 1 месяца 0 года
         *  yearCycleSize - Длина цикла годов
         *  weekSize - Длина недели
         *  differenceBetweenYear - Разница между годом привязки и введенным годом
         *  weekAndYearCycleSize - Разница в годах относительно цикла Год+Неделя
         *  number - Номер года в большом цикле лет
         */
        DayTemplate firstDayInFirstYear = calendarConfig.getAnchorWeekDay();
        Integer yearCycleSize = calendarConfig.getYearList().size();
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        Integer differenceBetweenYear = numberYear - calendarConfig.getBeginningYear();
        /*
         * Отвечает за то, какое количество раз нужно изменить firstDayInFirstDay, чтобы получить день недели года под номером numberYear
         */
        Integer shiftBetweenYears = differenceBetweenYear % (weekSize * yearCycleSize);
        /*
         * Отвечает за то, чтобы можно было с любого года начинать, не только с того, который знаменует начало цикла лет
         * Сначала же создается последовательность ... 0 1 2 3 0 1 2 3 ...
         * Так вот begin показывает с какого числа должна начинаться данная последовательность
         * 0 1 2 3 0 ...
         * 1 2 3 0 1 ...
         * 2 3 0 1 2 ...
         * 3 0 1 2 3 ...
         */
        Integer begin = calendarConfig.getBeginningYear() % yearCycleSize;

        for (int iterator = 1; iterator < shiftBetweenYears; iterator++) {
            firstDayInFirstYear = WeekService.getOffsetDayFrom(firstDayInFirstYear,
                    calendarConfig.getYearList().get((iterator + begin) % yearCycleSize)
                                  .getDayQuantity() % weekSize, calendarConfig.getWeek());
        }
        return firstDayInFirstYear;
    }
}