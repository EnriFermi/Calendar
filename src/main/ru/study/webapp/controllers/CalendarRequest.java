package ru.study.webapp.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CalendarRequest {

    private String year;
    private String configType;
    private String configPath;

    public CalendarRequest() {}
}
