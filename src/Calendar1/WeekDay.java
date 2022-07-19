package Calendar1;

public class WeekDay {
    WeekDay(String initialWeekDay) {
        setWeekDay(initialWeekDay);
    }
    WeekDay(int initialNumberWeekDay) {
        numberWeekDay = initialNumberWeekDay;
    }
    private int numberWeekDay = 7;
    public String getWeekDay() {
        return switch (numberWeekDay) {
            case 0 -> "Monday";
            case 1 -> "Tuesday";
            case 2 -> "Wednesday";
            case 3 -> "Thursday";
            case 4 -> "Friday";
            case 5 -> "Saturday";
            case 6 -> "Sunday";
            default -> "Null";
        };
    }
    public int getNumberWeekDay() {
        return numberWeekDay;
    }
    public void setWeekDay(String stringWeekDay) {
        numberWeekDay = switch (stringWeekDay) {
            case "Monday" -> 0;
            case "Tuesday" -> 1;
            case "Wednesday" -> 2;
            case "Thursday" -> 3;
            case "Friday" -> 4;
            case "Saturday" -> 5;
            case "Sunday" -> 6;
            default -> 7;
        };
    }
    public void changeWeekDay(int offset) {
        numberWeekDay = numberWeekDay + offset;
        if (numberWeekDay >= 0) {
            numberWeekDay = numberWeekDay % 7;
        } else {
            numberWeekDay = (numberWeekDay + 7) % 7;
        }
    }
}
