package ru.study.webapp.model.configuration.parsers.impl.json.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.webapp.model.configuration.domain.MonthTemplate;
import ru.study.webapp.model.configuration.parsers.impl.json.json.enums.JsonFieldNames;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.JsonParsingException;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
public class JsonMonthConfigurationParser {
    /**
     * Конструктор месяца по передаваемому JSON конфигу
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     * @throws Exception
     */
    public static MonthTemplate parse(JSONObject monthConfig) throws JsonParsingException {
        MonthTemplate monthTemplate = new MonthTemplate();
        /*
         * Настраиваем имя месяца
         */
        monthTemplate.setName(monthConfig.get(JsonFieldNames.NAME_OF_MONTH.getFieldName()).toString());
        /*
         * Настраиваем количество дней
         */
        monthTemplate.setDayCount(Integer.valueOf(monthConfig.get(JsonFieldNames.DAY_COUNT.getFieldName()).toString()));

        /*
         * Настраиваем список рабочих дней
         */
        monthTemplate = setDayWorkList(monthConfig, monthTemplate);
        /*
         * Настраиваем список нерабочих дней
         */
        monthTemplate = setDayWorkOutList(monthConfig, monthTemplate);
        return monthTemplate;
    }

    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     *
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private static MonthTemplate setDayWorkList(JSONObject monthConfig, MonthTemplate monthTemplate) throws JsonParsingException {
        List<Integer> dayWorkList = new ArrayList<>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(JsonFieldNames.DAY_WORK_LIST.getFieldName());
        if (dayWorkConfigList != null) {
            for (Object day:dayWorkConfigList) {
                try {
                    monthTemplate.addWorkDay(Integer.valueOf(((JSONObject) day).get(JsonFieldNames.DATE_OF_WORK_DAY.getFieldName())
                            .toString()));
                } catch (ConfigurationException e) {
                    throw new JsonParsingException(e);
                }
            }
        }
        return monthTemplate;
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     *
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private static MonthTemplate setDayWorkOutList(JSONObject monthConfig, MonthTemplate monthTemplate) throws JsonParsingException {
        List<Integer> dayWorkOutList = new ArrayList<>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(JsonFieldNames.DAY_WORKOUT_LIST.getFieldName());
        if (dayWorkOutConfigList != null) {
            for (Object day:dayWorkOutConfigList) {
                try {
                    monthTemplate.addWorkOutDay(Integer.valueOf(((JSONObject) day).get(JsonFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())
                            .toString()));
                } catch (ConfigurationException e) {
                    throw new JsonParsingException(e);
                }
            }
        }
        return monthTemplate;
    }
}
