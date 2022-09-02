package ru.study.calendar.config.parsers.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.domain.WeekTemplate;
import ru.study.calendar.config.parsers.impl.json.enums.JsonFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JsonParsingException;
/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
public class JsonWeekConfigParser {
    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    public static WeekTemplate parse(JSONObject weekConfig) throws JsonParsingException {
        WeekTemplate weekTemplate = new WeekTemplate();
        JSONArray dayListConfig = (JSONArray) weekConfig.get(JsonFieldNames.WEEKDAY_NAME_LIST.getFieldName());
        for (Object dayConf:dayListConfig) {
            try {
                weekTemplate.addWeekDay(JsonDayConfigParser.parse((JSONObject) dayConf));
            } catch (ConfigurationException e) {
                throw new JsonParsingException(e);
            }
        }
        return weekTemplate;
    }
}
