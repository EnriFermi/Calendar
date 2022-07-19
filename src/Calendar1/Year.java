package Calendar1;

public class Year {
    Year(int initialNumberYear) {
        setYear(initialNumberYear);

    }
    private int numberYear = 7;
    private WeekDay firstWeekDay = new WeekDay(7);
    private int isLeapYear = 0;
    public int getYear() {
        return numberYear;
    }
    public void setYear(int intNumberYear) {
        numberYear = intNumberYear;
        setIfLeapYear(numberYear);
    }
    public void changeYear(int offset) {
        numberYear = numberYear + offset;
        isLeapYear = (isLeapYear + offset) % 4;
    }
    private void setIfLeapYear(int numberYear) {
        isLeapYear = numberYear % 4;
    }
    public boolean getIsLeapYear() {
        if (isLeapYear % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
    public WeekDay getFirstWeekDay() {
        int diff = numberYear - 2020;
        int diffLeap = (diff + 3) / 4;
        return new WeekDay(((diff-diffLeap)+2 * diffLeap + 2) % 7 );
    }
}
