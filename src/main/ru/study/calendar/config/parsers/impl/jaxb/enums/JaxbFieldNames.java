package ru.study.calendar.config.parsers.impl.jaxb.enums;

/**
 * Хранит названия полей для парсинга JSON конфига
 */
public enum JaxbFieldNames {
    /*
     * День недели первого числа первого месяца привязочного года
     */
    ANCHOR_WEEKDAY("anchorWeekDay"),
    /*
     * Год начала допустимого интервала календаря
     */
    BEGINNING_YEAR("beginningYear"),
    /*
     * Год конца допустимого интервала календаря
     */
    END_YEAR("endYear"),
    /*
     * Список шаблонов лет
     */
    YEAR_LIST("yearList"),
    /*
     * Список месяцев в году
     */
    MONTH_LIST("monthList"),
    /*
     * Имя месяца
     */
    NAME_OF_MONTH("nameOfMonth"),
    /*
     * Количество дней в месяце
     */
    DAY_COUNT("dayCount"),
    /*
     * Список дополнительных нерабочих дней
     */
    DAY_WORKOUT_LIST("dayWorkOutList"),
    /*
     * Список дополнительных рабочих дней
     */
    DAY_WORK_LIST("dayWorkList"),
    /*
     * Шаблон недели
     */
    WEEK("week"),
    /*
     * Список дней недели
     */
    WEEKDAY_NAME_LIST("weekDayNameList"),
    /*
     * Название дня недели
     */
    DAY_NAME("dayName"),
    /*
     * Указание рабочий ли день
     */
    WEEKDAY_WORKOUT("weekDayWorkOut"),
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
    JaxbFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }
}
