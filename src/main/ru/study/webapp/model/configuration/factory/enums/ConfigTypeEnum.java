package ru.study.webapp.model.configuration.factory.enums;

public enum ConfigTypeEnum {
    /**
     * Парсер на основе JDBC с извлечением конфигурации из xml конфига
     */
    JDBC("xml"),
    /**
     * Парсер на основе DOM с извлечением из xml конфига
     */
    DOM("xml"),
    /**
     * Парсер на основе SAX с извлечением из xml конфига
     */
    SAX("xml"),
    /**
     * Парсер на основе JAXB с извлечением из xml конфига
     */

    JAXB("xml"),
    /**
     * Парсер на основе JSON с извлечением из json конфига
     */

    JSON("json"),
    /**
     * Автоматический выбор парсера
     */
    AUTO("");
    private final String fieldName;
    public String getTypeOfFile() {
        return this.fieldName;
    }
    ConfigTypeEnum(String fieldName) {
        this.fieldName = fieldName;
    }
}
