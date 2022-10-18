package ru.study.webapp.model.body.services;

import ru.study.webapp.model.body.domain.Calendar;
import ru.study.webapp.model.body.domain.Day;
import ru.study.webapp.exceptions.OutOfBoundException;

public class CalendarService {
    /**
     * Выдает по номеру дня и названию месяца день недели
     *
     * @param calendar Объект календаря, для которого нудно получить день недели
     * @param intDay Номер дня в месяце (начинается с 1)
     * @param stringMonth Номер дня в месяце (начинается с 1)
     * @return  Объект дня
     * @throws OutOfBoundException
     */
    public static Day getWeekDay(Calendar calendar, Integer intDay, String stringMonth) throws OutOfBoundException  {
        calendar.getArrayOfMonth();
        Integer monthIndex = YearService.getIndexOfMonthByName(stringMonth, calendar);
        Integer dayQuantityInMonth = calendar.getArrayOfMonth().get(monthIndex).getDayQuantity();
        if ((intDay > dayQuantityInMonth) || (intDay < 1)) {
            throw new OutOfBoundException("Количество дней за границей допустимых значений");
        }
        return calendar.getArrayOfMonth().get(monthIndex).getDayByNumberInMonth(intDay - 1);
    }
}
