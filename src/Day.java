public class Day implements interfaceDay { // МНЕ ТАК ПРОЩЕ!!!! ВМЕСТО СТРОК ОПЕРИРОВАТЬ ЦИФРАМИ!!!!!
    Day(int initialWeekDayNumber) {
        weekDayNumber = initialWeekDayNumber;
    }

    public int weekDayNumber;
    public String getWeekDay() {
        return getNumber(weekDayNumber);
    }

    public void setDay(String strWeekDay) {
        weekDayNumber = setNumber();
    }
    private static String getNumber(int number) { // Это лишь кодировка
        return switch (number) {
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
    public static int setNumber(String string) {
        return switch (string) {
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
}
