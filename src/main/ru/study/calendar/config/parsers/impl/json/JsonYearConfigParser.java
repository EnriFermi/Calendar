package ru.study.calendar.config.parsers.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.body.impl.YearTemplate;
import ru.study.calendar.config.body.inter.parsing.IYearTemplateForParsing;
import ru.study.calendar.config.parsers.impl.json.enums.JsonFieldNames;
import ru.study.calendar.errors.errorTypes.ConfigurationException;
import ru.study.calendar.errors.errorTypes.JsonParsingException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
public class JsonYearConfigParser {

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @param yearConfig, Объект JSON конфига, хранящий информацию о годе
     * @throws Exception
     */
    public static IYearTemplateForParsing parse(JSONObject yearConfig) throws JsonParsingException {
        IYearTemplateForParsing yearTemplate = new YearTemplate();
        JSONArray monthListConfig = (JSONArray) yearConfig.get(JsonFieldNames.MONTH_LIST.getFieldName());
        for (int iterator = 0; iterator < monthListConfig.size(); iterator++) {
            try {
                yearTemplate.addMonth(JsonMonthConfigParser.parse((JSONObject) monthListConfig.get(iterator)));
            } catch (ConfigurationException e) {
                throw new JsonParsingException(e);
            }
        }
        return yearTemplate;
    }
}
