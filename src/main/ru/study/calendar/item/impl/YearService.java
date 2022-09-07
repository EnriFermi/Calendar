package ru.study.calendar.item.impl;

public class YearService {
    /**
     * @param name Имя месяца
     * @param year Шаблон года
     * @return Номер месяца с именем name в году
     */
    static Integer getIndexOfMonthByName(String name, Calendar calendar) {
        for (int i = 0; i < calendar.getArrayOfMonth().size(); i++) {
            if (calendar.getArrayOfMonth().get(i).getMonthName().equals(name)) {
                return i;
            }
        }
        throw new RuntimeException("Месяца с таким именем не существует");
    }
}
