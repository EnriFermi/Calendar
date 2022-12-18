package ru.study.webapp.controller.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum CalendarSearchFilterEnum {
    IS_BEGINNING_YEAR(Arrays.asList("beginningYear"), "IS_BEGINNING_YEAR"),
    IS_END_YEAR(Arrays.asList("beginningYear"), "IS_END_YEAR"),
    CONTAINING_MONTH(Arrays.asList("id", "id", "id"), "CONTAINING_MONTH");
    private final List<String> dtFieldName;
    private final String jsonFieldName;
    public List<String> getDtFieldName() {
        return this.dtFieldName;
    }
    public String getJsonFieldName() {
        return this.jsonFieldName;
    }
    CalendarSearchFilterEnum(List<String> dtFieldName, String jsonFieldName) {
        this.dtFieldName = dtFieldName;
        this.jsonFieldName = jsonFieldName;
    }
    @JsonCreator
    public static CalendarSearchFilterEnum getRequestEnumFromCode(String name){
        for(CalendarSearchFilterEnum filter: CalendarSearchFilterEnum.values()){
            if(filter.getJsonFieldName().equals(name)){
                return filter;
            }
        }
        return null;
    }
}
