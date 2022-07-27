package ret;

import java.util.List;
/**
 * Шаблон недели, используется для параметризации недели
 *
 */
public interface IWeekTemplate {
    /**
     * Количество дней в неделе
     */
    Integer getWeekDayCount();

    /**
     * Параметры дней недели
     */
    List<IDayTemplate> weekDayNameList();
    Integer getIndexOfDayByName(String name);
}
