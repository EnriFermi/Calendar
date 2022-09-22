package ru.study.webapp.model.configuration.parsers.impl.xml.jaxb;

import org.mapstruct.factory.Mappers;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.JaxbParsingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JaxbConfigurationParser implements ConfigurationParser {
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
            JaxbCalendarConfigurationMapper mapper
                    = Mappers.getMapper(JaxbCalendarConfigurationMapper.class);
            return mapper.calendarMapper(calendarConfig);
        } catch (JAXBException e) {
            throw new JaxbParsingException(e);
        }
    }
}
