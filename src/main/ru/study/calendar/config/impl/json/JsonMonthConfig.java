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
    //FIXME проверить зацикливание Lombok
    /**
     * Имя месяца
     */
    private String name;
    /**
     * Количество дней в месяце
     */
    private Integer dayCount;
    //TODO по хорошему надо заменить на отдельный тип объектов, если в будущем захотим еще названия праздников добавлять
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
    JsonMonthConfig(JSONObject monthConfig) throws Exception {
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
        //TODO чувство прекрасного, логику вынести в метод DONE
        setDayWorkOutList(monthConfig);
        /*
         * Настраиваем список рабочих дней
         */
        setDayWorkList(monthConfig);
    }

    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private void setDayWorkList(JSONObject monthConfig) {
        this.dayWorkList = new ArrayList<>();
        JSONArray dayWorkConfigList = (JSONArray) monthConfig.get(JsonFieldNames.dayWorkList);
        if (dayWorkConfigList != null) {
            for (Object day:dayWorkConfigList) {
                Integer date = Integer.valueOf(((JSONObject) day).get(JsonFieldNames.dateOfWorkDay.getFieldName())
                        .toString());
                this.dayWorkList.add(date);
            }
        }
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private void setDayWorkOutList(JSONObject monthConfig) {
        this.dayWorkOutList = new ArrayList<>();
        JSONArray dayWorkOutConfigList = (JSONArray) monthConfig.get(JsonFieldNames.dayWorkOutList.getFieldName());
        if (dayWorkOutConfigList != null) {
            for (Object day:dayWorkOutConfigList) {
                Integer date = Integer.valueOf(((JSONObject) day).get(JsonFieldNames.dateOfWorkOutDay.getFieldName())
                        .toString());
                if(this.dayWorkList.contains(date)) {
                    throw new RuntimeException("Коллапс при попытке инициализировать список дополнительных выходных и рабочих дней");
                }
                this.dayWorkOutList.add(date);
            }
        }
    }
}
