package ru.study.calendar.item.impl;

import ru.study.calendar.exceptions.OutOfBoundException;
import ru.study.calendar.item.IDay;

public class CalendarService {
    /**
     * Конструктор календаря по номеру года из допустимого интервала
     *
     * @param numberYear     Номер года
     * @param calendarConfig
     * @throws Exception
     */
    //TODO утащить в сервис, возможно интерфейсы для хранения календаря не потребуются DONE

    /**
     * Выдает по номеру дня и названию месяца день недели
     * @param intDay Номер дня в месяце (начинается с 1)
     * @param stringMonth Название месяца
     * @return Объект дня
     * @throws Exception
     */
    public static IDay getWeekDay(Calendar calendar, Integer intDay, String stringMonth) throws Exception {
        calendar.getArrayOfMonth();
        Integer monthIndex = YearService.getIndexOfMonthByName(stringMonth, calendar);
        Integer dayQuantityInMonth = calendar.getArrayOfMonth().get(monthIndex).getDayQuantity();
        if ((intDay > dayQuantityInMonth) || (intDay < 1)) {
            throw new OutOfBoundException("Количество дней за границей допустимых значений");
        }
        return calendar.getArrayOfMonth().get(monthIndex).getDayByNumberInMonth(intDay - 1);
    };

    /**
     * Выдает количество дней в календаре
     * @return Количество дней в календаре
     */
    public static Integer getDayInYearCount(Calendar calendar) {
        return calendar.getDayQuantity();
    }

}
