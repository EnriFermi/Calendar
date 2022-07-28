package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;
import java.util.ArrayList;
import java.util.List;
public class JsonYearConfig implements IYearTemplate {
    private List<IMonthTemplate> monthList;
    private Integer numberOfMonth;

    private Integer dayQuantity;
    JsonYearConfig(JSONObject yearConfig){
        JsonFieldNames field = JsonFieldNames.numberOfMonth;
        this.numberOfMonth = ((Long) yearConfig.get(field.getFieldName())).intValue();
        field = JsonFieldNames.dayQuantity;
        this.dayQuantity = ((Long) yearConfig.get(field.getFieldName())).intValue();
        field = JsonFieldNames.monthList;
        this.monthList = new ArrayList<>();
        JSONArray monthListConfig = (JSONArray) yearConfig.get(field.getFieldName());
        for(int iterator=0; iterator<numberOfMonth;iterator++)
        {
            this.monthList.add(new JsonMonthConfig((JSONObject) monthListConfig.get(iterator)));
        }
    }

    @Override
    public Integer getDayQuantity() {
        return dayQuantity;
    }
    @Override
    public Integer getNumberOfMonth() {
        return this.numberOfMonth;
    }
    @Override
    public List<IMonthTemplate> getMonthList() {
        return this.monthList;
    }

    @Override
    public Integer getIndexOfMonthByName(String name) {
        for(int iterator=0; iterator<numberOfMonth; iterator++){
            if(monthList.get(iterator).getName().equals(name)){
                return iterator;
            }
        }
        return null;
    }

}
