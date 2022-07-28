package ru.study.calendar.config.impl.json;

import org.json.simple.JSONObject;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

public class JsonDayConfig implements IDayTemplate {
    private String dayName;
    private Boolean weekDayWorkOut;
    JsonDayConfig(JSONObject dayConfig){
        JsonFieldNames field = JsonFieldNames.dayName;
        this.dayName = (String) dayConfig.get(field.getFieldName());
        field = JsonFieldNames.weekDayWorkOut;
        this.weekDayWorkOut = (Boolean) dayConfig.get(field.getFieldName());
    }

    @Override
    public String getDayName() {
        return dayName;
    }

    @Override
    public Boolean isDefaultDayWorkOut() {
        return weekDayWorkOut;
    }
}
