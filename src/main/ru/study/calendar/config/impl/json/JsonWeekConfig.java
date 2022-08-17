package ru.study.calendar.config.impl.json;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
@Getter
public class JsonWeekConfig implements IWeekTemplate {
    /**
     * Количество дней в неделе
     */
    private Integer weekDayCount;
    /**
     * Список дней недели
     */
    private List<IDayTemplate> weekDayNameList;

    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    JsonWeekConfig(JSONObject weekConfig){
        JSONArray dayListConfig = (JSONArray) weekConfig.get(JsonFieldNames.WEEKDAY_NAME_LIST.getFieldName());
        weekDayNameList = new ArrayList<>();
        for (Object dayConf:dayListConfig) {
            IDayTemplate day = new JsonDayConfig((JSONObject) dayConf);
            if (weekDayNameList.contains(day) == false) {
                weekDayNameList.add(day);
            } else {
                throw new RuntimeException("Не уникальное название дня недели: " + day.getDayName());
            }
        }
        weekDayCount = weekDayNameList.size();
    }
}
