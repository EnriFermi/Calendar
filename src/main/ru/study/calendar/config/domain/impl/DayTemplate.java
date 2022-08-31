package ru.study.calendar.config.domain.impl;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PUBLIC)
public class DayTemplate {
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
    public String getDayName() {
        return dayName;
    }
    /**
     * Метод получения информации рабочий ли день
     * @return Возвращает boolean значение рабочий ли день
     */
    public Boolean isDefaultDayWorkOut() {
        return this.weekDayWorkOut;
    }


}