package ru.study.webapp.exceptions.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)

public class ErrorResponseBody {
    private String ErrorMessage;

    public ErrorResponseBody(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
