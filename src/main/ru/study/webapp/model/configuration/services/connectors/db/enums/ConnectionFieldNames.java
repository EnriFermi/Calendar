package ru.study.webapp.model.configuration.services.connectors.db.enums;

/**
 * Хранит названия полей для получения информации из БД
 */
public enum ConnectionFieldNames {
    /*
     * Логин пользователя для подключения к БД
     */
    USER_NAME("userName"),
    /*
     * Пароль пользователя для подключения к БД
     */
    PASSWORD("password"),
    /*
     * URL БД
     */
    CONNECTION_URL("connectionURL"),
    /*
     * Название драйвера JDBC
     */
    DRIVER_NAME("driverName"),
    /*
     * ID календаря в базе данных, который надо извлечь
     */
    CALENDAR_ID("calendarID");

    private final String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }
    ConnectionFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
