package ru.study.calendar.trash;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.domain.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigFactory;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.impl.Calendar;

/**
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public class ConfigTest {

    @Test
    @ParameterizedTest
    @CsvFileSource(resources = "/testResources.csv")
    public void Test(String configPath, String result) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);

        try {
            ICalendarTemplateForReading calendarConfig = ConfigFactory.getCalendarTemplate(configPath, "json.json");
            ICalendar calendar = new Calendar(2022, calendarConfig);
            log.info(calendar.getWeekDay(8, "August").getWeekDay());
            log.info("В году всего " + calendar.getDayInYearCount() + " дней");

        } catch (Exception err) {
            Assert.assertTrue(err.getMessage().contains(result));
        }
    }
}
