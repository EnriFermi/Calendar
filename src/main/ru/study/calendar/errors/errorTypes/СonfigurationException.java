package ru.study.calendar.errors.errorTypes;
/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class СonfigurationException extends RuntimeException{

    public СonfigurationException() {
        super("Исключение при попытке получения данных из файлов конфигурации");
    }

    public СonfigurationException(String message) {
        super(message);
    }

    public СonfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public СonfigurationException(Throwable cause) {
        super(cause);
    }

    public СonfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
