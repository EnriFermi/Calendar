package ru.study.webapp.model.configuration.parsers.impl.json.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.enums.JdbcFieldNames;
import ru.study.webapp.model.configuration.parsers.impl.json.json.enums.JsonFieldNames;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.IOConfigurationException;
import ru.study.webapp.model.exceptions.JsonParsingException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonConfigurationParser implements ConfigurationParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        CalendarTemplate calendarTemplate = new CalendarTemplate();
        JSONParser parserOfCalendar = new JSONParser();
        FileReader configFile;
        try {
            configFile = new FileReader(configPath);
        } catch (FileNotFoundException e) {
            throw new IOConfigurationException(e);
        }
        JSONObject configOfCalendar;
        try {
            configOfCalendar = (JSONObject) parserOfCalendar.parse(configFile);
        } catch (ParseException e) {
            throw new JsonParsingException(e);
        } catch (IOException e) {
            throw new IOConfigurationException(e);
        }
        // Получаем день недели 1 дня 1 месяца 1 года
        calendarTemplate.setAnchorWeekDay(JsonDayConfigurationParser.parse((JSONObject) configOfCalendar.get(JsonFieldNames.ANCHOR_WEEKDAY.getFieldName())));
        // Год начала допустимого интервала календаря
        calendarTemplate.setBeginningYear(Integer.valueOf(configOfCalendar.get(JsonFieldNames.BEGINNING_YEAR.getFieldName())
                .toString()));
        // Год конца допустимого интервала календаря
        calendarTemplate.setEndYear(Integer.valueOf(configOfCalendar.get(JsonFieldNames.END_YEAR.getFieldName()).toString()));
        // Получаем список годов
        JSONArray yearArray = (JSONArray) configOfCalendar.get(JsonFieldNames.YEAR_LIST.getFieldName());
        yearArray.stream().map(jsonObject -> Integer.valueOf(((JSONObject) jsonObject).get(JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName()).toString()));
        for (Object year:yearArray) {
            calendarTemplate.addYear( JsonYearConfigurationParser.parse((JSONObject) year));
        }
        // Получаем неделю
        calendarTemplate.setWeek(JsonWeekConfigurationParser.parse((JSONObject) configOfCalendar.get(JsonFieldNames.WEEK.getFieldName())));
        return calendarTemplate;
    }
}
