package ru.study.webapp.model.configuration.services.converters;

import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.exceptions.ConfigurationException;

public interface ConfigConverter {
    void convert(ConfigurationParser configParser, String configPathFrom, String configPathTo) throws ConfigurationException;
}
