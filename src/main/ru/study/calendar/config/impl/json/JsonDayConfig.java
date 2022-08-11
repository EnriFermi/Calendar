package ru.study.calendar.config.impl.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;
/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JsonDayConfig implements IDayTemplate{
    /**
     * Название дня недели
     */
    @Getter @EqualsAndHashCode.Include private String dayName;
    /**
     * Указание рабочий ли день
     */
    private Boolean weekDayWorkOut;

    /**
     * Конструктор дня недели по передаваемому JSON конфигу
     * @param dayConfig Объект JSON конфига, хранящий информацию о конкретном дне
     */
    JsonDayConfig(JSONObject dayConfig){
        this.dayName = dayConfig.get(JsonFieldNames.DAY_NAME.getFieldName()).toString();
        this.weekDayWorkOut = Boolean.valueOf(dayConfig.get(JsonFieldNames.WEEKDAY_WORKOUT.getFieldName()).toString());
    }

    /**
     * Метод получения информации рабочий ли день
     * @return Возвращает boolean значение рабочий ли день
     */
    @Override
    public Boolean isDefaultDayWorkOut() {
        return this.weekDayWorkOut;
    }
}
