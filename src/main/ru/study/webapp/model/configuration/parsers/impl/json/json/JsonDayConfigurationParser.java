package ru.study.webapp.model.configuration.parsers.impl.json.json;

import org.json.simple.JSONObject;
import ru.study.webapp.model.configuration.domain.DayTemplate;
import ru.study.webapp.model.configuration.parsers.impl.json.json.enums.JsonFieldNames;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

public class JsonDayConfigurationParser {

    public static DayTemplate parse(JSONObject dayConfig){
        DayTemplate dayTemplate = new DayTemplate();
        dayTemplate.setDayName(dayConfig.get(JsonFieldNames.DAY_NAME.getFieldName()).toString());
        dayTemplate.setWeekDayWorkOut(Boolean.valueOf(dayConfig.get(JsonFieldNames.WEEKDAY_WORKOUT.getFieldName()).toString()));
        return dayTemplate;
    }
}
