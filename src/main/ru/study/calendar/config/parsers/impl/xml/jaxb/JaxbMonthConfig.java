package ru.study.calendar.config.parsers.impl.xml.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс реализующий хранение данных о конфигурации конкретного месяца в году
 */
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbMonthConfig {
    @XmlElement(name = "nameOfMonth")
    private String monthName;
    @XmlElement(name = "dayCount")
    private Integer dayCount;
    @XmlElement(name = "dayWorkList")
    private JaxbDayWorkList dayWorkList;
    @XmlElement(name = "dayWorkOutList")
    private JaxbDayWorkOutList dayWorkOutList;

}
