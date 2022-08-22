package ru.study.calendar.config.parsers;

import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.exceptions.ConfigurationException;

public interface ConfigParser {
    public ICalendarTemplateForReading parse(String configPath) throws ConfigurationException;
}
