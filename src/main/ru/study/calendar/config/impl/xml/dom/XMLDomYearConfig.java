package ru.study.calendar.config.impl.xml.dom;

import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.calendar.errors.errorTypes.XmlСonfigurationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
@Getter
public class XMLDomYearConfig implements IYearTemplate {
    /**
     * Список месяцев в году
     */
    private final List<IMonthTemplate> monthList;
    /**
     * Количество дней в году
     */
    private final Integer dayQuantity;

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @param yearConfig, Объект JSON конфига, хранящий информацию о годе
     * @throws Exception
     */
    XMLDomYearConfig(Node yearConfig) {
        this.monthList = new ArrayList<>();
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
                        IMonthTemplate month = new XMLDomMonthConfig(monthConfig);
                        if (!this.monthList.contains(month)) {
                            this.monthList.add(month);
                        } else {
                            throw new XmlСonfigurationException("Не уникальное название месяца: " + month.getName());
                        }
                    }
                }
            }

        }
        this.dayQuantity = monthList.stream().map(month -> month.getDayCount()).reduce((sum, add) -> sum + add).get();
    }
}
