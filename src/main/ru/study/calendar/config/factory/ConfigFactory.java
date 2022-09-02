package ru.study.calendar.config.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.config.domain.CalendarTemplate;
import ru.study.calendar.config.factory.enums.ConfigTypeEnum;
import ru.study.calendar.config.parsers.ConfigParser;
import ru.study.calendar.config.parsers.impl.xml.jaxb.JaxbConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.JdbcConfigParser;
import ru.study.calendar.config.parsers.impl.json.JsonConfigParser;
import ru.study.calendar.config.parsers.impl.xml.dom.XMLDomConfigParser;
import ru.study.calendar.config.parsers.impl.xml.sax.XMLSaxConfigParser;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.ConfigurationFactoryException;

import java.util.Arrays;
import java.util.List;

/**
 * Обработчик получения информации из конфигов, инициализирует требуемый парсер
 */
public interface ConfigFactory {
    /**
     * По типу получения конфига выдает объект Конфигурации календаря
     * @param configPath Путь к файлу конфигурации
     * @param configType Тип получения информации. Формат(тип_получения.расширение_файла)
     * @return Объект Конфигурации календаря
     * @throws ConfigurationException
     */
    static CalendarTemplate getCalendarTemplate(String configPath, ConfigTypeEnum configType) throws ConfigurationException {
        Logger log = LoggerFactory.getLogger(ConfigFactory.class);
        try {
            return configSwitch(configType).parse(configPath+ "." +configType.getTypeOfFile());
        } catch (ConfigurationFactoryException e) {
            log.info(e.getMessage());
            return defaultHandler(configPath, log);
        }
    }

    /**
     * Вызывает нужный парсер
     * @param configType Тип парсера
     * @return Объект парсера
     * @throws ConfigurationException
     */
    private static ConfigParser configSwitch(ConfigTypeEnum configType) throws ConfigurationException {
        switch (configType) {
            case JDBC:
                return new JdbcConfigParser();
            case DOM:
                return new XMLDomConfigParser();
            case SAX:
                return new XMLSaxConfigParser();
            case JAXB:
                return new JaxbConfigParser();
            case JSON:
                return new JsonConfigParser();
            case AUTO:
                throw new ConfigurationFactoryException("Автоматический выбор типа");
            default:
                throw new ConfigurationException("Отсутствует тип конфигурации");
        }
    }

    /**
     * Обработчик по умолчанию
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
