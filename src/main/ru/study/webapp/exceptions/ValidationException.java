package ru.study.webapp.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(Class errorClass, Long id, String errorMessage) {
        super("Ошибка при проверке даннных в объекте класса: "+ errorClass.getSimpleName() +" с id: " + id +
                "\n Описание ошибки: " + errorMessage);
    }
}
