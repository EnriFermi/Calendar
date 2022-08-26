package ru.study.calendar.config.parsers.impl.jdbc.enums;

/**
 * Хранит названия полей для получения информации из БД
 */
public enum JdbcFieldNames {
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
     * ID года в БД
     */
    YEAR_ID("yearid"),
    /*
     * ID месяца в БД
     */
    MONTH_ID("monthid"),
    /*
     * ID дня недели, являющегося привязочным
     */
    ANCHOR_WEEKDAY_KEY("anchorWeekDayKey"),
    /*
     * Год начала допустимого интервала календаря
     */
    BEGINNING_YEAR("beginnigyear"),
    /*
     * Год конца допустимого интервала календаря
     */
    END_YEAR("endYear"),
    /*
     * Имя месяца
     */
    NAME_OF_MONTH("monthName"),
    /*
     * Количество дней в месяце
     */
    DAY_COUNT("dayCount"),
    /*
     * Имя месяца
     */
    DAY_NAME("dayName"),
    /*
     * Указание рабочий ли день
     */
    WEEKDAY_WORKOUT("weekdayworkout"),
    /*
     * Дата нерабочего дня
     */
    DATE_OF_WORKOUT_DAY("dateOfWorkOutDay"),
    /*
     * Дата рабочего дня
     */
    DATE_OF_WORK_DAY("dateOfWorkDay");
    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }
    JdbcFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
