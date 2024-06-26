package ru.study.webapp.exceptions.model;

public class IOConfigurationException extends ConfigurationException {
    public IOConfigurationException() {
        super();
    }

    public IOConfigurationException(String message) {
        super(message);
    }

    public IOConfigurationException(Throwable cause) {
        super(cause);
    }
}
