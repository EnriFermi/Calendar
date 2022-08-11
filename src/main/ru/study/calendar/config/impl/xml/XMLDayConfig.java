package ru.study.calendar.config.impl.xml;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.impl.xml.enums.XMLFieldNames;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class XMLDayConfig implements IDayTemplate{
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
    XMLDayConfig(Node dayConfig){
        NodeList listDayConfig = dayConfig.getChildNodes();
        for(Integer i=0; i< listDayConfig.getLength(); i++) {

            Node attributeDay = listDayConfig.item(i);
            if (attributeDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (attributeDay.getNodeName().equals(XMLFieldNames.DAY_NAME.getFieldName())) {
                dayName = attributeDay.getTextContent();
                continue;
            }

            if (attributeDay.getNodeName().equals(XMLFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                weekDayWorkOut = Boolean.valueOf(attributeDay.getTextContent());
                continue;
            }
        }
    }

    public XMLDayConfig(String dayName, Boolean weekDayWorkOut) {
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
