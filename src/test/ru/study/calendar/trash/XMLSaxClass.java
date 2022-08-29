package ru.study.calendar.trash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.service.ConfigConverter;
import ru.study.calendar.config.service.converter.Xml2DbConverter;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {
            ConfigConverter converter = new Xml2DbConverter();
            converter.convert( "resources\\classicCalendar.xml","resources\\serverConfiguration.xml");

        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
