//TODO посмотреть lombok
public class Day implements IDay {
    Day(String initialWeekDay, boolean initialIsWorkingDay) {
        setDay(initialWeekDay, initialIsWorkingDay);
    }
    private String weekDay;
    private boolean isWorkingDay;
    public String getWeekDay() {
        return weekDay;
    }
    public boolean getIsWorkingDay() {
        return isWorkingDay;
    }

    private void setDay(String strWeekDay, boolean boolIsWorkingDay) {
        weekDay = strWeekDay;
        isWorkingDay = boolIsWorkingDay;
    }


}
