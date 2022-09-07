package ru.study.calendar.config.parsers.impl.xml.jaxb;

import org.mapstruct.factory.Mappers;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.xml.xsd.CalendarConfig;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.JaxbParsingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JaxbConfigParser implements ConfigParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        File file = new File(configPath);
        try {
            JAXBContext context = JAXBContext.newInstance(CalendarConfig.class);
            CalendarConfig calendarConfig = new CalendarConfig();
            try {
                InputStream is = new FileInputStream(file);
                JAXBElement<CalendarConfig> jaxb = context.createUnmarshaller().unmarshal(new StreamSource(is), CalendarConfig.class);
                calendarConfig = jaxb.getValue();
            } catch (FileNotFoundException e) {
                throw new JaxbParsingException(e);
            }
            JaxbCalendarConfigMapper mapper
                    = Mappers.getMapper(JaxbCalendarConfigMapper.class);
            return mapper.calendarMapper(calendarConfig);
        } catch (JAXBException e) {
            throw new JaxbParsingException(e);
        }
    }
}
