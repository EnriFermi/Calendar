package ru.study.calendar.errors.errorTypes;
/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class XmlСonfigurationException extends RuntimeException{

    public XmlСonfigurationException() {
        super("Исключение при попытке получения данных из файлов конфигурации в формате XML");
    }

    public XmlСonfigurationException(String message) {
        super(message);
    }

    public XmlСonfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlСonfigurationException(Throwable cause) {
        super(cause);
    }

    public XmlСonfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
