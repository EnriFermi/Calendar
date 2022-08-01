package ru.study.calendar.config.impl.json;

import lombok.Getter;
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

@Getter
public class JsonCalendarConfig implements ICalendarConfig {
    private final JsonDayConfig anchorWeekDay;
    private final List<IYearTemplate> yearList;
    private final IWeekTemplate week;
    private final Integer beginningYear;
    private final Integer endYear;

    //TODO п.3 от 1 августа 2022
    public JsonCalendarConfig(String path) {
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
        //TODO не красиво п.2 1.08.22
        /**
         * Получаем день недели 1 дня 1 месяца 1 года
         */
        this.anchorWeekDay = new JsonDayConfig((JSONObject) configOfCalendar.get(JsonFieldNames.anchorWeekDay.getFieldName()));
        /**
         * Год начала допустимого интервала календаря
         */
        this.beginningYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.beginningYear.getFieldName())
                                                             .toString());
        /**
         * Год конца допустимого интервала календаря
         */
        this.endYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.endYear.getFieldName()).toString());
        /**
         * Получаем список годов
         */
        this.yearList = new ArrayList<>();
        JSONArray yearArray = (JSONArray) configOfCalendar.get(JsonFieldNames.yearList.getFieldName());
        for (int iterator = 0; iterator < yearArray.size(); iterator++) {
            this.yearList.add(new JsonYearConfig((JSONObject) yearArray.get(iterator)));
        }
        /**
         * Получаем неделю
         */
        this.week = new JsonWeekConfig((JSONObject) configOfCalendar.get(JsonFieldNames.week.getFieldName()));
    }
}
