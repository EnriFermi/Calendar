package ru.study.calendar.config.domain.impl;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import ru.study.calendar.exceptions.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

@Setter(value = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MonthTemplate {
    /**
     * Имя месяца
     */
    @EqualsAndHashCode.Include
    private String name;
    /**
     * Количество дней в месяце
     */
    private Integer dayCount;
    /**
     * Список дополнительных нерабочих дней
     */
    private List<Integer> dayWorkOutList;
    /**
     * Список дополнительных рабочих дней
     */
    private List<Integer> dayWorkList;

    /**
     * Конструктор месяца по передаваемому JSON конфигу
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     * @throws Exception
     */
    public MonthTemplate() {
        dayWorkOutList = new ArrayList<>();
        dayWorkList = new ArrayList<>();
    }
    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthAttribute Объект JSON конфига, хранящий информацию о конкретном месяце
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
    public void addWorkOutDay(Integer date) throws ConfigurationException {
        if(date > dayCount) {
            throw new ConfigurationException("Номер дополнительного нерабочего дня за границей допустимых значений");
        }
        if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
            throw new ConfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
        }
        dayWorkOutList.add(date);
    }

    public String getName() {
        return name;
    }

    public Integer getDayCount() {
        return dayCount;
    }


    public List<Integer> getDayWorkOutList() {
        return dayWorkOutList;
    }

    public List<Integer> getDayWorkList() {
        return dayWorkList;
    }
}
