package ru.study.calendar.exceptions;

/**
 * Исключение о выходе значений переменной за границы допустимых значений
 */

public class OutOfBoundException extends Exception{
    public OutOfBoundException() {
        super("Значение за границей допустимых значений");
    }
    public OutOfBoundException(String message) {
        super(message);
    }

    public OutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfBoundException(Throwable cause) {
        super(cause);
    }

    public OutOfBoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
