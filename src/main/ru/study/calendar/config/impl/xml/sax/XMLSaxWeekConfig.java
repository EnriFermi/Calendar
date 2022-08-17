package ru.study.calendar.config.impl.xml.sax;

import lombok.Getter;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.errors.errorTypes.XmlСonfigurationException;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации недели в календаре
 */
@Getter
public class XMLSaxWeekConfig implements IWeekTemplate {
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
    XMLSaxWeekConfig() {
        weekDayCount = 0;
        weekDayNameList = new ArrayList<>();
    }
    protected void addWeekDay(IDayTemplate day) {
        if (! weekDayNameList.contains(day)) {
            weekDayNameList.add(day);
            weekDayCount++;
        } else {
            throw new XmlСonfigurationException("Не уникальное название дня недели: " + day.getDayName());
        }
    }
    protected void resetWeekConfig() {
        weekDayCount=0;
        weekDayNameList = new ArrayList<>();
    }

    public void clone(XMLSaxWeekConfig weekConstructor) {
        weekDayCount = weekConstructor.getWeekDayCount();
        weekDayNameList = weekConstructor.getWeekDayNameList();
    }
}
