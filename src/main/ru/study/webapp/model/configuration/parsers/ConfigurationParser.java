package ru.study.webapp.model.configuration.parsers;

import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.exceptions.model.ConfigurationException;

/**
 * Шаблон парсера
 */
public interface ConfigurationParser {
    /**
     * Парсит конфиг по заданному адресу
     *
     * @param configPath Адрес конфига
     * @return Объект Конфигурации календаря
     * @throws ConfigurationException
     */
    CalendarTemplate parse(String configPath) throws ConfigurationException;
}
