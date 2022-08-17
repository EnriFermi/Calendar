package ru.study.calendar.errors.errorTypes;
/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class JsonСonfigurationException extends RuntimeException{

    public JsonСonfigurationException() {
        super("Исключение при попытке получения данных из файлов конфигурации в формате Json");
    }

    public JsonСonfigurationException(String message) {
        super(message);
    }

    public JsonСonfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonСonfigurationException(Throwable cause) {
        super(cause);
    }

    public JsonСonfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
