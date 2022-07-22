import java.util.ArrayList;
import java.util.Arrays;

public class Year implements IYear {
    Year(int initialNumberYear, Week week) {
        boolean isLeapYear = (initialNumberYear % 4 == 0);
        ArrayList<String> initialMonthName = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
        ArrayList<Integer> initialDayQuantityInMonth = new ArrayList<Integer>(Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
        if (isLeapYear) {
            initialDayQuantityInMonth.set(1, 29);
        }
        ArrayList<String> initialNameOfFirstDay = new ArrayList<String>();
        int  beginningOfYear = setFirstWeekDay(initialNumberYear);
        int offset = 0;
        for (int i=0; i<12; i++) {
            initialNameOfFirstDay.add(week.getStringFromInt((beginningOfYear+offset)%7));
            offset += initialDayQuantityInMonth.get(i);
        }
        setYear(initialNameOfFirstDay, initialMonthName, initialDayQuantityInMonth);
    }
    /*----------------------------*/
    private ArrayList<String> nameOfFirstDay;
    private ArrayList<String> monthName;
    private ArrayList<Integer> dayQuantityInMonth;
    /*----------------------------*/
    private void setYear(ArrayList<String> strNameOfFirstDay, ArrayList<String> strMonthName, ArrayList<Integer>intDayQuantityInMonth) {
        nameOfFirstDay = strNameOfFirstDay;
        monthName = strMonthName;
        dayQuantityInMonth = intDayQuantityInMonth;

    }
    private int setFirstWeekDay(int number) { /* При дальнейшем развитии надо переделать*/
        int diff = number - 2020;
        int diffLeap = (diff + 3) / 4;
        return ((diff-diffLeap)+2 * diffLeap + 2) % 7;
    }
    public int getDayQuantityInMonth(int offset) {
        return dayQuantityInMonth.get(offset);
    }

    public String getNameOfFirstDay(int offset) {
        return nameOfFirstDay.get(offset);
    }
    public String getMonthName(int offset) {
        return monthName.get(offset);
    }
    public int getNumberOfMonth() {
        return monthName.size();
    }
    public int getMonthNumber(String name) {
        return monthName.indexOf(name);
    }
}
