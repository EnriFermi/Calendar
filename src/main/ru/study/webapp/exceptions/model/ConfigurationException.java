package ru.study.webapp.exceptions.model;

public class ConfigurationException extends RuntimeException {
    public ConfigurationException(Throwable cause) {
        super(cause);
    }
    public ConfigurationException() {
        super("Исключение при попытке получения данных из файлов конфигурации");
    }

    public ConfigurationException(String message) {
        super(message);
    }
}
