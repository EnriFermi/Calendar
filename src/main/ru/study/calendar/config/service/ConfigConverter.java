package ru.study.calendar.config.service;

import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.exceptions.ConfigurationException;

public interface ConfigConverter {
    public void convert(ConfigParser configParser, String configPathFrom, String configPathTo) throws ConfigurationException;
}
