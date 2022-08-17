package ru.study.calendar.trash;


import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.impl.Calendar;

/**
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
//TODO сделать тесты на импорт конфига, успешный и не успешный (для каждого вида ошибок, включая парсинг) DONE
public class ConfigTest {

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"testResources\\dateOfWorkOutDay_is_outOfRange",
            "testResources\\normalList",
            "testResources\\sameDayInWorkOutList_and_inWorkList",
            "testResources\\sameMonthName",
            "testResources\\sameWeekDayName",
            "testResources\\similarDays_in_dayWorkOutList"})
    public void Test(String configPath) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            ICalendar calendar = new Calendar(2021, "xml", configPath);
            System.out.println(calendar.getWeekDay(9, "August").isWorkingDay());
        } catch (Exception err) {
            log.error("Error: ", err);
            throw err;
        }
    }
}
