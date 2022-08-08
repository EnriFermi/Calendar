package ru.study.calendar.item.impl;

import lombok.Getter;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.item.IDay;
import ru.study.calendar.item.IMonth;
import ru.study.calendar.service.WeekService;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Month implements IMonth {
    /**
     * Имя месяца
     */
    private String monthName;
    /**
     * Список дней месяца
     */
    private List<Day> arrayOfDays;
    /**
     * Количество дней в месяце
     */
    private int dayQuantity;

    /**
     * Конструктор месяца на основании шаблона месяца
     * @param month Шаблон месяца
     * @param weekDay День недели 1 числа месяца
     * @param weekConfig Конфиг календаря
     */
    Month(IMonthTemplate month, IDayTemplate weekDay, IWeekTemplate weekConfig) {
        this.monthName = month.getName();
        this.dayQuantity = month.getDayCount();
        this.arrayOfDays = new ArrayList<>();
        fillDaysList(month, weekDay, weekConfig);
    }

    /**
     * Заполняет список дней, получая данные из конфига
     * @param month Шаблон месяца
     * @param weekDay День недели 1 числа месяца
     * @param weekConfig Конфиг календаря
     */
    private void fillDaysList(IMonthTemplate month, IDayTemplate weekDay, IWeekTemplate weekConfig) {
        IDayTemplate day = weekDay;
        List<Integer> dayWorkOutList = month.getDayWorkOutList();
        List<Integer> dayWorkList = month.getDayWorkList();
        for(int date = 0; date < dayQuantity; date++) {
            day = WeekService.getOffsetDayFrom(day, date, weekConfig);
            arrayOfDays.add(new Day(day.getDayName(),
                    day.isDefaultDayWorkOut() && dayWorkList.contains(date) && ! dayWorkOutList.contains(date)));
        }
    }

    /**
     * По номеру дня выдает объект типа день
     * @param date Номер дня в месяце
     * @return Объект дня
     */
    public IDay getDayByNumberInMonth(int date) {
        //TODO 8 Подумать о поведении системы если не найдется дня или кривой входной параметр DONE (см. Calendar/getWeekDay)
        return this.arrayOfDays.get(date);
    }
}