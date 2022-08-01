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
        this.name = monthConfig.get(JsonFieldNames.nameOfMonth.getFieldName()).toString();
        /**
         * Настраиваем количество дней
         */
        this.dayCount = Integer.valueOf(monthConfig.get(JsonFieldNames.dayCount.getFieldName()).toString());
        /**
         * Настраиваем список нерабочих дней
         */
        this.dayWorkOutList = new ArrayList<>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(JsonFieldNames.dayWorkOutList.getFieldName());
        if (dayWorkOutConfigList != null) {
            for (Object day:dayWorkOutConfigList) {
                this.dayWorkOutList.add((Integer) ((JSONObject) day).get(JsonFieldNames.dateOfWorkOutDay.getFieldName()));
            }
        }
        /**
         * Настраиваем список рабочих дней
         */
        this.dayWorkList = new ArrayList<>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(JsonFieldNames.dayWorkList);
        if (dayWorkConfigList != null) {
            for (Object day:dayWorkConfigList) {
                this.dayWorkList.add((Integer) ((JSONObject) day).get(JsonFieldNames.dateOfWorkDay.getFieldName()));
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
