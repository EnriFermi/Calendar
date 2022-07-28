package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.util.ArrayList;
import java.util.List;

public class JsonWeekConfig implements IWeekTemplate {
    private Integer weekDayCount;
    private List<IDayTemplate> weekDayNameList;
    JsonWeekConfig(JSONObject weekConfig){
        JsonFieldNames field = JsonFieldNames.weekDayCount;
        this.weekDayCount = ((Long) weekConfig.get(field.getFieldName())).intValue();
        field = JsonFieldNames.weekDayNameList;
        JSONArray dayListConfig = (JSONArray) weekConfig.get(field.getFieldName());
        weekDayNameList = new ArrayList<>();
        for (Object day:dayListConfig) {
            weekDayNameList.add(new JsonDayConfig((JSONObject) day));
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
    public Integer getIndexOfDayByName(String name) {
        //TODO почитать про equals, hashcode
        for(int iterator=0; iterator<this.weekDayCount; iterator++){
            if(this.weekDayNameList.get(iterator).getDayName().equals(name)){
                return iterator;
            }
        }
        return 0;
    }

    @Override
    public IDayTemplate getOffsetDayFrom(IDayTemplate startDate, Integer offset) {
        //TODO реализовать
        return  null;
    }
}
