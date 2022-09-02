package ru.study.calendar.config.parsers;

import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.exceptions.ConfigurationException;

/**
 * Шаблон парсера
 */
public interface ConfigParser {
    /**
     * Парсит конфиг по заданному адресу
     *
     * @param configPath Адрес конфига
     * @return Объект Конфигурации календаря
     * @throws ConfigurationException
     */
    public CalendarTemplate parse(String configPath) throws ConfigurationException;
}
