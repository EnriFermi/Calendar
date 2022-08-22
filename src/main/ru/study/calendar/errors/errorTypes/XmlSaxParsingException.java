package ru.study.calendar.errors.errorTypes;

/**
 * Исключение вызванное проблемами при попытке создания конфига
 */

public class XmlSaxParsingException extends ConfigurationException {

    public XmlSaxParsingException() {
        super("Исключение при попытке получения данных из файлов конфигурации в формате XML Sax способом");
    }

    public XmlSaxParsingException(String message) {
        super(message);
    }

    public XmlSaxParsingException(Exception e) {
        super(e);
    }
}
