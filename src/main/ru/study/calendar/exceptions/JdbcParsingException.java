package ru.study.calendar.exceptions;
/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class JdbcParsingException extends ConfigurationException{

    public JdbcParsingException() {
        super("Исключение при попытке получения данных из файлов конфигурации из БД при помощи JDBC");
    }

    public JdbcParsingException(String message) {
        super(message);
    }
    public JdbcParsingException(Throwable cause) {
        super(cause);
    }
}
