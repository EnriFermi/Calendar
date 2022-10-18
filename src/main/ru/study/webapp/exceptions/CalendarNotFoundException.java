package ru.study.webapp.exceptions;

public class CalendarNotFoundException extends RuntimeException {
    public CalendarNotFoundException(Long id) {
        super("Не удается найти календарь с id: " + id);
    }
}
