public interface ICalendar {
    //TODO переделать на возврат IDay
    /*
    Выдает по номеру дня и названию месяца день недели
     */
    String getWeekDay(int intDay, String stringMonth);
    /*
    Выдает по номеру дня и названию месяца рабочий день или нет
    */
    boolean getIsWorking(int intDay, String stringMonth);
}
