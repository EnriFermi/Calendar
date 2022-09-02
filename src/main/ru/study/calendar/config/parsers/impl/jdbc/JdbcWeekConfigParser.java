package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.domain.impl.WeekTemplate;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JaxbParsingException;

import java.sql.*;

/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
public class JdbcWeekConfigParser {
    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    public static WeekTemplate parse(Connection connection, Integer calendarIndex) throws JaxbParsingException, SQLException {
        WeekTemplate weekTemplate = new WeekTemplate();
        PreparedStatement dayListStatement = connection.prepareStatement("select * from "
                + JdbcFieldNames.DAY_LIST.getFieldName() + " where "
                + JdbcFieldNames.CALENDAR_ID.getFieldName()
                + " = ?");
        dayListStatement.setInt(1, calendarIndex);
        try(ResultSet weekSet = dayListStatement.executeQuery()) {
            while(weekSet.next()) {
                try {
                    weekTemplate.addWeekDay(JdbcDayConfigParser.parse(weekSet));
                } catch (ConfigurationException e) {
                    throw new JaxbParsingException(e);
                }
            }
        }
        return weekTemplate;
    }
}
