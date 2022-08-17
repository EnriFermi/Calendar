package ru.study.calendar.config.impl.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;
import ru.study.calendar.errors.errorTypes.JsonСonfigurationException;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JsonMonthConfig implements IMonthTemplate {
    /**
     * Имя месяца
     */
    @EqualsAndHashCode.Include private String name;
    /**
     * Количество дней в месяце
     */
    private Integer dayCount;
    /**
     * Список дополнительных нерабочих дней
     */
    private List<Integer> dayWorkOutList;
    /**
     * Список дополнительных рабочих дней
     */
    private List<Integer> dayWorkList;

    /**
     * Конструктор месяца по передаваемому JSON конфигу
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     * @throws Exception
     */
    JsonMonthConfig(JSONObject monthConfig) {
        /*
         * Настраиваем имя месяца
         */
        this.name = monthConfig.get(JsonFieldNames.NAME_OF_MONTH.getFieldName()).toString();
        /*
         * Настраиваем количество дней
         */
        this.dayCount = Integer.valueOf(monthConfig.get(JsonFieldNames.DAY_COUNT.getFieldName()).toString());

        /*
         * Настраиваем список рабочих дней
         */
        this.dayWorkList = setDayWorkList(monthConfig);
        /*
         * Настраиваем список нерабочих дней
         */
        this.dayWorkOutList = setDayWorkOutList(monthConfig);
    }

    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private List<Integer> setDayWorkList(JSONObject monthConfig) {
        List<Integer> dayWorkList = new ArrayList<>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(JsonFieldNames.DAY_WORK_LIST.getFieldName());
        if (dayWorkConfigList != null) {
            for (Object day:dayWorkConfigList) {
                Integer date = Integer.valueOf(((JSONObject) day).get(JsonFieldNames.DATE_OF_WORK_DAY.getFieldName())
                        .toString());
                if(date > dayCount) {
                    throw new JsonСonfigurationException("Номер дополнительного рабочего дня за границей допустимых значений");
                }
                if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
                    throw new JsonСonfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
                }
                dayWorkList.add(date);
            }
        }
        return dayWorkList;
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private List<Integer> setDayWorkOutList(JSONObject monthConfig) {
        List<Integer> dayWorkOutList = new ArrayList<>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(JsonFieldNames.DAY_WORKOUT_LIST.getFieldName());
        if (dayWorkOutConfigList != null) {
            dayWorkOutConfigList.forEach(day -> {
                Integer date = Integer.valueOf(((JSONObject) day).get(JsonFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())
                        .toString());
                if(date > dayCount) {
                    throw new JsonСonfigurationException("Номер дополнительного нерабочего дня за границей допустимых значений");
                }
                if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
                    throw new JsonСonfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
                }
                dayWorkOutList.add(date);
            });
        }
        return dayWorkOutList;
    }
}
