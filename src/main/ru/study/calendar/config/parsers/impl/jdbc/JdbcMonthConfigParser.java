package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.body.impl.MonthTemplate;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JsonParsingException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
public class JdbcMonthConfigParser {
    /**
     * Конструктор месяца по передаваемому JSON конфигу
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     * @throws Exception
     */
    public static MonthTemplate parse(ResultSet monthSet, ResultSet workListSet, ResultSet workOutListSet) throws JsonParsingException, SQLException {
        MonthTemplate monthTemplate = new MonthTemplate();
        /*
         * Настраиваем имя месяца
         */
        monthTemplate.setName(monthSet.getString(JdbcFieldNames.NAME_OF_MONTH.getFieldName()));
        /*
         * Настраиваем количество дней
         */
        monthTemplate.setDayCount(monthSet.getInt(JdbcFieldNames.DAY_COUNT.getFieldName()));

        /*
         * Настраиваем список рабочих дней
         */
        monthTemplate = setDayWorkList(workListSet, monthTemplate);
        /*
         * Настраиваем список нерабочих дней
         */
        monthTemplate = setDayWorkOutList(workOutListSet, monthTemplate);
        return monthTemplate;
    }

    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private static MonthTemplate setDayWorkList(ResultSet workListSet, MonthTemplate monthTemplate) throws JsonParsingException, SQLException {
        if (workListSet != null) {
            while (workListSet.next()) {
                try {
                    monthTemplate.addWorkDay(workListSet.getInt(JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName()));
                } catch (ConfigurationException e) {
                    throw new JsonParsingException(e);
                }
            }
        }
        return monthTemplate;
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private static MonthTemplate setDayWorkOutList(ResultSet workOutListSet, MonthTemplate monthTemplate) throws JsonParsingException, SQLException {
        if (workOutListSet != null) {
            while (workOutListSet.next()) {
                try {
                    monthTemplate.addWorkDay(workOutListSet.getInt(JdbcFieldNames.DATE_OF_WORKOUT_DAY.getFieldName()));
                } catch (ConfigurationException e) {
                    throw new JsonParsingException(e);
                }
            }
        }
        return monthTemplate;
    }
}
