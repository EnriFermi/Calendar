package ru.study.webapp.model.configuration.parsers.impl.db.jdbc;

import ru.study.webapp.model.configuration.domain.WeekTemplate;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums.JdbcFieldNames;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.JaxbParsingException;

import java.sql.*;

/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
public class JdbcWeekConfigurationParser {
    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    public static WeekTemplate parse(Connection connection, Integer calendarIndex) throws JaxbParsingException, SQLException {
        WeekTemplate weekTemplate = new WeekTemplate();
        PreparedStatement dayListStatement = connection.prepareStatement("select * from "
                + JdbcFieldNames.DAY_TABLE.getFieldName() + " where "
                + JdbcFieldNames.CALENDAR_ID.getFieldName()
                + " = ?");
        dayListStatement.setInt(1, calendarIndex);
        try(ResultSet weekSet = dayListStatement.executeQuery()) {
            while(weekSet.next()) {
                try {
                    weekTemplate.addWeekDay(JdbcDayConfigurationParser.parse(weekSet));
                } catch (ConfigurationException e) {
                    throw new JaxbParsingException(e);
                }
            }
        }
        return weekTemplate;
    }
}
