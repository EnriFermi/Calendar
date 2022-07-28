package ru.study.calendar.config.impl.json.enums;

//TODO добавить комменты
public enum JsonFieldNames {
    anchorweekday ("anchorWeekDay"),
    yearList("yearList"),
    anchorYear("anchorYear"),
    numberOfMonth("numberOfMonth"),
    dayQuantity("dayQuantity"),
    monthList("monthList"),
    nameOfMonth("nameOfMonth"),
    dayCount("dayCount"),
    dayWorkOutList("dayWorkOutList"),
    dayWorkList("dayWorkList"),
    week("week"),
    weekDayCount("weekDayCount"),
    weekDayNameList("weekDayNameList"),
    dayName("dayName"),
    weekDayWorkOut("weekDayWorkOut"),
    dateOfWorkOutDay("dateOfWorkOutDay"),
    dateOfWorkDay("dateOfWorkDay");
    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }
    JsonFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
