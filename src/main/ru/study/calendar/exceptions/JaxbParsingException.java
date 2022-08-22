package ru.study.calendar.exceptions;
/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class JaxbParsingException extends ConfigurationException{

    public JaxbParsingException() {
        super("Исключение при попытке получения данных из файлов конфигурации в формате XML при помощи JAXB");
    }

    public JaxbParsingException(String message) {
        super(message);
    }
    public JaxbParsingException(Throwable cause) {
        super(cause);
    }
}
