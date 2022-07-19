public class Year implements interfaceYear{
    Year(int initialNumberYear) {
        setYear(initialNumberYear);

    }
    private int numberYear = 7;
    private Day firstWeekDay = new Day(7);
    /* Я не понял, зачем менять */
    private int isLeapYear = 0;
    public int getYear() {
        return numberYear;
    }
    public void setYear(int intNumberYear) {
        numberYear = intNumberYear;
        isLeapYear = setIfLeapYear();
    }
    public void changeYear(int offset) {
        numberYear = numberYear + offset;
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
    public Day getFirstWeekDay() {
        int diff = numberYear - 2020;
        int diffLeap = (diff + 3) / 4;
        return new Day(((diff-diffLeap)+2 * diffLeap + 2) % 7 );
    }
}
