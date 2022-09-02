package ru.study.calendar.config.parsers.impl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.study.calendar.config.domain.impl.*;
import ru.study.calendar.config.parsers.impl.jdbc.enums.JdbcFieldNames;
import ru.study.calendar.config.parsers.impl.json.enums.JsonFieldNames;

import java.util.List;
import java.util.stream.Stream;

//TODO почитать про параметры , всегда советую использовать ReportingPolicy.ERROR DONE
@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, imports = {JsonFieldNames.class,
        JSONObject.class, JSONArray.class})
public abstract class CalendarConfigMapper {

    @Mapping(target = "anchorWeekDay", expression = "java(dayMapper((JSONObject) configOfCalendar" +
            ".get(JsonFieldNames.ANCHOR_WEEKDAY.getFieldName())))")
    //TODO аналогично переделать, и заменить название метода DONE
    @Mapping(target = "yearList", expression = "java(((JSONArray) configOfCalendar" +
            ".get(JsonFieldNames.YEAR_LIST.getFieldName())).stream().toList())")
    @Mapping(target = "beginningYear", expression = "java(Integer.valueOf(configOfCalendar" +
            ".get(JsonFieldNames.BEGINNING_YEAR.getFieldName()).toString()))")
    @Mapping(target = "endYear", expression = "java(Integer.valueOf(configOfCalendar" +
            ".get(JsonFieldNames.END_YEAR.getFieldName()).toString()))")
    @Mapping(target = "week", expression = "java(weekMapper((JSONObject) configOfCalendar" +
            ".get(JsonFieldNames.WEEK.getFieldName())))")
    public abstract CalendarTemplate calendarMapper(JSONObject configOfCalendar);

    @Mapping(target = "monthList", expression = "java(((JSONArray) yearConfig.get(JsonFieldNames.MONTH_LIST.getFieldName()))" +
            ".stream().toList())")
    @Mapping(target = "dayQuantity", constant = "0")
    public abstract YearTemplate yearMapper(JSONObject yearConfig);

    @Mapping(target = "name", expression = "java(monthConfig.get(JsonFieldNames.NAME_OF_MONTH.getFieldName()).toString())")
    @Mapping(target = "dayCount", expression = "java(Integer.valueOf(monthConfig" +
            ".get(JsonFieldNames.DAY_COUNT.getFieldName()).toString()))")
    //FIXME
    @Mapping(target = "dayWorkList", expression = "java(((JSONArray) monthConfig" +
            ".get(JsonFieldNames.DAY_WORK_LIST.getFieldName())).stream().toList().)")
    @Mapping(target = "dayWorkOutList", expression = "java(((JSONArray) monthConfig" +
            ".get(JsonFieldNames.DAY_WORKOUT_LIST.getFieldName())).stream().toList())")
    public abstract MonthTemplate monthMapper(JSONObject monthConfig);

    @Mapping(target = "dayName", expression = "java(dayConfig.get(JsonFieldNames.DAY_NAME.getFieldName()).toString())")
    @Mapping(target = "weekDayWorkOut", expression = "java(Boolean.valueOf(dayConfig" +
            ".get(JsonFieldNames.WEEKDAY_WORKOUT.getFieldName()).toString()))")
    public abstract DayTemplate dayMapper(JSONObject dayConfig);


    public List<Integer> setDate(Stream<Object> dateList) {
        return dateList.map(jsonObject -> Integer.valueOf(((JSONObject) jsonObject)
                .get(JdbcFieldNames.DATE_OF_WORK_DAY.getFieldName()).toString())).toList();
    }
    @Mapping(target = "weekDayNameList", expression = "java(((JSONArray) weekConfig" +
            ".get(JsonFieldNames.WEEKDAY_NAME_LIST.getFieldName())).stream().toList())")
    @Mapping(target = "weekDayCount", constant = "0")
    public abstract WeekTemplate weekMapper(JSONObject weekConfig);

}
