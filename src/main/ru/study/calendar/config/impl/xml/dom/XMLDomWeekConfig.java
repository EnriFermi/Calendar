package ru.study.calendar.config.impl.xml.dom;

import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.impl.xml.dom.enums.XMLDomFieldNames;
import ru.study.calendar.errors.errorTypes.XmlСonfigurationException;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
@Getter
public class XMLDomWeekConfig implements IWeekTemplate {
    /**
     * Количество дней в неделе
     */
    private Integer weekDayCount;
    /**
     * Список дней недели
     */
    private List<IDayTemplate> weekDayNameList;

    /**
     * Конструктор недели по передаваемому JSON конфигу
     * @param weekConfig Объект JSON конфига, хранящий информацию о неделе
     */
    XMLDomWeekConfig(Node weekConfig){
        weekDayNameList = new ArrayList<>();
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
                        IDayTemplate day = new XMLDomDayConfig(weekDay);
                        if (! weekDayNameList.contains(day)) {
                            weekDayNameList.add(day);
                        } else {
                            throw new XmlСonfigurationException("Не уникальное название дня недели: " + day.getDayName());
                        }
                    }
                }
            }
        }
        weekDayCount = weekDayNameList.size();
    }
}
