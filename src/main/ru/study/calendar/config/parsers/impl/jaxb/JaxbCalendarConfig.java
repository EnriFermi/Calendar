package ru.study.calendar.config.parsers.impl.jaxb;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс реализующий хранение данных о конфигурации календаря
 */
@Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
@XmlRootElement(name = "calendarConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbCalendarConfig  {
    @XmlElement(name = "anchorWeekDay")
    private JaxbDayConfig jaxbDayConfig;
    @XmlElement(name = "beginningYear")
    private Integer beginningYear;
    @XmlElement(name = "endYear")
    private Integer endYear;
    @XmlElement(name = "yearList")
    private JaxbYearList jaxbYearList;
    @XmlElement(name = "week")
    private JaxbWeek jaxbWeek;
}
