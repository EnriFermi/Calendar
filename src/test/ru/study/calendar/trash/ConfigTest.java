package ru.study.calendar.trash;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;

/**
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
//TODO сделать тесты на импорт конфига, успешный и не успешный (для каждого вида ошибок, включая парсинг) DONE
public class ConfigTest {

    @Test
    @ParameterizedTest
    @CsvFileSource(resources = "/testResources.csv")
    public void Test(String configPath, String result) throws Exception {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
        } catch (Exception err) {
            Assert.assertTrue(err.getMessage().contains(result));
            log.error("Error: ", err);
        }
    }
}
