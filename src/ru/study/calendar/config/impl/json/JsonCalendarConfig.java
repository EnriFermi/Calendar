package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.study.calendar.config.ICalendarConfig;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonCalendarConfig implements ICalendarConfig {
    private String anchorWeekDay;
    private Integer anchorYear;
    private List<IYearTemplate> yearList;
    private IWeekTemplate week;
    //TODO 9. ReT Сделать красивые обработки
    public JsonCalendarConfig(String path){
        JSONParser parserOfCalendar = new JSONParser();
        FileReader configFile;
        try {
            configFile = new FileReader(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JSONObject configOfCalendar;
        try {
            configOfCalendar = (JSONObject) parserOfCalendar.parse(configFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /**
         * Получаем день недели 1 дня 1 месяца 1 года
         */
        //TODO проверить, что такое приведение типов отработает вместо (String)
        this.anchorWeekDay = configOfCalendar.get(JsonFieldNames.anchorweekday.getFieldName()).toString();
        /**
         * Получаем год привязки(из-за исключений в григорианском календаре)
         */
        //TODO проверить, что такое приведение типов отработает вместо (Long).... .intValue()
        this.anchorYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.anchorYear.getFieldName()).toString());
        /**
         * Получаем список годов
         */
        this.yearList = new ArrayList<>();
        JSONArray yearArray = (JSONArray) configOfCalendar.get(JsonFieldNames.yearList.getFieldName());
        for(int iterator=0; iterator<yearArray.size(); iterator++) {
            this.yearList.add(new JsonYearConfig((JSONObject) yearArray.get(iterator)));
        }
        /**
         * Получаем неделю
         */
        this.week = new JsonWeekConfig((JSONObject) configOfCalendar.get(JsonFieldNames.week.getFieldName()));
    }

    @Override
    public String getAnchorWeekDay() {
        return anchorWeekDay;
    }

    @Override
    public List<IYearTemplate> getYearList() {
        return yearList;
    }

    @Override
    public IWeekTemplate getWeek() {
        return week;
    }

    @Override
    public Integer getAnchorYear() {
        return anchorYear;
    }
}
