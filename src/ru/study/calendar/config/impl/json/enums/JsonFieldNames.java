package ru.study.calendar.config.impl.json.enums;

public enum JsonFieldNames {
    /**
     * День недели первого числа первого месяца привязочного года
     */
    anchorWeekDay("anchorWeekDay"),
    /**
     * Год начала допустимого интервала календаря
     */
    beginningYear("beginningYear"),
    /**
     * Год конца допустимого интервала календаря
     */
    endYear("endYear"),
    /**
     * Список шаблонов лет
     */
    yearList("yearList"),
    /**
     * Количество месяцев
     */
    numberOfMonth("numberOfMonth"),
    /**
     * Количество дней в году
     */
    dayQuantity("dayQuantity"),
    /**
     * Список месяцев в году
     */
    monthList("monthList"),
    /**
     * Имя месяца
     */
    nameOfMonth("nameOfMonth"),
    /**
     * Количество дней в месяце
     */
    dayCount("dayCount"),
    /**
     * Список дополнительных нерабочих дней
     */
    dayWorkOutList("dayWorkOutList"),
    /**
     * Список дополнительных рабочих дней
     */
    dayWorkList("dayWorkList"),
    /**
     * Шаблон недели
     */
    week("week"),
    /**
     * Количество дней в неделе
     */
    weekDayCount("weekDayCount"),
    /**
     * Список дней недели
     */
    weekDayNameList("weekDayNameList"),
    /**
     * Название дня недели
     */
    dayName("dayName"),
    /**
     * Указание рабочий ли день
     */
    weekDayWorkOut("weekDayWorkOut"),
    /**
     * Дата нерабочего дня
     */
    dateOfWorkOutDay("dateOfWorkOutDay"),
    /**
     * Дата рабочего дня
     */
    dateOfWorkDay("dateOfWorkDay");
    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }
    JsonFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
