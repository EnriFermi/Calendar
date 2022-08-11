package ru.study.calendar.config.impl.xml;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.study.calendar.config.ICalendarTemplate;
import ru.study.calendar.config.IWeekTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.xml.enums.XMLFieldNames;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий хранение данных о конфигурации календаря
 */
@Getter
public class XMLCalendarConfig implements ICalendarTemplate {
    /**
     * День первого числа первого месяца привязочного года
     */
    private XMLDayConfig anchorWeekDay;
    /**
     * Список шаблонов лет
     */
    private final List<IYearTemplate> yearList;
    /**
     * Шаблон недели
     */
    private IWeekTemplate week;
    /**
     * Год начала допустимого интервала календаря
     */
    private Integer beginningYear;
    /**
     * Год конца допустимого интервала календаря
     */
    private Integer endYear;

    /**
     * Конструктор календаря по передаваемому пути к конфигу
     *
     * @param path Путь к конфигу
     * @throws Exception
     */
    public XMLCalendarConfig(String path) throws Exception {
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fac.newDocumentBuilder();
        Document document = builder.parse(new File(path));
        NodeList configOfCalendar = document.getFirstChild().getChildNodes();
        yearList = new ArrayList<>();
        for (Integer i = 0; i < configOfCalendar.getLength(); i++) {
            Node elementCalendar = configOfCalendar.item(i);
            if (elementCalendar.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // Получаем 1 день 1 месяца привязочного года
            if (elementCalendar.getNodeName().equals(XMLFieldNames.ANCHOR_WEEKDAY.getFieldName())) {
                anchorWeekDay = new XMLDayConfig(elementCalendar);
            }
            // Год начала допустимого интервала календаря
            if (elementCalendar.getNodeName().equals(XMLFieldNames.BEGINNING_YEAR.getFieldName())) {
                beginningYear = Integer.valueOf(elementCalendar.getTextContent());
            }
            // Год конца допустимого интервала календаря
            if (elementCalendar.getNodeName().equals(XMLFieldNames.END_YEAR.getFieldName())) {
                endYear = Integer.valueOf(elementCalendar.getTextContent());
            }
            //------------------------------------------------
            // Получаем список годов
            if (elementCalendar.getNodeName().equals(XMLFieldNames.YEAR_LIST.getFieldName())) {
                NodeList yearConfigList = elementCalendar.getChildNodes();
                for (Integer j = 0; j < yearConfigList.getLength(); j++) {

                    Node yearConfig = yearConfigList.item(j);
                    if (yearConfig.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (yearConfig.getNodeName().equals(XMLFieldNames.YEAR_CONFIG.getFieldName())) {
                        yearList.add(new XMLYearConfig(yearConfig));
                    }
                }
            }
            // Получаем неделю
            if (elementCalendar.getNodeName().equals(XMLFieldNames.WEEK.getFieldName())) {
                week = new XMLWeekConfig(elementCalendar);
            }
        }
    }
}
