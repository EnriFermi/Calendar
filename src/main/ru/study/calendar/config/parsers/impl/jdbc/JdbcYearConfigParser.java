package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.domain.impl.YearTemplate;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import java.sql.*;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
public class JdbcYearConfigParser {

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @param yearConfig, Объект JSON конфига, хранящий информацию о годе
     * @throws Exception
     */
    public static YearTemplate parse(Connection connection, Integer index) throws JdbcParsingException, SQLException {
        YearTemplate yearTemplate =  new YearTemplate();
        PreparedStatement monthListStatement = connection.prepareStatement("select * from "
                + JdbcFieldNames.MONTH_LIST.getFieldName() + " where "
                + JdbcFieldNames.YEAR_ID.getFieldName() + " = ?");
        monthListStatement.setInt(1, index);
        try(ResultSet monthSet = monthListStatement.executeQuery()) {
            Integer monthId;
            while (monthSet.next()) {
                try {
                    PreparedStatement workListStatement = connection.prepareStatement("select * from "
                            + JdbcFieldNames.DAY_WORK_LIST.getFieldName() + " where "
                            + JdbcFieldNames.MONTH_ID.getFieldName() + " = ?");
                    PreparedStatement workOutListStatement = connection.prepareStatement("select * from "
                            + JdbcFieldNames.DAY_WORKOUT_LIST.getFieldName() + " where "
                            + JdbcFieldNames.MONTH_ID.getFieldName() + " = ?");
                    monthId = monthSet.getInt(JdbcFieldNames.MONTH_ID.getFieldName());
                    workListStatement.setInt(1, monthId);
                    workOutListStatement.setInt(1, monthId);
                    try (ResultSet workListSet = workListStatement.executeQuery();
                        ResultSet workOutListSet = workOutListStatement.executeQuery()) {
                        yearTemplate.addMonth(JdbcMonthConfigParser.parse(monthSet, workListSet, workOutListSet));
                    }
                } catch (ConfigurationException e) {
                    throw new JdbcParsingException(e);
                }
            }
        }
        return yearTemplate;
    }
}
