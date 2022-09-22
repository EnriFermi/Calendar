package ru.study.webapp.model.exceptions;

public class ConfigurationFactoryException extends ConfigurationException {
    public ConfigurationFactoryException(Throwable cause) {
        super(cause);
    }
    public ConfigurationFactoryException() {
        super("Исключение при попытке получения данных из файлов конфигурации");
    }

    public ConfigurationFactoryException(String message) {
        super(message);
    }
}
