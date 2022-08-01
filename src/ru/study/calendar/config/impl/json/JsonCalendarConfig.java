package ru.study.calendar.config.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.study.calendar.config.ICalendarConfig;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonCalendarConfig implements ICalendarConfig {
    private JsonDayConfig anchorWeekDay;
    private List<IYearTemplate> yearList;
    private IWeekTemplate week;
    private Integer beginningYear;
    private Integer endYear;
    //TODO 9. ReT Сделать красивые обработки ?
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
        //TODO проверить, что такое приведение типов отработает вместо (String) DONE
        this.anchorWeekDay = new JsonDayConfig((JSONObject) configOfCalendar.get(JsonFieldNames.anchorWeekDay.getFieldName()));
        /**
         * Получаем год привязки(из-за исключений в григорианском календаре)
         */
        //TODO проверить, что такое приведение типов отработает вместо (Long).... .intValue() DONE
        /**
         * Год начала допустимого интервала календаря
         */
        this.beginningYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.beginningYear.getFieldName()).toString());
        /**
         * Год конца допустимого интервала календаря
         */
        this.endYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.endYear.getFieldName()).toString());
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
    public IDayTemplate getAnchorWeekDay() {
        return this.anchorWeekDay;
    }

    @Override
    public List<IYearTemplate> getYearList() {
        return this.yearList;
    }

    @Override
    public IWeekTemplate getWeek() {
        return this.week;
    }


    @Override
    public Integer getBeginningYear() {
        return this.beginningYear;
    }

    @Override
    public Integer getEndYear() {
        return this.endYear;
    }
}
