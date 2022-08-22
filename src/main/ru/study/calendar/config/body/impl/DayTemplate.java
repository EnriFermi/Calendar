package ru.study.calendar.config.body.impl;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.study.calendar.config.body.inter.parsing.IDayTemplateForParsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PUBLIC)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DayTemplate implements IDayTemplateForParsing {
    /**
     * Конструктор дня недели по передаваемому JSON конфигу
     * @param  Объект JSON конфига, хранящий информацию о конкретном дне
     */


    /**
     * Название дня недели
     */
    @EqualsAndHashCode.Include @Getter
    @XmlElement
    private String dayName;
    /**
     * Указание рабочий ли день
     */
    @XmlElement
    private Boolean weekDayWorkOut;
    public DayTemplate() {
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

    public void clone(IDayTemplateForParsing dayConstructor) {
        dayName = dayConstructor.getDayName();
        weekDayWorkOut = dayConstructor.isDefaultDayWorkOut();
    }
}