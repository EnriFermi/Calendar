package data;

import ret.IDay;

//TODO посмотреть lombok https://projectlombok.org/ DONE
public class Day implements IDay {
    Day(String weekDay, boolean isWorkingDay) {
        this.weekDay = weekDay;
        this.isWorkingDay = isWorkingDay;
    }
    private String weekDay;
    private boolean isWorkingDay;
    public String getWeekDay() {
        return this.weekDay;
    }
    public boolean getIsWorkingOutDay() {
        return this.isWorkingDay;
    }

    //TODO в конструкторах использовать this
    private void setDay(String weekDay, boolean isWorkingDay) {
        this.weekDay = weekDay;
        this.isWorkingDay = isWorkingDay;
    }
}
