package ret;

public interface ICalendar {
    //TODO переделать на возврат ret.IDay
    /**
     *   Выдает по номеру дня и названию месяца день недели
     */
    ret.IDay getWeekDay(Integer intDay, String stringMonth);

}
