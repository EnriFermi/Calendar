package ru.study.webapp.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.exceptions.ValidationException;

@ControllerAdvice
public class CalendarControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class, ValidationException.class})
    protected ResponseEntity<Object> handleNotFoundAndValidation(RuntimeException ex, WebRequest request) {
        ErrorResponseBody body = new ErrorResponseBody(ex.getMessage());
        return handleExceptionInternal(ex, body,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    //TODO если версии не совпали то 409 Conflict
}
