package template;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ret.IMonthTemplate;

import java.util.ArrayList;
import java.util.List;

public class MonthTemplate implements IMonthTemplate {
    private String name;
    private Integer dayCount;
    private List<Integer> dayWorkOutList;
    private List<Integer> dayWorkList;
    MonthTemplate(JSONObject monthConfig) {
        /**
         * Настраиваем имя месяца
         */
        FieldsNames field = FieldsNames.nameOfMonth;
        this.name = (String) monthConfig.get(field.getFieldName());
        /**
         * Настраиваем количество дней
         */
        field = FieldsNames.dayCount;
        this.dayCount = ((Long) monthConfig.get(field.getFieldName())).intValue();
        /**
         * Настраиваем список нерабочих дней
         */
        field = FieldsNames.dayWorkOutList;
        dayWorkOutList = new ArrayList<Integer>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(field);
        field = FieldsNames.dateOfWorkOutDay;
        if (dayWorkOutConfigList != null) {
            for (Object day:dayWorkOutConfigList) {
                dayWorkOutList.add((Integer) ((JSONObject) day).get(field.getFieldName()));
            }
        }
        /**
         * Настраиваем список рабочих дней
         */
        field = FieldsNames.dayWorkList;
        dayWorkList = new ArrayList<Integer>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(field);
        field = FieldsNames.dateOfWorkDay;
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
