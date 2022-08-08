package ru.study.calendar.service;

import ru.study.calendar.config.IYearTemplate;

public interface YearService {
    static Integer getIndexOfMonthByName(String name, IYearTemplate year) {
        for (int i = 0; i < year.getMonthList().size(); i++) {
            if (year.getMonthList().get(i).getName().equals(name)) {
                return i;
            }
        }
        throw new RuntimeException("Месяца с таким именем не существует");
    }
}
