package ru.study.calendar.config.parsers.impl.xml.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbDayConfig {
    @XmlElement(name = "dayName")
    private String dayName;
    @XmlElement(name = "weekDayWorkOut")
    private Boolean weekDayWorkOut;
}
