package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class JsonYearConfig implements IYearTemplate {
    private List<IMonthTemplate> monthList;
    private Integer numberOfMonth;
    private HashMap<String, Integer> monthMap;

    private Integer dayQuantity;
    JsonYearConfig(JSONObject yearConfig){
        this.numberOfMonth = Integer.valueOf(yearConfig.get(JsonFieldNames.numberOfMonth.getFieldName()).toString());
        this.dayQuantity = Integer.valueOf(yearConfig.get(JsonFieldNames.dayQuantity.getFieldName()).toString());
        this.monthList = new ArrayList<>();
        this.monthMap = new HashMap<>();
        JSONArray monthListConfig = (JSONArray) yearConfig.get(JsonFieldNames.monthList.getFieldName());
        for(int iterator=0; iterator<this.numberOfMonth;iterator++)
        {
            this.monthList.add(new JsonMonthConfig((JSONObject) monthListConfig.get(iterator)));
            this.monthMap.put(this.monthList.get(iterator).getName(), iterator);
        }
    }

    @Override
    public Integer getDayQuantity() {
        return this.dayQuantity;
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
    public Integer getIndexOfMonthByName(String name) throws Exception {
        if (this.monthMap.containsKey(name)) {
            return this.monthMap.get(name);
        }
        throw new Exception("Month with such name doesn't exist");
    }

    @Override
    public IMonthTemplate getOffsetDayFrom(IMonthTemplate startDate, Integer offset) {
        Integer fullOffset = this.monthMap.get(startDate.getName()) + offset % this.numberOfMonth;
        return this.monthList.get(fullOffset);
    }
}
