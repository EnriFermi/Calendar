package ru.study.calendar.trash;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.CalendarMain;
import ru.study.calendar.config.body.impl.CalendarTemplate;
import ru.study.calendar.config.parsers.impl.jaxb.CalendarConfigMapper;
import ru.study.calendar.config.parsers.impl.jaxb.JaxbCalendarConfig;

import javax.xml.bind.JAXBContext;
import java.io.File;

public class XMLSaxClass {
    @Test
    public void Test() {
        Logger log = LoggerFactory.getLogger(CalendarMain.class);
        try {

            System.out.println(jaxbCalendarConfig.getJaxbWeek().getJaxbWeekDayNameList().getJaxbWeekDayConfigList().get(0).getWeekDayWorkOut());
        } catch (Exception e) {

            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
