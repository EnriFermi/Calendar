package ru.study.webapp.controller.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class CalendarRequest {
    private List<CalendarSearchFilter> searchFilterList;
    private List<CalendarSortFilter> sortFilterList;
}
