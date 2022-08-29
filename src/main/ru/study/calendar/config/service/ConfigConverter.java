package ru.study.calendar.config.service;

import ru.study.calendar.exceptions.ConfigurationException;

public interface ConfigConverter {
    public void convert(String configPathFrom, String configPathTo) throws ConfigurationException;
}
