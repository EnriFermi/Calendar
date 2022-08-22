package ru.study.calendar.errors.errorTypes;

public class ConfigurationException extends Exception {
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
