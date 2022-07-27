package ret;

import java.util.List;

/**
 * Шаблон года, используется для параметризации года
 *
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IYearTemplate {
    /**
     * Количество дней в годе
     */
    Integer getDayQuantity();
    /**
     * Количество месяцев
     */
    Integer getNumberOfMonth();
    /**
     * Параметры месяцев
     */
    List<IMonthTemplate> getMonthList();
    Integer getIndexOfMonthByName(String name);
}
