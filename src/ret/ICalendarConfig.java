package ret;

import java.util.List;

/**
 * Настройки календаря
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface ICalendarConfig {



    /**
     * Количество дней в неделе
    */
    Integer getWeekDayCount();

    /**
     * Название дней недели
     */
    List<String> weekDayNameList();

    /**
     * ! Выходные дни недели !
     */
    List<Integer> weekDayWorkOutList();

}
