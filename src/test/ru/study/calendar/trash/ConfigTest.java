package ru.study.calendar.trash;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.factory.ConfigFactory;
import ru.study.calendar.config.factory.enums.ConfigTypeEnum;
import ru.study.calendar.item.impl.Calendar;
import ru.study.calendar.item.impl.CalendarService;

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
            CalendarTemplate calendarConfig = ConfigFactory.getCalendarTemplate(configPath, ConfigTypeEnum.JSON);
            Calendar calendar = new Calendar(2022, calendarConfig);
            log.info(CalendarService.getWeekDay(calendar, 10, "August").getWeekDay());

        } catch (Exception err) {
            Assert.assertTrue(err.getMessage().contains(result));
        }
    }
}
