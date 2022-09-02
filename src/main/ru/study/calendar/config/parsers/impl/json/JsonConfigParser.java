package ru.study.calendar.config.parsers.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.study.calendar.config.domain.impl.CalendarTemplate;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.config.parsers.impl.json.enums.JsonFieldNames;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.IOConfigurationException;
import ru.study.calendar.exceptions.JsonParsingException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonConfigParser implements ConfigParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        ru.study.calendar.config.domain.impl.CalendarTemplate calendarTemplate = new ru.study.calendar.config.domain.impl.CalendarTemplate();
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
        System.out.println(configOfCalendar);
        // Получаем день недели 1 дня 1 месяца 1 года
        calendarTemplate.setAnchorWeekDay(JsonDayConfigParser.parse((JSONObject) configOfCalendar.get(JsonFieldNames.ANCHOR_WEEKDAY.getFieldName())));
        // Год начала допустимого интервала календаря
        calendarTemplate.setBeginningYear(Integer.valueOf(configOfCalendar.get(JsonFieldNames.BEGINNING_YEAR.getFieldName())
                .toString()));
        // Год конца допустимого интервала календаря
        calendarTemplate.setEndYear(Integer.valueOf(configOfCalendar.get(JsonFieldNames.END_YEAR.getFieldName()).toString()));
        // Получаем список годов
        JSONArray yearArray = (JSONArray) configOfCalendar.get(JsonFieldNames.YEAR_LIST.getFieldName());
        yearArray.stream().map(jsonObject -> Integer.valueOf(((JSONObject) jsonObject).get(JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName()).toString()));
        for (Object year:yearArray) {
            calendarTemplate.addYear( JsonYearConfigParser.parse((JSONObject) year));
        }
        // Получаем неделю
        calendarTemplate.setWeek(JsonWeekConfigParser.parse((JSONObject) configOfCalendar.get(JsonFieldNames.WEEK.getFieldName())));
        return (CalendarTemplate) calendarTemplate;
    }
}
