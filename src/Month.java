import java.util.ArrayList;
public class Month implements IMonth {
    Month(String initialMonth, String initialWeekDay, int initialDayQuantity, Week  initialWeek) {
        setMonth(initialMonth, initialWeekDay, initialDayQuantity, initialWeek);
    }
    private String monthName;
    private ArrayList<Day> arrayOfDays = new ArrayList<Day>();
    private int dayQuantity = 0; // количество дней в месяцев
    /*

    public String getMonthName() {
        return monthName;
    }
    public int getDayQuantity() {
        return dayQuantity;
    }*/
    public Day getDay(int date) {
        return arrayOfDays.get(date);
    }
    private void setMonth(String stringMonth, String stringWeekDay, int intDayQuantity, Week week){
        monthName = stringMonth;
        dayQuantity = intDayQuantity;
        int begin = week.getIntFromString(stringWeekDay);
        for(int date = 0; date < intDayQuantity; date++) {
            arrayOfDays.add(new Day(week.getStringFromInt((date + begin) % 7), week.getIsWorking((date + begin) % 7)));
        }
    }
}
