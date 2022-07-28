package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.util.ArrayList;
import java.util.List;

public class JsonMonthConfig implements IMonthTemplate {
    private String name;
    private Integer dayCount;
    private List<Integer> dayWorkOutList;
    private List<Integer> dayWorkList;
    JsonMonthConfig(JSONObject monthConfig) {
        /**
         * Настраиваем имя месяца
         */
        JsonFieldNames field = JsonFieldNames.nameOfMonth;
        this.name = (String) monthConfig.get(field.getFieldName());
        /**
         * Настраиваем количество дней
         */
        field = JsonFieldNames.dayCount;
        this.dayCount = ((Long) monthConfig.get(field.getFieldName())).intValue();
        /**
         * Настраиваем список нерабочих дней
         */
        field = JsonFieldNames.dayWorkOutList;
        dayWorkOutList = new ArrayList<>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(field);
        field = JsonFieldNames.dateOfWorkOutDay;
        if (dayWorkOutConfigList != null) {
            for (Object day:dayWorkOutConfigList) {
                dayWorkOutList.add((Integer) ((JSONObject) day).get(field.getFieldName()));
            }
        }
        /**
         * Настраиваем список рабочих дней
         */
        field = JsonFieldNames.dayWorkList;
        dayWorkList = new ArrayList<>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(field);
        field = JsonFieldNames.dateOfWorkDay;
        if (dayWorkConfigList != null) {
            for (Object day:dayWorkConfigList) {
                dayWorkList.add((Integer) ((JSONObject) day).get(field.getFieldName()));
            }
        }
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getDayCount() {
        return this.dayCount;
    }

    @Override
    public List<Integer> getDayWorkOutList() {
        return this.dayWorkOutList;
    }

    @Override
    public List<Integer> getDayWorkList() {
        return this.dayWorkList;
    }

}
