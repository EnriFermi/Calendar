package ru.study.webapp.model.configuration.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.factory.enums.ConfigTypeEnum;
import ru.study.webapp.model.configuration.parsers.ConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.xml.dom.XMLDomConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.xml.jaxb.JaxbConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.db.jdbc.JdbcConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.json.json.JsonConfigurationParser;
import ru.study.webapp.model.configuration.parsers.impl.xml.sax.XMLSaxConfigParser;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.ConfigurationFactoryException;

import java.util.Arrays;
import java.util.List;

/**
 * Обработчик получения информации из конфигов, инициализирует требуемый парсер
 */
public interface ConfigurationFactory {
    /**
     * По типу получения конфига выдает объект Конфигурации календаря
     *
     * @param configPath Путь к файлу конфигурации
     * @param configType Тип получения информации. Формат(тип_получения.расширение_файла)
     * @return Объект Конфигурации календаря
     * @throws ConfigurationException
     */
    static CalendarTemplate getCalendarTemplate(String configPath, ConfigTypeEnum configType) throws ConfigurationException {
        Logger log = LoggerFactory.getLogger(ConfigurationFactory.class);
        try {
            return configSwitch(configType).parse(configPath+ "." +configType.getTypeOfFile());
        } catch (ConfigurationFactoryException e) {
            log.info(e.getMessage());
            return defaultHandler(configPath, log);
        }
    }

    /**
     * Вызывает нужный парсер
     *
     * @param configType Тип парсера
     * @return Объект парсера
     * @throws ConfigurationException
     */
    private static ConfigurationParser configSwitch(ConfigTypeEnum configType) throws ConfigurationException {
        switch (configType) {
            case JDBC:
                return new JdbcConfigurationParser();
            case DOM:
                return new XMLDomConfigurationParser();
            case SAX:
                return new XMLSaxConfigParser();
            case JAXB:
                return new JaxbConfigurationParser();
            case JSON:
                return new JsonConfigurationParser();
            case AUTO:
                throw new ConfigurationFactoryException("Автоматический выбор типа");
            default:
                throw new ConfigurationException("Отсутствует тип конфигурации");
        }
    }

    /**
     * Обработчик по умолчанию
     *
     * @param configPath Путь к файлу конфигурации
     * @param log Логгер
     * @return Объект Конфигурации календаря
     * @throws ConfigurationException
     */
    private static CalendarTemplate defaultHandler(String configPath, Logger log) throws ConfigurationException {
        List<ConfigTypeEnum> list = Arrays.stream(new ConfigTypeEnum[] {ConfigTypeEnum.DOM, ConfigTypeEnum.SAX,
                ConfigTypeEnum.JSON, ConfigTypeEnum.JAXB, ConfigTypeEnum.JDBC}).toList();
        for (ConfigTypeEnum configType:list) {
            try {
                return configSwitch(configType).parse(configPath + "." + configType.getTypeOfFile());
            } catch (ConfigurationException e) {
                log.warn("ConfigType: " + configType + "\n Error: ", e);
            }
        }
        throw new ConfigurationException("Не удалось инициализировать календарь");
    }
}
