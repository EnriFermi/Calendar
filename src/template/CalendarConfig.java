package template;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ret.ICalendarConfig;
import ret.IWeekTemplate;
import ret.IYearTemplate;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CalendarConfig implements ICalendarConfig {
    private String anchorWeekDay;
    private Integer anchorYear;
    private List<IYearTemplate> yearList;
    private IWeekTemplate week;
    public CalendarConfig(String path){
        FieldsNames field = FieldsNames.anchorWeekDay;
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
        this.anchorWeekDay = (String) configOfCalendar.get(field.getFieldName());
        /**
         * Получаем год привязки(из-за исключений в григорианском календаре)
         */
        field = FieldsNames.anchorYear;
        this.anchorYear = ((Long) configOfCalendar.get(field.getFieldName())).intValue();
        /**
         * Получаем список годов
         */
        this.yearList = new ArrayList<IYearTemplate>();
        field = FieldsNames.yearList;
        JSONArray yearArray = (JSONArray) configOfCalendar.get(field.getFieldName());
        for(int iterator=0; iterator<yearArray.size(); iterator++) {
            this.yearList.add(new YearTemplate((JSONObject) yearArray.get(iterator)));
        }
        /**
         * Получаем неделю
         */
        field = FieldsNames.week;
        this.week = new WeekTemplate((JSONObject) configOfCalendar.get(field.getFieldName()));
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
