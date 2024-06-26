package ru.study.webapp.model.configuration.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.study.webapp.exceptions.model.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PUBLIC)

public class YearTemplate {
    public void setMonthList(List<MonthTemplate> monthList) throws ConfigurationException {
        monthList.clear();
        for(MonthTemplate month:monthList){
            addMonth(month);
        }
    }

    /**
     * Список месяцев в году
     */
    private List<MonthTemplate> monthList;
    /**
     * Количество дней в году
     */
    @Setter(AccessLevel.PUBLIC)
    private Integer dayQuantity;
    public YearTemplate(){
        monthList = new ArrayList<>();
        dayQuantity = 0;
    }
    /**
     * Добавляет шаблон месяца к списку месяцев
     *
     * @param month Шаблон месяца
     */
    public void addMonth(MonthTemplate month) throws ConfigurationException {
        if (!monthList.contains(month)) {
            monthList.add(month);
            dayQuantity+=month.getDayCount();
        } else {
            throw new ConfigurationException("Не уникальное название месяца: " + month.getName());
        }
    }
}
