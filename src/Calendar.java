import java.util.ArrayList;

public class Calendar implements ICalendar {
    Calendar(int initialNumberYear) {
        Week initialWeek = new Week();
        Year initialYear = new Year(initialNumberYear, initialWeek);
        setCalendar(initialYear, initialWeek);
    }
    private ArrayList<Month> arrayOfMonth; // массив месяцев
    private Week week;
    private Year year;
    public void setCalendar(Year inYear, Week inWeek) {
        week = inWeek;
        year = inYear;
        arrayOfMonth = new ArrayList<Month>();
        for (int i=0; i<year.getNumberOfMonth(); i++) {
            arrayOfMonth.add(new Month(year.getMonthName(i), year.getNameOfFirstDay(i), year.getDayQuantityInMonth(i), week));
        }
    }
    public String getWeekDay(int intDay, String stringMonth) { // получение дня недели по дате
        return arrayOfMonth.get(year.getMonthNumber(stringMonth)).getDay(intDay-1).getWeekDay();
    }
    public boolean getIsWorking(int intDay, String stringMonth) {
        return arrayOfMonth.get(year.getMonthNumber(stringMonth)).getDay(intDay-1).getIsWorkingDay();
    }

}