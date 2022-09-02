package ru.study.calendar.config.parsers.impl.xml.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbWeekDayNameList {
    @XmlElement(name = "weekDayName")
    private List<JaxbDayConfig> jaxbWeekDayConfigList = new ArrayList<>();
}
