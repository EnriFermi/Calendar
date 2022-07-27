package ret;

//TODO добавить комментарии
public interface IDay {
    /**
     * Выдает день недели
     * @return
     */
    String getWeekDay();

    /**
     * Выдает рабочий день или нет
     * @return
     */
    boolean getIsWorkingOutDay();
}
