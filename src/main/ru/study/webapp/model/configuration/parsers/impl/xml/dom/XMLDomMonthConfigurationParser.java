package ru.study.webapp.model.configuration.parsers.impl.xml.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.webapp.model.configuration.domain.MonthTemplate;
import ru.study.webapp.model.configuration.parsers.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.webapp.model.exceptions.ConfigurationException;
import ru.study.webapp.model.exceptions.XmlDomParsingException;

/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */

public class XMLDomMonthConfigurationParser {
    /**
     * Имя месяца
     */
    /**
     * Количество дней в месяце
     */
    /**
     * Список дополнительных нерабочих дней
     */
    /**
     * Список дополнительных рабочих дней
     */

    /**
     * Конструктор месяца по передаваемому JSON конфигу
     * @throws Exception
     */
    protected static MonthTemplate parse(Node monthConfig) throws XmlDomParsingException {
        NodeList monthAttributeList = monthConfig.getChildNodes();
        MonthTemplate
                monthTemplate = new MonthTemplate();
        for (Integer i=0; i<monthAttributeList.getLength(); i++) {
            Node monthAttribute = monthAttributeList.item(i);
            if (monthAttribute.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            // Настраиваем имя месяца
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.NAME_OF_MONTH.getFieldName())) {
                monthTemplate.setName(monthAttribute.getTextContent());
                continue;
            }
            // Настраиваем количество дней
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.DAY_COUNT.getFieldName())) {
                monthTemplate.setDayCount(Integer.valueOf(monthAttribute.getTextContent()));
            }
            /*
             * Настраиваем список рабочих дней
             */
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.DAY_WORK_LIST.getFieldName())) {
                monthTemplate = setWorkList(monthAttribute, monthTemplate);
            }
            /*
             * Настраиваем список нерабочих дней
             */
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.DAY_WORKOUT_LIST.getFieldName())) {
                monthTemplate = setWorkOutList(monthAttribute, monthTemplate);
            }
        }
        return monthTemplate;
    }
    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthAttribute Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private static MonthTemplate setWorkList(Node monthAttribute, MonthTemplate monthTemplate) throws XmlDomParsingException {
        NodeList workList = monthAttribute.getChildNodes();
        for(Integer j=0; j<workList.getLength(); j++) {
            Node workDay = workList.item(j);
            if(workDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if(workDay.getNodeName().equals(XMLDomFieldNames.DATE_OF_WORK_DAY.getFieldName())) {
                try {
                    monthTemplate.addWorkDay(Integer.valueOf(workDay.getTextContent()));
                } catch (ConfigurationException e) {
                    throw new XmlDomParsingException(e);
                }
            }
        }
        return monthTemplate;
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     * @param monthAttribute Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private static MonthTemplate setWorkOutList(Node monthAttribute, MonthTemplate month) throws XmlDomParsingException {
        NodeList workOutList = monthAttribute.getChildNodes();
        for(Integer j=0; j<workOutList.getLength(); j++) {
            Node workOutDay = workOutList.item(j);
            if(workOutDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if(workOutDay.getNodeName().equals(XMLDomFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())) {
                try {
                    month.addWorkOutDay(Integer.valueOf(workOutDay.getTextContent()));
                } catch (ConfigurationException e) {
                    throw new XmlDomParsingException(e);
                }
            }
        }
        return month;
    }
}
