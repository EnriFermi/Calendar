package ret;

/**
 * Параметры дня недели
 */

public interface IDayTemplate {
    /**
     * Название дня недели
     */
    String getDayName();
    /**
     * ! Выходные дни недели !
     */
    Boolean getWeekDayWorkOut();
}
