package template;

import org.json.simple.JSONObject;
import ret.IDayTemplate;

public class DayTemplate implements IDayTemplate {
    private String dayName;
    private Boolean weekDayWorkOut;
    DayTemplate(JSONObject dayConfig){
        FieldsNames field = FieldsNames.dayName;
        this.dayName = (String) dayConfig.get(field.getFieldName());
        field = FieldsNames.weekDayWorkOut;
        this.weekDayWorkOut = (Boolean) dayConfig.get(field.getFieldName());
    }

    @Override
    public String getDayName() {
        return dayName;
    }

    @Override
    public Boolean getWeekDayWorkOut() {
        return weekDayWorkOut;
    }
}
