package ru.study.webapp.model.configuration.parsers.impl.xml.dom;

import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.webapp.model.configuration.domain.YearTemplate;
import ru.study.webapp.model.configuration.parsers.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.webapp.exceptions.model.ConfigurationException;
import ru.study.webapp.exceptions.model.XmlDomParsingException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
@Getter
public class XMLDomYearConfigurationParser {
    /**
     * Список месяцев в году
     */
    /**
     * Количество дней в году
     */

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @throws Exception
     */
    protected static YearTemplate parse(Node yearConfig) throws XmlDomParsingException {
        YearTemplate yearTemplate =  new YearTemplate();
        NodeList yearConfigList = yearConfig.getChildNodes();
        for (Integer i = 0; i < yearConfigList.getLength(); i++) {
            Node yearAttributeList = yearConfigList.item(i);
            if (yearAttributeList.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (yearAttributeList.getNodeName().equals(XMLDomFieldNames.MONTH_LIST.getFieldName())) {
                NodeList monthConfigList = yearAttributeList.getChildNodes();
                for (Integer j=0; j<monthConfigList.getLength(); j++) {
                    Node monthConfig = monthConfigList.item(j);
                    if (yearAttributeList.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (monthConfig.getNodeName().equals(XMLDomFieldNames.MONTH_CONFIG.getFieldName())) {
                        try {
                            yearTemplate.addMonth(XMLDomMonthConfigurationParser.parse(monthConfig));
                        } catch (ConfigurationException e) {
                            throw new XmlDomParsingException(e);
                        }
                    }
                }
            }
        }
        return yearTemplate;
    }
}
