package ru.study.webapp.model.configuration.parsers.impl.db.jdbc;

import ru.study.webapp.model.configuration.domain.DayTemplate;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums.JdbcFieldNames;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

public class JdbcDayConfigurationParser {

    public static DayTemplate parse(ResultSet daySet) throws SQLException {
        DayTemplate dayTemplate = new DayTemplate();
        dayTemplate.setDayName(daySet.getString(JdbcFieldNames.DAY_NAME.getFieldName()));
        dayTemplate.setWeekDayWorkOut(daySet.getBoolean(JdbcFieldNames.WEEKDAY_WORKOUT.getFieldName()));
        return dayTemplate;
    }
}
