package template;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ret.IDayTemplate;
import ret.IWeekTemplate;

import java.util.ArrayList;
import java.util.List;

public class WeekTemplate implements IWeekTemplate {
    private Integer weekDayCount;
    private List<IDayTemplate> weekDayNameList;
    WeekTemplate(JSONObject weekConfig){
        FieldsNames field = FieldsNames.weekDayCount;
        this.weekDayCount = ((Long) weekConfig.get(field.getFieldName())).intValue();
        field = FieldsNames.weekDayNameList;
        JSONArray dayListConfig = (JSONArray) weekConfig.get(field.getFieldName());
        weekDayNameList = new ArrayList<IDayTemplate>();
        for (Object day:dayListConfig) {
            weekDayNameList.add(new DayTemplate((JSONObject) day));
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
        for(int iterator=0; iterator<this.weekDayCount; iterator++){
            if(this.weekDayNameList.get(iterator).getDayName().equals(name)){
                return iterator;
            }
        }
        return 0;
    }
}
