package ru.study.webapp.controller.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class CalendarSearchFilter {
    private CalendarSearchFilterEnum filterType;
    private Object filterBody;
}
