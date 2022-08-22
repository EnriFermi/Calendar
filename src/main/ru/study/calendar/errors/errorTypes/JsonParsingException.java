package ru.study.calendar.errors.errorTypes;
/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class JsonParsingException extends ConfigurationException{

    public JsonParsingException() {
        super("Исключение при попытке получения данных из файлов конфигурации в формате Json");
    }

    public JsonParsingException(String message) {
        super(message);
    }
    public JsonParsingException(Throwable cause) {
        super(cause);
    }
}
