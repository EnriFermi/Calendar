package ru.study.calendar.config.parsers;

import ru.study.calendar.config.body.inter.reading.ICalendarTemplateForReading;
import ru.study.calendar.config.parsers.impl.jaxb.JaxbConfigParser;
import ru.study.calendar.config.parsers.impl.json.JsonConfigParser;
import ru.study.calendar.config.parsers.impl.xml.dom.XMLDomConfigParser;
import ru.study.calendar.config.parsers.impl.xml.sax.XMLSaxConfigParser;

public interface ConfigFactory {
    static ICalendarTemplateForReading getCalendarTemplate(String configPath, String configType) throws Exception {
        ConfigParser parser;
        switch (configType) {
            case "xml.dom":
                 parser = new XMLDomConfigParser();
                return parser.parse(configPath);
            case "xml.sax":
                parser =  new XMLSaxConfigParser();
                return parser.parse(configPath);
            case "jaxb":
                parser = new JaxbConfigParser();
                return parser.parse(configPath);
            default:
                parser = new JsonConfigParser();
                return parser.parse(configPath);
        }
    }
}
