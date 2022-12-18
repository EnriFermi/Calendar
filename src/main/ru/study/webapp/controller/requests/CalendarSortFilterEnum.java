package ru.study.webapp.controller.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CalendarSortFilterEnum {
    IS_BEGINNING_YEAR("beginningYear", "IS_BEGINNING_YEAR"),
    IS_END_YEAR("endYear", "IS_END_YEAR");
    private final String jsonFieldName;
    private final String dtFieldName;

    public String getDtFieldName() {
        return this.dtFieldName;
    }
    public String getJsonFieldName() {
        return this.jsonFieldName;
    }
    CalendarSortFilterEnum(String dtFieldName, String jsonFieldName) {
        this.dtFieldName = dtFieldName;
        this.jsonFieldName = jsonFieldName;
    }
    @JsonCreator
    public static CalendarSortFilterEnum getSortEnumFromCode(String name){
        for(CalendarSortFilterEnum filter: CalendarSortFilterEnum.values()){
            if(filter.getJsonFieldName().equals(name)){
                return filter;
            }
        }
        return null;
    }
}
