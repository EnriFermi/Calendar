package ru.study.calendar.config.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.impl.jaxb.JaxbConfigParser;
import ru.study.calendar.config.parsers.impl.jdbc.JdbcConfigParser;
import ru.study.calendar.config.parsers.impl.json.JsonConfigParser;
import ru.study.calendar.config.parsers.impl.xml.dom.XMLDomConfigParser;
import ru.study.calendar.config.parsers.impl.xml.sax.XMLSaxConfigParser;
import ru.study.calendar.exceptions.ConfigurationException;
import ru.study.calendar.exceptions.ConfigurationFactoryException;

import java.util.Arrays;
import java.util.List;

public interface ConfigFactory {
    static ICalendarTemplateForReading getCalendarTemplate(String configPath, String configType) throws ConfigurationException {
        Logger log = LoggerFactory.getLogger(ConfigFactory.class);
        //TODO марафет DONE
        try {
            return configSwitch(configType).parse(configPath+ "." +configType.split("\\.")[1]);
        } catch (ConfigurationFactoryException e) {
            log.info(e.getMessage());
            return defaultHandler(configPath, log);
        }
    }
    static ConfigParser configSwitch(String configType) throws ConfigurationException {
        switch (configType) {
            case "jdbc.xml":
                return new JdbcConfigParser();
            case "dom.xml":
                return new XMLDomConfigParser();
            case "sax.xml":
                return new XMLSaxConfigParser();
            case "jaxb.xml":
                return new JaxbConfigParser();
            case "json.json":
                return new JsonConfigParser();
            case "auto":
                throw new ConfigurationFactoryException("Автоматический выбор типа");
            default:
                throw new ConfigurationException("Отсутствует тип конфигурации");
        }
    }
    static ICalendarTemplateForReading defaultHandler(String configPath, Logger log) throws ConfigurationException {
        List<String> list = Arrays.stream(new String[] {"dom.xml", "sax.xml", "json.json", "jaxb.xml", "jdbc"}).toList();
        for (String configType:list) {
            try {
                return configSwitch(configType).parse(configPath + "." + configType.split("\\.")[1]);
            } catch (ConfigurationException e) {
                log.warn("ConfigType: " + configType + "\n Error: ", e);
            }
        }
        throw new ConfigurationException("Не удалось инициализировать календарь");
    }
}