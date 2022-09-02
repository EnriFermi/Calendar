package ru.study.calendar.config.parsers.impl.xml.jaxb;

import org.mapstruct.factory.Mappers;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JaxbParsingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbConfigParser implements ConfigParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        JaxbCalendarConfig jaxbCalendarConfig;
        File file = new File(configPath);
        try {
            JAXBContext context = JAXBContext.newInstance(JaxbCalendarConfig.class);
            jaxbCalendarConfig = (JaxbCalendarConfig) context.createUnmarshaller().unmarshal(file);
            CalendarConfigMapper mapper
                    = Mappers.getMapper(CalendarConfigMapper.class);
            return mapper.calendarMapper(jaxbCalendarConfig);
        } catch (JAXBException e) {
            throw new JaxbParsingException(e);
        }
    }
}
