package ru.study.webapp.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class errorClass, Long id) {
        super("Не удается найти объект типа: "+ errorClass.getSimpleName() +" с id: " + id);
    }
    public NotFoundException(Class errorClass, String errorMessage) {
        super("Не удается найти объект типа: "+ errorClass.getSimpleName() +
                "\n Описание ошибки: " + errorMessage);
    }
}
