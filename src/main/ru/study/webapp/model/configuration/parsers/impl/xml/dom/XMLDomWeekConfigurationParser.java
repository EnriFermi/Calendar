package ru.study.webapp.model.configuration.parsers.impl.xml.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.webapp.model.configuration.domain.WeekTemplate;
import ru.study.webapp.model.configuration.parsers.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.XmlDomParsingException;

/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
public class XMLDomWeekConfigurationParser {
    /**
     * Количество дней в неделе
     */
    /**
     * Список дней недели
     */
    /**
     * Конструктор недели по передаваемому JSON конфигу
     */

    protected static WeekTemplate parse(Node weekConfig) throws XmlDomParsingException {
        WeekTemplate weekTemplate = new WeekTemplate();
        NodeList weekConfigList = weekConfig.getChildNodes();
        for(Integer i=0; i<weekConfigList.getLength(); i++) {
            Node weekAttribute = weekConfigList.item(i);
            if (weekAttribute.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (weekAttribute.getNodeName().equals(XMLDomFieldNames.WEEKDAY_NAME_LIST.getFieldName())) {
                NodeList weekDayList = weekAttribute.getChildNodes();
                for (Integer j=0; j<weekDayList.getLength(); j++){
                    Node weekDay = weekDayList.item(j);
                    if (weekDay.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (weekDay.getNodeName().equals(XMLDomFieldNames.WEEKDAY_NAME.getFieldName())) {
                        try {
                            weekTemplate.addWeekDay(XMLDomDayConfigurationParser.parse(weekDay));
                        } catch (ConfigurationException e) {
                            throw new XmlDomParsingException(e);
                        }
                    }
                }
            }
        }
        return weekTemplate;
    }
}
