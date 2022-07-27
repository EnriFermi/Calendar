package ret;

import java.util.List;

/**
 * Параметры месяца
 * @author Romanikhin Valeriy <romanihin@unislabs.com>
 */
public interface IMonthTemplate {
    /**
     * Имя месяца
     * @return
     */
    String getName();

    /**
     * Количество дней в месяце
     * @return
     */
    Integer getDayCount();

    /**
     * ! Дополнительные не рабочие !
     */
    List<Integer> getDayWorkOutList();

    /**
     * ! Дополнительные рабочие !
     */
    List<Integer> getDayWorkList();

}
