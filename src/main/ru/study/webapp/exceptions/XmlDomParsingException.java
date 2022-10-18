package ru.study.webapp.exceptions;

/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class XmlDomParsingException extends ConfigurationException {

    public XmlDomParsingException() {
        super("Исключение при попытке получения данных из файлов конфигурации в формате XML Dom способом");
    }

    public XmlDomParsingException(String message) {
        super(message);
    }

    public XmlDomParsingException(Throwable cause) {
        super(cause);
    }
}
