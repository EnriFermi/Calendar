package ru.study.calendar.config.service.converter;

import ru.study.calendar.config.domain.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.xml.sax.XMLSaxConfigParser;
import ru.study.calendar.config.service.ConfigConverter;
import ru.study.calendar.exceptions.ConfigurationException;

public class Xml2DbConverter implements ConfigConverter {

    @Override
    //TODO на вход  ConfigParser + configPathXML + configPathDb
    public void convert(String configPathXML, String configPathDb) throws ConfigurationException {
        ConfigParser configParser = new XMLSaxConfigParser();
        ICalendarTemplateForReading calendarTemplate = configParser.parse(configPathXML);
        Template2DbConverter.convert(calendarTemplate, configPathDb);
    }

}
