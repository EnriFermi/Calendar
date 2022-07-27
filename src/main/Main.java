package main;

import data.Calendar;

public class Main {

    public static void main(String[] args) {
        Calendar x = new Calendar(2015);
        System.out.println(x.getWeekDay(16, "May").getIsWorkingOutDay());
    }
}
