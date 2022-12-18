package ru.study.webapp.exceptions;

public class FilterNotFoundException extends RuntimeException {
    public FilterNotFoundException(Class errorClass, String filterName) {
        super("Не удается найти фильтр объекта типа: "+ errorClass.getSimpleName() +
                "\n Имя фильтра: " + filterName);
    }
}
