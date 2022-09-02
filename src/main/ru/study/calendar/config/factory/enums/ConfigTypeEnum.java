package ru.study.calendar.config.factory.enums;

public enum ConfigTypeEnum {
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
    ConfigTypeEnum(String fieldName) {
        this.fieldName = fieldName;
    }
}
