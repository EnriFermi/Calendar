package ru.study.calendar.config.parsers.impl.xml.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.domain.impl.DayTemplate;
import ru.study.calendar.config.parsers.impl.xml.dom.enums.XMLDomFieldNames;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

public class XMLDomDayConfigParser {
    /**
     * Название дня недели
     */
    /**
     * Указание рабочий ли день
     */

    /**
     * Конструктор дня недели по передаваемому JSON конфигу
     * @param dayConfig Объект JSON конфига, хранящий информацию о конкретном дне
     */
    protected static DayTemplate parse(Node dayConfig){
        DayTemplate dayTemplate = new DayTemplate();
        NodeList listDayConfig = dayConfig.getChildNodes();
        for(Integer i=0; i< listDayConfig.getLength(); i++) {
            Node attributeDay = listDayConfig.item(i);
            if (attributeDay.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (attributeDay.getNodeName().equals(XMLDomFieldNames.DAY_NAME.getFieldName())) {
                dayTemplate.setDayName(attributeDay.getTextContent());
                continue;
            }

            if (attributeDay.getNodeName().equals(XMLDomFieldNames.WEEKDAY_WORKOUT.getFieldName())) {
                dayTemplate.setWeekDayWorkOut(Boolean.valueOf(attributeDay.getTextContent()));
                continue;
            }
        }
        return dayTemplate;
    }


    /**
     * Метод получения информации рабочий ли день
     * @return Возвращает boolean значение рабочий ли день
     */
}
