package ru.study.calendar.config.impl.xml.sax;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.study.calendar.config.IDayTemplate;

/**
 * Класс реализующий хранение данных о конфигурации конкретного дня недели
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PROTECTED)
public class XMLSaxDayConfig implements IDayTemplate{
    /**
     * Конструктор дня недели по передаваемому JSON конфигу
     * @param  Объект JSON конфига, хранящий информацию о конкретном дне
     */


    /**
     * Название дня недели
     */
    @EqualsAndHashCode.Include @Getter private String dayName;
    /**
     * Указание рабочий ли день
     */
    private Boolean weekDayWorkOut;

    /**
     * Метод получения информации рабочий ли день
     * @return Возвращает boolean значение рабочий ли день
     */
    public XMLSaxDayConfig() {
        dayName = "";
        weekDayWorkOut = false;
    }
    @Override
    public Boolean isDefaultDayWorkOut() {
        return this.weekDayWorkOut;
    }
    protected void resetDay() {
        dayName = "";
        weekDayWorkOut = false;
    }

    public void clone(XMLSaxDayConfig dayConstructor) {
        dayName = dayConstructor.getDayName();
        weekDayWorkOut = dayConstructor.isDefaultDayWorkOut();
    }
}
