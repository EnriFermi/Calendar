package ru.study.calendar.config.impl.xml.sax;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.errors.errorTypes.XmlСonfigurationException;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class XMLSaxMonthConfig implements IMonthTemplate {
    /**
     * Имя месяца
     */
    @EqualsAndHashCode.Include @Setter(AccessLevel.PROTECTED) private String name;
    /**
     * Количество дней в месяце
     */
    @Setter(AccessLevel.PROTECTED) private Integer dayCount;
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
    XMLSaxMonthConfig() {
        dayCount = 0;
        name = "";
        dayWorkOutList = new ArrayList<>();
        dayWorkList = new ArrayList<>();
    }
    /**
     * Получает из конфига месяца список дополнительных рабочих дней
     * @param monthAttribute Объект JSON конфига, хранящий информацию о конкретном месяце
     */
    protected void addWorkDay(Integer date) {
        if(date > dayCount) {
            throw new XmlСonfigurationException("Номер дополнительного рабочего дня за границей допустимых значений");
        }
        if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
            throw new XmlСonfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
        }
        dayWorkList.add(date);
    }
    protected void addWorkOutDay(Integer date) {
        if(date > dayCount) {
            throw new XmlСonfigurationException("Номер дополнительного нерабочего дня за границей допустимых значений");
        }
        if(dayWorkList.contains(date)||dayWorkOutList.contains(date)) {
            throw new XmlСonfigurationException("Коллапс имен при попытке инициализировать список дополнительных выходных и рабочих дней");
        }
        dayWorkOutList.add(date);
    }
    protected void resetMonth() {
        dayCount = 0;
        name = "";
        dayWorkOutList = new ArrayList<>();
        dayWorkList = new ArrayList<>();
    }

    public void clone(XMLSaxMonthConfig monthConstructor) {
        dayCount = monthConstructor.getDayCount();
        name = monthConstructor.getName();
        dayWorkOutList = monthConstructor.getDayWorkOutList();
        dayWorkList = monthConstructor.getDayWorkList();
    }
}
