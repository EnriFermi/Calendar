package ru.study.calendar.config.impl.json;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
@Getter
public class JsonYearConfig implements IYearTemplate {
    /**
     * Список месяцев в году
     */
    private final List<IMonthTemplate> monthList;
    /**
     * Количество дней в году
     */
    private final Integer dayQuantity;

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @param yearConfig, Объект JSON конфига, хранящий информацию о годе
     * @throws Exception
     */
    JsonYearConfig(JSONObject yearConfig) throws Exception {
        //TODO убрать такое поле из конфига, и самому посчитать либо добавить проверку совпадения значений
        this.dayQuantity = Integer.valueOf(yearConfig.get(JsonFieldNames.dayQuantity.getFieldName()).toString());
        this.monthList = new ArrayList<>();
        JSONArray monthListConfig = (JSONArray) yearConfig.get(JsonFieldNames.monthList.getFieldName());
        for (int iterator = 0; iterator < monthListConfig.size(); iterator++) {
            IMonthTemplate month = new JsonMonthConfig((JSONObject) monthListConfig.get(iterator));
            if (!this.monthList.contains(month)) {
                this.monthList.add(month);
            } else {
                throw new RuntimeException("Не уникальное название месяца: " + month.getName());
            }
        }
    }
}
