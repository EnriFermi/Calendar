package ru.study.webapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalendarControllerException {

    @ResponseBody
    @ExceptionHandler(CalendarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String calendarNotFoundHandler(CalendarNotFoundException ex) {
        return ex.getMessage();
    }
}
