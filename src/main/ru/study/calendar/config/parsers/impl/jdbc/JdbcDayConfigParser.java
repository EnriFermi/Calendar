package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.body.impl.DayTemplate;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

public class JdbcDayConfigParser {

    public static DayTemplate parse(ResultSet daySet) throws SQLException {
        DayTemplate dayTemplate = new DayTemplate();
        dayTemplate.setDayName(daySet.getString(JdbcFieldNames.DAY_NAME.getFieldName()));
        dayTemplate.setWeekDayWorkOut(daySet.getBoolean(JdbcFieldNames.WEEKDAY_WORKOUT.getFieldName()));
        return dayTemplate;
    }
}
