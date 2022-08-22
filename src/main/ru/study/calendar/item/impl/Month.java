package ru.study.calendar.item.impl;

import lombok.Getter;
import ru.study.calendar.config.body.inter.reading.IDayTemplateForReading;
import ru.study.calendar.config.body.inter.reading.IMonthTemplateForReading;
import ru.study.calendar.config.body.inter.reading.IWeekTemplateForReading;
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
    private final String monthName;
    /**
     * Список дней месяца
     */
    private final List<Day> arrayOfDays;
    /**
     * Количество дней в месяце
     */
    private final int dayQuantity;

    /**
     * Конструктор месяца на основании шаблона месяца
     *
     * @param month      Шаблон месяца
     * @param weekDay    День недели 1 числа месяца
     * @param weekConfig Конфиг календаря
     */
    Month(IMonthTemplateForReading month, IDayTemplateForReading weekDay, IWeekTemplateForReading weekConfig) {
        this.monthName = month.getName();
        this.dayQuantity = month.getDayCount();
        this.arrayOfDays = new ArrayList<>();
        fillDaysList(month, weekDay, weekConfig);
    }

    /**
     * Заполняет список дней, получая данные из конфига
     *
     * @param month      Шаблон месяца
     * @param weekDay    День недели 1 числа месяца
     * @param weekConfig Конфиг календаря
     */
    private void fillDaysList(IMonthTemplateForReading month, IDayTemplateForReading weekDay, IWeekTemplateForReading weekConfig) {
        IDayTemplateForReading day = weekDay;
        List<Integer> dayWorkOutList = month.getDayWorkOutList();
        List<Integer> dayWorkList = month.getDayWorkList();
        for (int date = 0; date < dayQuantity; date++) {
            day = WeekService.getOffsetDayFrom(day, 1, weekConfig);
            arrayOfDays.add(new Day(day.getDayName(),
                    (day.isDefaultDayWorkOut() && !dayWorkList.contains(date + 1)) || dayWorkOutList.contains(date + 1)));
        }
    }

    /**
     * По номеру дня выдает объект типа день
     *
     * @param date Номер дня в месяце
     * @return Объект дня
     */
    public IDay getDayByNumberInMonth(int date) {
        return this.arrayOfDays.get(date);
    }
}
