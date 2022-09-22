package ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums;

/**
 * Хранит названия полей для получения информации из БД
 */
public enum JdbcFieldNames {

    CALENDAR_TABLE("calendarlist"),
    DAY_TABLE("daylist"),
    DAY_WORK_TABLE("dayworklist"),
    DAY_WORKOUT_TABLE("dayworkoutlist"),
    MONTH_TABLE("monthlist"),
    YEAR_TABLE("yearlist"),
    CALENDAR_ID("calendarid"),
    YEAR_ID("yearid"),
    /*
     * ID месяца в БД
     */
    MONTH_ID("monthid"),

    DAY_ID("dayid"),
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
    private final String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }
    JdbcFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
