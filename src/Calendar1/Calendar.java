package Calendar1;

public class Calendar {
    Calendar(int initialYear) {
        setCalendar(initialYear);
    }
    private Month[] arrayOfMonth = new Month[12]; // массив месяцев
    private Year startYear = new Year(0); // год календаря
    public void setCalendar(int intYear) {
        startYear.setYear(intYear);
        arrayOfMonth[0] = new Month("January", startYear.getFirstWeekDay().getWeekDay(), startYear.getYear());
        for (int i=1; i<12; i++) {
            arrayOfMonth[i] = arrayOfMonth[i - 1];
            arrayOfMonth[i].changeMonth(1);
        }
    }
    public String getWeekDay(int Day, String stringMonth) { // получение дня недели по дате
        Month Month = new Month("Null", "Null", 0); // Не понял как работает static метод
        int intMonth = Month.getIntFromString(stringMonth);
        WeekDay findableWeekDay = arrayOfMonth[intMonth].getBeginningOfMonth();
        findableWeekDay.changeWeekDay(Day-1);
        return findableWeekDay.getWeekDay();
    }

}