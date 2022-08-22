package ru.study.calendar.config.parsers;

import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.errors.errorTypes.ConfigurationException;

public interface ConfigParser {
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException;
}
