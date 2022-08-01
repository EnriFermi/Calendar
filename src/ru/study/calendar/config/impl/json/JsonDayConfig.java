package ru.study.calendar.config.impl.json;

import org.json.simple.JSONObject;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

public class JsonDayConfig implements IDayTemplate {
    private String dayName;
    private Boolean weekDayWorkOut;
    JsonDayConfig(JSONObject dayConfig){
        this.dayName = dayConfig.get(JsonFieldNames.dayName.getFieldName()).toString();
        this.weekDayWorkOut = Boolean.valueOf(dayConfig.get(JsonFieldNames.weekDayWorkOut.getFieldName()).toString());
    }

    @Override
    public String getDayName() {
        return this.dayName;
    }

    @Override
    public Boolean isDefaultDayWorkOut() {
        return this.weekDayWorkOut;
    }
}
