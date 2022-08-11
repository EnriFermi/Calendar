package ru.study.calendar.config.impl.json;

import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.study.calendar.config.ICalendarTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.enums.JsonFieldNames;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий хранение данных о конфигурации календаря
 */
@Getter
public class JsonCalendarConfig implements ICalendarTemplate {
    /**
     * День первого числа первого месяца привязочного года
     */
    private final ru.study.calendar.config.impl.json.JsonDayConfig anchorWeekDay;
    /**
     * Список шаблонов лет
     */
    private final List<IYearTemplate> yearList;
    /**
     * Шаблон недели
     */
    private final IWeekTemplate week;
    /**
     * Год начала допустимого интервала календаря
     */
    private final Integer beginningYear;
    /**
     * Год конца допустимого интервала календаря
     */
    private final Integer endYear;

    /**
     * Конструктор календаря по передаваемому пути к конфигу
     *
     * @param path Путь к конфигу
     * @throws Exception
     */

    //TODO сделать конкретные Exception DONE
    public JsonCalendarConfig(String path) throws Exception {
        JSONParser parserOfCalendar = new JSONParser();
        FileReader configFile;
        configFile = new FileReader(path);
        JSONObject configOfCalendar;
        configOfCalendar = (JSONObject) parserOfCalendar.parse(configFile);

        // Получаем день недели 1 дня 1 месяца 1 года
        this.anchorWeekDay = new JsonDayConfig((JSONObject) configOfCalendar.get(JsonFieldNames.ANCHOR_WEEKDAY.getFieldName()));
        // Год начала допустимого интервала календаря
        this.beginningYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.BEGINNING_YEAR.getFieldName())
                                                             .toString());
        // Год конца допустимого интервала календаря
        this.endYear = Integer.valueOf(configOfCalendar.get(JsonFieldNames.END_YEAR.getFieldName()).toString());
        // Получаем список годов
        this.yearList = new ArrayList<>();
        JSONArray yearArray = (JSONArray) configOfCalendar.get(JsonFieldNames.YEAR_LIST.getFieldName());
        //TODO check yearArray.forEach(); DONE
        yearArray.forEach(year -> {
            this.yearList.add(new JsonYearConfig((JSONObject) year));
        });
        // Получаем неделю
        this.week = new JsonWeekConfig((JSONObject) configOfCalendar.get(JsonFieldNames.WEEK.getFieldName()));
    }
}
