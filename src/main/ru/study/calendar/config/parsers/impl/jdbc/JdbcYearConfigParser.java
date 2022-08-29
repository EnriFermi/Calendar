package ru.study.calendar.config.parsers.impl.jdbc;

import ru.study.calendar.config.body.impl.YearTemplate;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JdbcParsingException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        Statement monthStatement = connection.createStatement();
        Statement workStatement = connection.createStatement();
        Statement workOutStatement = connection.createStatement();
        ResultSet monthSet = monthStatement.executeQuery("select * from " +JdbcFieldNames.MONTH_LIST.getFieldName() +" where yearid = " + index);
        Integer monthId;
        while (monthSet.next()) {
            try {
                monthId = monthSet.getInt(JdbcFieldNames.MONTH_ID.getFieldName());
                ResultSet workListSet = workStatement.executeQuery("select * from " +JdbcFieldNames.DAY_WORK_LIST.getFieldName()
                        +" where "+ JdbcFieldNames.MONTH_ID.getFieldName() +" = " + monthId);
                ResultSet workOutListSet = workOutStatement.executeQuery("select * from "+ JdbcFieldNames.DAY_WORKOUT_LIST.getFieldName()
                        + " where " + JdbcFieldNames.MONTH_ID + " = " + monthId);
                yearTemplate.addMonth(JdbcMonthConfigParser.parse(monthSet, workListSet, workOutListSet));
                workListSet.close();
                workOutListSet.close();
            } catch (ConfigurationException e) {
                throw new JdbcParsingException(e);
            }
        }
        monthSet.close();
        return yearTemplate;
    }
}
