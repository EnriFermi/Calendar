package ru.study.calendar.config.parsers.impl.json;

import org.json.simple.JSONObject;
import ru.study.calendar.config.body.impl.DayTemplate;
import ru.study.calendar.config.parsers.impl.json.enums.JsonFieldNames;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

public class JsonDayConfigParser{

    public static DayTemplate parse(JSONObject dayConfig){
        DayTemplate dayTemplate = new DayTemplate();
        dayTemplate.setDayName(dayConfig.get(JsonFieldNames.DAY_NAME.getFieldName()).toString());
        dayTemplate.setWeekDayWorkOut(Boolean.valueOf(dayConfig.get(JsonFieldNames.WEEKDAY_WORKOUT.getFieldName()).toString()));
        return dayTemplate;
    }
}
