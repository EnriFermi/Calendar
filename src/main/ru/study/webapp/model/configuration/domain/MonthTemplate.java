package ru.study.webapp.model.configuration.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.study.webapp.exceptions.model.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MonthTemplate {
    /**
     * Имя месяца
     */
    @Setter(AccessLevel.PUBLIC)
    @EqualsAndHashCode.Include
    private String name;
    /**
     * Количество дней в месяце
     */
    @Setter(AccessLevel.PUBLIC)
    private Integer dayCount;

    public void setDayWorkOutList(List<Integer> dayWorkOutList) throws ConfigurationException {
        this.dayWorkOutList.clear();
        for (Integer day : dayWorkOutList) {
            addWorkOutDay(day);
        }
    }
    public void setDayWorkList(List<Integer> dayWorkList) throws ConfigurationException {
        this.dayWorkList.clear();
        for (Integer day : dayWorkList) {
            addWorkDay(day);
        }
    }

    /**
     * Список дополнительных нерабочих дней
     */
    private List<Integer> dayWorkOutList;
    /**
     * Список дополнительных рабочих дней
     */
    private List<Integer> dayWorkList;
    public MonthTemplate() {
        dayWorkOutList = new ArrayList<>();
        dayWorkList = new ArrayList<>();
    }
    /**
     * Добавляет номер дополнительного рабочего дня
     *
     * @param date Дата дополнительного рабочего дня
     */
    public void addWorkDay(Integer date) throws ConfigurationException {
        if(date > dayCount) {
            throw new ConfigurationException("Номер дополнительного рабочего дня за границей допустимых значений");
        }
        if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
            throw new ConfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
        }
        dayWorkList.add(date);
    }
    /**
     * Добавляет номер дополнительного нерабочего дня
     *
     * @param date Дата дополнительного нерабочего дня
     */
    public void addWorkOutDay(Integer date) throws ConfigurationException {
        if(date > dayCount) {
            throw new ConfigurationException("Номер дополнительного нерабочего дня за границей допустимых значений");
        }
        if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
            throw new ConfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
        }
        dayWorkOutList.add(date);
    }
}
