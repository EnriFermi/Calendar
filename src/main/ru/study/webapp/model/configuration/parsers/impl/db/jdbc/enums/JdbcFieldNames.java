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
    CALENDAR_ID("calendar_id"),
    YEAR_ID("year_id"),
    /*
     * ID месяца в БД
     */
    MONTH_ID("month_id"),

    DAY_ID("day_id"),
    /*
     * ID дня недели, являющегося привязочным
     */
    ANCHOR_WEEKDAY_KEY("anchor_week_day_key"),
    /*
     * Год начала допустимого интервала календаря
     */
    BEGINNING_YEAR("beginning_year"),
    /*
     * Год конца допустимого интервала календаря
     */
    END_YEAR("end_year"),
    /*
     * Имя месяца
     */
    NAME_OF_MONTH("month_name"),
    /*
     * Количество дней в месяце
     */
    DAY_COUNT("day_count"),
    /*
     * Имя месяца
     */
    DAY_NAME("day_name"),
    /*
     * Указание рабочий ли день
     */
    WEEKDAY_WORKOUT("week_day_work_out"),
    /*
     * Дата нерабочего дня
     */
    DATE_OF_WORKOUT_DAY("date_of_work_out_day"),
    /*
     * Дата рабочего дня
     */
    DATE_OF_WORK_DAY("date_of_work_day");
    private final String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }
    JdbcFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
