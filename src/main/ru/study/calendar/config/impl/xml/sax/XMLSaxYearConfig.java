package ru.study.calendar.config.impl.xml.sax;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.errors.errorTypes.XmlСonfigurationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий хранение данных о конфигурации конкретного года в цикле лет
 */
@Getter
public class XMLSaxYearConfig implements IYearTemplate {
    /**
     * Список месяцев в году
     */
    private List<IMonthTemplate> monthList;
    /**
     * Количество дней в году
     */
    @Setter(AccessLevel.PROTECTED)
    private Integer dayQuantity;

    /**
     * Конструктор года по передаваемому JSON конфигу
     *
     * @throws Exception
     */
    XMLSaxYearConfig(){
        monthList = new ArrayList<>();
        dayQuantity = 0;
    }
    protected void addMonth(IMonthTemplate month){
        if (!monthList.contains(month)) {
            monthList.add(month);
            dayQuantity+=month.getDayCount();
        } else {
            throw new XmlСonfigurationException("Не уникальное название месяца: " + month.getName());
        }
    }
    protected void resetYearConfig() {
        dayQuantity = 0;
        monthList = new ArrayList<>();
    }

    public void clone(XMLSaxYearConfig yearConstructor) {
        dayQuantity = yearConstructor.getDayQuantity();
        monthList = yearConstructor.getMonthList();
    }
}
