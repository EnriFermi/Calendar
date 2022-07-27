package ret;

import java.util.List;

/**
 * Настройки календаря
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface ICalendarConfig {
    String getAnchorWeekDay();
    List<IYearTemplate> getYearList();
    IWeekTemplate getWeek();
    Integer getAnchorYear();
}
