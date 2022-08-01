package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonWeekConfig implements IWeekTemplate {
    private Integer weekDayCount;
    private List<IDayTemplate> weekDayNameList;

    private HashMap<String, Integer> weekDayNameMap;

    JsonWeekConfig(JSONObject weekConfig){
        this.weekDayCount = Integer.valueOf(weekConfig.get(JsonFieldNames.weekDayCount.getFieldName()).toString());
        JSONArray dayListConfig = (JSONArray) weekConfig.get(JsonFieldNames.weekDayNameList.getFieldName());
        this.weekDayNameList = new ArrayList<>();
        this.weekDayNameMap = new HashMap<>();
        int iterator = 0;
        for (Object day:dayListConfig) {
            this.weekDayNameList.add( new JsonDayConfig((JSONObject) day));
            this.weekDayNameMap.put(this.weekDayNameList.get(iterator).getDayName(), iterator);
            iterator++;
        }
    }

    @Override
    public Integer getWeekDayCount() {
        return this.weekDayCount;
    }

    @Override
    public List<IDayTemplate> weekDayNameList() {
        return this.weekDayNameList;
    }

    @Override
    public IDayTemplate getOffsetDayFrom(IDayTemplate startDate, Integer offset) {
        //TODO реализовать DONE
        Integer fullOffset = (this.weekDayNameMap.get(startDate.getDayName())+offset) % this.weekDayCount;
        return this.weekDayNameList.get(fullOffset);
    }
}
