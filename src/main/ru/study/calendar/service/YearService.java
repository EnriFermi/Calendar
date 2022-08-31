package ru.study.calendar.service;

import ru.study.calendar.config.domain.impl.YearTemplate;

public interface YearService {
    /**
     * @param name Имя месяца
     * @param year Шаблон года
     * @return Номер месяца с именем name в году
     */
    static Integer getIndexOfMonthByName(String name, YearTemplate year) {
        for (int i = 0; i < year.getMonthList().size(); i++) {
            if (year.getMonthList().get(i).getName().equals(name)) {
                return i;
            }
        }
        throw new RuntimeException("Месяца с таким именем не существует");
    }
}
