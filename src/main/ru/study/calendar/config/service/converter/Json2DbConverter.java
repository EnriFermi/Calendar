package ru.study.calendar.config.service.converter;

import ru.study.calendar.config.domain.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.json.JsonConfigParser;
import ru.study.calendar.config.service.ConfigConverter;
import ru.study.calendar.exceptions.ConfigurationException;

public class Json2DbConverter implements ConfigConverter {
    @Override
    public void convert(String configPathJson, String configPathDb) throws ConfigurationException {
        ConfigParser configParser = new JsonConfigParser();
        ICalendarTemplateForReading calendarTemplate = configParser.parse(configPathJson);
        Template2DbConverter.convert(calendarTemplate, configPathDb);
    }
}
