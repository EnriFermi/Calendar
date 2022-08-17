package ru.study.calendar.config.impl.xml.dom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.impl.xml.dom.enums.XMLDomFieldNames;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class XMLDomDayConfig implements IDayTemplate{
    /**
     * Название дня недели
     */
    @Getter @EqualsAndHashCode.Include private String dayName;
    /**
     * Указание рабочий ли день
     */
    private Boolean weekDayWorkOut;

    /**
     * Конструктор дня недели по передаваемому JSON конфигу
     * @param dayConfig Объект JSON конфига, хранящий информацию о конкретном дне
     */
    XMLDomDayConfig(Node dayConfig){
        NodeList listDayConfig = dayConfig.getChildNodes();
        for(Integer i=0; i< listDayConfig.getLength(); i++) {

            Node attributeDay = listDayConfig.item(i);
            if (attributeDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (attributeDay.getNodeName().equals(XMLDomFieldNames.DAY_NAME.getFieldName())) {
                dayName = attributeDay.getTextContent();
                continue;
            }

            if (attributeDay.getNodeName().equals(XMLDomFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                weekDayWorkOut = Boolean.valueOf(attributeDay.getTextContent());
                continue;
            }
        }
    }

    public XMLDomDayConfig(String dayName, Boolean weekDayWorkOut) {
    }

    /**
     * Метод получения информации рабочий ли день
     * @return Возвращает boolean значение рабочий ли день
     */
    @Override
    public Boolean isDefaultDayWorkOut() {
        return this.weekDayWorkOut;
    }
}
