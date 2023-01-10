package ru.study.webapp.controller.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum CalendarSearchFilterEnum {
    IS_BEGINNING_YEAR("IS_BEGINNING_YEAR"),
    IS_END_YEAR("IS_END_YEAR"),
    CONTAINING_MONTH( "CONTAINING_MONTH");
    private final String jsonFieldName;
    public String getJsonFieldName() {
        return this.jsonFieldName;
}
    CalendarSearchFilterEnum(String jsonFieldName) {
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
