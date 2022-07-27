//TODO посмотреть lombok https://projectlombok.org/
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

    //TODO в конструкторах использовать this
    private void setDay(String weekDay, boolean isWorkingDay) {
        this.weekDay = weekDay;
        this.isWorkingDay = isWorkingDay;
    }
}
