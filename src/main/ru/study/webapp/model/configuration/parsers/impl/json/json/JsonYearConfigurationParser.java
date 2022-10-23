package ru.study.webapp.model.configuration.parsers.impl.json.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.webapp.model.configuration.domain.YearTemplate;
import ru.study.webapp.model.configuration.parsers.impl.json.json.enums.JsonFieldNames;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.JsonParsingException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
public class JsonYearConfigurationParser {

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @param yearConfig, Объект JSON конфига, хранящий информацию о годе
     * @throws Exception
     */
    public static YearTemplate parse(JSONObject yearConfig) throws JsonParsingException {
        YearTemplate yearTemplate = new YearTemplate();
        JSONArray monthListConfig = (JSONArray) yearConfig.get(JsonFieldNames.MONTH_LIST.getFieldName());
        for (int iterator = 0; iterator < monthListConfig.size(); iterator++) {
            try {
                yearTemplate.addMonth(JsonMonthConfigurationParser.parse((JSONObject) monthListConfig.get(iterator)));
            } catch (ConfigurationException e) {
                throw new JsonParsingException(e);
            }
        }
        return yearTemplate;
    }
}
