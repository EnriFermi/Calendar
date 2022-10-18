package ru.study.webapp.model.configuration.parsers.impl.json.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.webapp.model.configuration.domain.WeekTemplate;
import ru.study.webapp.model.configuration.parsers.impl.json.json.enums.JsonFieldNames;
import ru.study.webapp.exceptions.ConfigurationException;
import ru.study.webapp.exceptions.JsonParsingException;

/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
public class JsonWeekConfigurationParser {
    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    public static WeekTemplate parse(JSONObject weekConfig) throws JsonParsingException {
        WeekTemplate weekTemplate = new WeekTemplate();
        JSONArray dayListConfig = (JSONArray) weekConfig.get(JsonFieldNames.WEEKDAY_NAME_LIST.getFieldName());
        for (Object dayConf:dayListConfig) {
            try {
                weekTemplate.addWeekDay(JsonDayConfigurationParser.parse((JSONObject) dayConf));
            } catch (ConfigurationException e) {
                throw new JsonParsingException(e);
            }
        }
        return weekTemplate;
    }
}
