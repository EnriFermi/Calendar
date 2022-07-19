package Calendar1;

public class Month {
    Month(String initialMonth, String initialWeekDay, int initialYearNumber) {
        setMonth(initialMonth, initialWeekDay, initialYearNumber);
    }
    private static final int[] dataMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 0}; //массив размеров месяцев
    private int numberMonth = 12; // номер месяца (начинается с 0)
    private int dayQuantity = 0; // количество дней в месяце
    private final Year startYear = new Year(0); // показатель високосности(принимает значения от 0 до 3
                                                        // показывает какой по счету год в четверке, где под 0 високосный
                                                        // реализован в виде объекта класса Year
    private final WeekDay beginningOfMonth = new WeekDay("Null"); // день недели первого числа месяца

    public String getStringMonth() {
        return getStringFromInt(numberMonth);
    }
    public int getIntMonth() {
        return numberMonth;
    }
    public int getDayQuantity() {
        return dayQuantity;
    }
    public WeekDay getBeginningOfMonth() {
        return beginningOfMonth;
    }
    public void setMonth(String stringMonth, String stringWeekDay, int intYearNumber) {
        startYear.setYear(intYearNumber);
        numberMonth = getIntFromString(stringMonth);
        setDayQuantity();
        beginningOfMonth.setWeekDay(stringWeekDay);
    }
    //----------------------------------------
    private void setDayQuantity() {
        if ((numberMonth == 1)&&(startYear.getIsLeapYear())) {
            dayQuantity = 29;
            return;
        }
        dayQuantity = dataMonth[numberMonth];
    }
    //-------------------------------------------
    public void changeMonth(int offset) { // Если при сдвиге месяц переходит в новый год, то это учитывается
        int prevMonthNumber = numberMonth;
        numberMonth = numberMonth + offset;
        if (numberMonth >= 0) {
            numberMonth = numberMonth % 12;
        } else {
            numberMonth = (numberMonth + 12) % 12;
        }
        setDayQuantity();
        for (int i = prevMonthNumber; i < Math.abs(offset) + prevMonthNumber; i++) {
            if (i % 12 == 0) {
                startYear.changeYear(1);
            }
            if ((i % 12 == 1) && (startYear.getIsLeapYear())) {
                beginningOfMonth.changeWeekDay(29);
            } else {
                beginningOfMonth.changeWeekDay(dataMonth[i % 12]);
            }
        }
    }
    public WeekDay getDateWeekDay(int Day) { // возвращает день недели дня с номером Day в месяце
        WeekDay DateWeekDay = beginningOfMonth;
        DateWeekDay.changeWeekDay(Day-1);
        return DateWeekDay;
    }
    public int getIntFromString(String stringMonth) { // вспомогательный функции(должны быть статическими методами)
        return switch (stringMonth) {
            case "January" -> 0;
            case "February" -> 1;
            case "March" -> 2;
            case "April" -> 3;
            case "May" -> 4;
            case "June" -> 5;
            case "July" -> 6;
            case "August" -> 7;
            case "September" -> 8;
            case "October" -> 9;
            case "November" -> 10;
            case "December" -> 11;
            default -> 12;
        };
    }
    public String getStringFromInt(int intMonth) {
        return switch (intMonth) {
            case 0 -> "January";
            case 1 -> "February";
            case 2 -> "March";
            case 3 -> "April";
            case 4 -> "May";
            case 5 -> "June";
            case 6 -> "July";
            case 7 -> "August";
            case 8 -> "September";
            case 9 -> "October";
            case 10 -> "November";
            case 11 -> "December";
            default -> "Null";
        };
    }

}
