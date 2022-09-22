package ru.study.webapp.model.body.services;

import ru.study.webapp.model.body.domain.Calendar;

public class YearService {
    /**
     * Возвращает индекс месяца в календаре по его имени
     *
     * @param name Имя месяца
     * @param calendar Шаблон года
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
