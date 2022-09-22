package ru.study.calendar.trash;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.webapp.model.CalendarMain;
import ru.study.webapp.model.body.domain.Calendar;
import ru.study.webapp.model.body.services.CalendarService;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.factory.ConfigurationFactory;
import ru.study.webapp.model.configuration.factory.enums.ConfigTypeEnum;

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
            CalendarTemplate calendarConfig = ConfigurationFactory.getCalendarTemplate(configPath, ConfigTypeEnum.JSON);
            Calendar calendar = new Calendar(2022, calendarConfig);
            log.info(CalendarService.getWeekDay(calendar, 10, "August").getWeekDay());

        } catch (Exception err) {
            Assert.assertTrue(err.getMessage().contains(result));
        }
    }
}
