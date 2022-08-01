package ru.study.calendar.item;

public interface IMonth {
    /**
     *  По номеру дня выдает объект типа день
     */
    IDay getDayByNumberInMonth(int number);

}
