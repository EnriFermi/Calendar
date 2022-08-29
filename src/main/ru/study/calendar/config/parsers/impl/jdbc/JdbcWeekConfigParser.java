package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.body.impl.WeekTemplate;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JaxbParsingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
public class JdbcWeekConfigParser {
    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    public static WeekTemplate parse(Statement statement) throws JaxbParsingException, SQLException {
        WeekTemplate weekTemplate = new WeekTemplate();
        ResultSet weekSet = statement.executeQuery("select * from " + JdbcFieldNames.DAY_LIST.getFieldName());
        while(weekSet.next()) {
            try {
                weekTemplate.addWeekDay(JdbcDayConfigParser.parse(weekSet));
            } catch (ConfigurationException e) {
                throw new JaxbParsingException(e);
            }
        }
        weekSet.close();
        return weekTemplate;
    }
}
