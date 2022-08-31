package ru.study.calendar.config.factory.enums;

public enum ConfigTypesEnum {
    JDBC("xml"),
    DOM("xml"),
    SAX("xml"),
    JAXB("xml"),
    JSON("json"),
    AUTO("");
    private String fieldName;
    public String getTypeOfFile() {
        return this.fieldName;
    }
    ConfigTypesEnum(String fieldName) {
        this.fieldName = fieldName;
    }
}
