package ru.study.calendar.config.impl.xml.dom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.calendar.errors.errorTypes.XmlСonfigurationException;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class XMLDomMonthConfig implements IMonthTemplate {
    /**
     * Имя месяца
     */
    @EqualsAndHashCode.Include private String name;
    /**
     * Количество дней в месяце
     */
    private Integer dayCount;
    /**
     * Список дополнительных нерабочих дней
     */
    private List<Integer> dayWorkOutList;
    /**
     * Список дополнительных рабочих дней
     */
    private List<Integer> dayWorkList;

    /**
     * Конструктор месяца по передаваемому JSON конфигу
     * @param monthConfig Объект JSON конфига, хранящий информацию о конкретном месяце
     * @throws Exception
     */
    XMLDomMonthConfig(Node monthConfig) {
        NodeList monthAttributeList = monthConfig.getChildNodes();
        this.dayWorkList = new ArrayList<>();
        this.dayWorkOutList = new ArrayList<>();
        for (Integer i=0; i<monthAttributeList.getLength(); i++) {
            Node monthAttribute = monthAttributeList.item(i);
            if (monthAttribute.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            // Настраиваем имя месяца
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.NAME_OF_MONTH.getFieldName())) {
                name = monthAttribute.getTextContent();
                continue;
            }
            // Настраиваем количество дней
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.DAY_COUNT.getFieldName())) {
                dayCount = Integer.valueOf(monthAttribute.getTextContent().toString());
            }
            /*
             * Настраиваем список рабочих дней
             */
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.DAY_WORK_LIST.getFieldName())) {
                setWorkList(monthAttribute);
            }
            /*
             * Настраиваем список нерабочих дней
             */
            if (monthAttribute.getNodeName().equals(XMLDomFieldNames.DAY_WORKOUT_LIST.getFieldName())) {
                setWorkOutList(monthAttribute);
            }
        }
    }
    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthAttribute Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private void setWorkList(Node monthAttribute) {
        NodeList workList = monthAttribute.getChildNodes();
        for(Integer j=0; j<workList.getLength(); j++) {
            Node workDay = workList.item(j);
            if(workDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if(workDay.getNodeName().equals(XMLDomFieldNames.DATE_OF_WORK_DAY.getFieldName())) {
                Integer date = Integer.valueOf(workDay.getTextContent());
                if(date > dayCount) {
                    throw new XmlСonfigurationException("Номер дополнительного рабочего дня за границей допустимых значений");
                }
                if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
                    throw new XmlСonfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
                }
                dayWorkList.add(date);
            }
        }
    }
    /**
     * Получает из конфига месяца список дополнительных нерабочих дней
     * @param monthAttribute Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    private void setWorkOutList(Node monthAttribute) {
        NodeList workOutList = monthAttribute.getChildNodes();
        for(Integer j=0; j<workOutList.getLength(); j++) {
            Node workOutDay = workOutList.item(j);
            if(workOutDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if(workOutDay.getNodeName().equals(XMLDomFieldNames.DATE_OF_WORKOUT_DAY.getFieldName())) {
                Integer date = Integer.valueOf(workOutDay.getTextContent());
                System.out.println(date);
                if(date > dayCount) {
                    throw new XmlСonfigurationException("Номер дополнительного нерабочего дня за границей допустимых значений");
                }
                if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
                    throw new XmlСonfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
                }
                dayWorkOutList.add(date);
            }
        }
    }
}
