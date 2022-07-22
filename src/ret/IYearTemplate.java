package ret;

import java.util.List;

/**
 * Шаблон года, используется для параметризации года
 *
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IYearTemplate {

    /**
     * Количество месяцев
     */
    Integer getMonthCount();

    /**
     * Параметры месяцев
     */
    List<IMonthTemplate> getMonthList();


}
