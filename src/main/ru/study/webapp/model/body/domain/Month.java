package ru.study.webapp.model.body.domain;

import lombok.AccessLevel;
import lombok.Getter;
import ru.study.webapp.model.body.services.WeekService;
import ru.study.webapp.model.configuration.domain.DayTemplate;
import ru.study.webapp.model.configuration.domain.MonthTemplate;
import ru.study.webapp.model.configuration.domain.WeekTemplate;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
public class Month {
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
    Month(MonthTemplate month, DayTemplate weekDay, WeekTemplate weekConfig) {
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
    private void fillDaysList(MonthTemplate month, DayTemplate weekDay, WeekTemplate weekConfig) {
        DayTemplate day = weekDay;
        List<Integer> dayWorkOutList = month.getDayWorkOutList();
        List<Integer> dayWorkList = month.getDayWorkList();
        for (int date = 0; date < dayQuantity; date++) {
            day = WeekService.getOffsetDayFrom(day, 1, weekConfig);
            arrayOfDays.add(new Day(day.getDayName(),
                    (day.getWeekDayWorkOut() && !dayWorkList.contains(date + 1)) || dayWorkOutList.contains(date + 1)));
        }
    }

    /**
     * По номеру дня выдает объект типа день
     *
     * @param date Номер дня в месяце
     * @return Объект дня
     */
    public Day getDayByNumberInMonth(int date) {
        return this.arrayOfDays.get(date);
    }
}
