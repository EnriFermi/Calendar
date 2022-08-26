package ru.study.calendar.config.body.impl;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import ru.study.calendar.config.body.inter.reading.IDayTemplateForReading;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PUBLIC)
public class DayTemplate implements IDayTemplateForReading {
    /**
     * Конструктор дня недели по передаваемому JSON конфигу
     * @param  Объект JSON конфига, хранящий информацию о конкретном дне
     */


    /**
     * Название дня недели
     */
    @EqualsAndHashCode.Include
    private String dayName;
    /**
     * Указание рабочий ли день
     */
    private Boolean weekDayWorkOut;
    @Override
    public String getDayName() {
        return dayName;
    }
    /**
     * Метод получения информации рабочий ли день
     * @return Возвращает boolean значение рабочий ли день
     */
    @Override
    public Boolean isDefaultDayWorkOut() {
        return this.weekDayWorkOut;
    }

    public void resetDay() {
    }

    public void clone(DayTemplate dayConstructor) {
        dayName = dayConstructor.getDayName();
        weekDayWorkOut = dayConstructor.isDefaultDayWorkOut();
    }
}