package ru.study.calendar.config.impl.json;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
@Getter
public class JsonMonthConfig implements IMonthTemplate {
    /**
     * Имя месяца
     */
    private String name;
    /**
     * Количество дней в месяце
     */
    private Integer dayCount;
    /**
     * Список дополнительных нерабочих дней
     */
    private List<Integer> dayWorkOutList;
    //TODO почему не используется
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
        this.name = monthConfig.get(JsonFieldNames.nameOfMonth.getFieldName()).toString();
        /*
         * Настраиваем количество дней
         */
        this.dayCount = Integer.valueOf(monthConfig.get(JsonFieldNames.dayCount.getFieldName()).toString());
        /*
         * Настраиваем список нерабочих дней
         */
        //TODO аналогично setDayWorkList
        setDayWorkOutList(monthConfig);
        /*
         * Настраиваем список рабочих дней
         */
        this.dayWorkList.addAll(setDayWorkList(monthConfig));
    }

    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private List<Integer> setDayWorkList(JSONObject monthConfig) {
        List<Integer> dayWorkList = new ArrayList<>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(JsonFieldNames.dayWorkList);
        if (dayWorkConfigList != null) {
            for (Object day:dayWorkConfigList) {
                Integer date = Integer.valueOf(((JSONObject) day).get(JsonFieldNames.dateOfWorkDay.getFieldName())
                        .toString());
                dayWorkList.add(date);
            }
        }
        return dayWorkList;
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private void setDayWorkOutList(JSONObject monthConfig) {
        this.dayWorkOutList = new ArrayList<>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(JsonFieldNames.dayWorkOutList.getFieldName());
        if (dayWorkOutConfigList != null) {
            //TODO   dayWorkOutConfigList.forEach();
            for (Object day:dayWorkOutConfigList) {
                Integer date = Integer.valueOf(((JSONObject) day).get(JsonFieldNames.dateOfWorkOutDay.getFieldName())
                        .toString());
                if(this.dayWorkList.contains(date)) {
                    //TODO подумать после реализации пунктов от 8.08
                    throw new RuntimeException("Коллапс при попытке инициализировать список дополнительных выходных и рабочих дней");
                }
                this.dayWorkOutList.add(date);
            }
        }
    }
}
