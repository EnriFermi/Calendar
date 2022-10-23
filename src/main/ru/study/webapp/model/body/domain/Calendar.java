package ru.study.webapp.model.body.domain;

import lombok.AccessLevel;
import lombok.Getter;
import ru.study.webapp.model.body.services.WeekService;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
import ru.study.webapp.model.configuration.domain.DayTemplate;
import ru.study.webapp.model.configuration.domain.MonthTemplate;
import ru.study.webapp.model.configuration.domain.YearTemplate;
import ru.study.webapp.exceptions.model.OutOfBoundException;

import java.util.ArrayList;
import java.util.List;
@Getter(AccessLevel.PUBLIC)
public class Calendar {
    /**
     * Список месяцев в календаре
     */
    private final List<Month> arrayOfMonth;

    /**
     * Номер года в цикле
     */
    private final Integer dayQuantity;
    /**
     * Конструктор календаря по номеру года из допустимого интервала и шаблону календаря
     *
     * @param numberYear Номер года
     * @param calendarTemplate Шаблон календаря
     * @throws OutOfBoundException
     */
    public Calendar(int numberYear, CalendarTemplate calendarTemplate) throws OutOfBoundException {
        checkForValid(numberYear, calendarTemplate);
        this.arrayOfMonth = new ArrayList<>();
        this.dayQuantity = fillMonthList(numberYear, calendarTemplate);
    }

    /**
     * Проверяет на корректность введенный номер года (должен попадать в указанный в конфиге интервал)
     *
     * @param numberYear Номер года
     * @param calendarConfig Шаблон календаря
     * @throws OutOfBoundException
     */
    private void checkForValid(int numberYear, CalendarTemplate calendarConfig) throws OutOfBoundException {
        if ((calendarConfig.getBeginningYear() > numberYear) || (calendarConfig.getEndYear() < numberYear)) {
            throw new OutOfBoundException("Номер года вне границ разрешенного диапазона");
        }
    }
    /**
     * Заполняет список месяцев, получая данные из конфига
     *
     * @param numberYear Номер года
     * @param calendarConfig Шаблон календаря
     * @return Количество дней в году
     */
    private Integer fillMonthList(int numberYear, CalendarTemplate calendarConfig) {
        Integer indexOfYearInConfig = (numberYear - 1) % calendarConfig.getYearList().size();
        DayTemplate day = getIndexOfFirstDay(numberYear, calendarConfig);
        YearTemplate year = calendarConfig.getYearList().get(indexOfYearInConfig);
        MonthTemplate month;
        for (int i = 0; i < year.getMonthList().size(); i++) {
            month = year.getMonthList().get(i);
            this.arrayOfMonth.add(new Month(month, day, calendarConfig.getWeek()));
            day = WeekService.getOffsetDayFrom(day, month.getDayCount(), calendarConfig.getWeek());
        }
        return year.getDayQuantity();
    }

    /**
     * Возвращает день недели первого месяца года под номером numberYear
     *
     * @param numberYear     Номер года
     * @param calendarConfig Конфиг календаря
     * @return Шаблон первого дня первого месяца года под номером numberYear
     */
    /**
     * Возвращает день недели первого месяца года под номером numberYear
     *
     * @param numberYear     Номер года
     * @param calendarConfig Конфиг календаря
     * @return Шаблон первого дня первого месяца года под номером numberYear
     */
    private DayTemplate getIndexOfFirstDay(Integer numberYear, CalendarTemplate calendarConfig) {
        /*
         *  firstDayInFirstYear - День недели 1 числа 1 месяца 0 года
         *  yearCycleSize - Длина цикла годов
         *  weekSize - Длина недели
         *  differenceBetweenYear - Разница между годом привязки и введенным годом
         *  weekAndYearCycleSize - Разница в годах относительно цикла Год+Неделя
         *  number - Номер года в большом цикле лет
         */
        DayTemplate firstDayInFirstYear = calendarConfig.getAnchorWeekDay();
        Integer yearCycleSize = calendarConfig.getYearList().size();
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        Integer differenceBetweenYear = numberYear - calendarConfig.getBeginningYear();
        Integer shiftBetweenYears = differenceBetweenYear % (weekSize * yearCycleSize);
        Integer begin = calendarConfig.getBeginningYear() % yearCycleSize;

        for (int iterator = 1; iterator < shiftBetweenYears; iterator++) {
            firstDayInFirstYear = WeekService.getOffsetDayFrom(firstDayInFirstYear,
                    calendarConfig.getYearList().get((iterator + begin) % yearCycleSize)
                                  .getDayQuantity() % weekSize, calendarConfig.getWeek());
        }
        return firstDayInFirstYear;
    }
}