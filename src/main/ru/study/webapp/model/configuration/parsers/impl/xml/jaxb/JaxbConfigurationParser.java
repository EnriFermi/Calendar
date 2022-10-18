package ru.study.webapp.model.configuration.parsers.impl.xml.jaxb;

import org.mapstruct.factory.Mappers;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.exceptions.ConfigurationException;
import ru.study.webapp.exceptions.JaxbParsingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class JaxbConfigurationParser implements ConfigurationParser {
    @Override
    public CalendarTemplate parse(String configPath) throws ConfigurationException {
        File file = new File(configPath);
        try {
            JAXBContext context = JAXBContext.newInstance(CalendarConfig.class);
            CalendarConfig calendarConfig = new CalendarConfig();
            StreamSource is = new StreamSource(file);
            calendarConfig = ((JAXBElement<CalendarConfig>) context.createUnmarshaller().unmarshal(is, CalendarConfig.class)).getValue();
            JaxbCalendarConfigurationMapper mapper
                    = Mappers.getMapper(JaxbCalendarConfigurationMapper.class);
            return mapper.calendarMapper(calendarConfig);
        } catch (JAXBException e) {
            throw new JaxbParsingException(e);
        }
    }
}
