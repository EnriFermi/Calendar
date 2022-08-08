package ru.study.calendar.item.impl;

import ru.study.calendar.config.ICalendarTemplate;
import ru.study.calendar.config.IDayTemplate;
import ru.study.calendar.config.IMonthTemplate;
import ru.study.calendar.config.IYearTemplate;
import ru.study.calendar.config.impl.json.JsonCalendarConfig;
import ru.study.calendar.item.ICalendar;
import ru.study.calendar.item.IDay;
import ru.study.calendar.service.weekService;
import ru.study.calendar.service.yearService;

import java.util.ArrayList;
import java.util.List;

public class Calendar implements ICalendar {
    /**
     * Список месяцев в календаре
     */
    private final List<Month> arrayOfMonth;
    /**
     * Номер года в цикле
     */
    private final Integer indexOfYearInConfig;
    /**
     * Конфиг календаря
     */
    private final ICalendarTemplate calendarConfig;

    //-------------------------------------

    /**
     * Конструктор календаря по номеру года из допустимого интервала
     * @param numberYear Номер года
     * @throws Exception
     */
    public Calendar(int numberYear) throws Exception {
        this.calendarConfig = new JsonCalendarConfig("resources\\classicCalendar.json");
        //TODO предлагаю все проверки в 1 метод собрать (На уровне введенных данных сделано)
        /*
         * Проверка, что введенный номер года соответствует допустимому диапазону
         */
        checkForValid(numberYear);

        //TODO почитать про static классы DONE (пока что конфиги не переведены в static)
        this.arrayOfMonth = new ArrayList<>();
        /*
         * anchor - Номер дня недели 1 числа i месяца года, по которому создается календарь
         * weekSize - Количество дней недели
         */
        this.indexOfYearInConfig = (numberYear - 1) % this.calendarConfig.getYearList().size();
        fillMonthList(numberYear);
    }

    /**
     * Проверяет на корректность введенный номер года (должен попадать в указанный в конфиге интервал)
     * @param numberYear Проверяемый номер года
     * @throws Exception
     */
    private void checkForValid(int numberYear) {
        if ((this.calendarConfig.getBeginningYear() > numberYear)&&(this.calendarConfig.getEndYear() < numberYear)) {
            throw new RuntimeException("Номер года вне границ разрешенного диапазона");
        }
    }

    /**
     * Заполняет список месяцев, получая данные из конфига
     * @param numberYear Номер года
     * @throws Exception
     */
    private void fillMonthList(int numberYear) {
        IDayTemplate day = getIndexOfFirstDay(numberYear, this.calendarConfig);
        IYearTemplate year = this.calendarConfig.getYearList().get(this.indexOfYearInConfig);
        IMonthTemplate month;
        for (int i = 0; i < year.getMonthList().size(); i++) {
            month = year.getMonthList().get(i);
            this.arrayOfMonth.add(new Month(month, day, calendarConfig.getWeek()));
            day = weekService.getOffsetDayFrom(day, month.getDayCount(), calendarConfig.getWeek());
        }
    }

    /**
     * Возвращает день недели первого месяца года под номером numberYear
     * @param numberYear     Номер года
     * @param calendarConfig Конфиг календаря
     * @return Шаблон первого дня первого месяца года под номером numberYear
     */
    private IDayTemplate getIndexOfFirstDay(Integer numberYear, ICalendarTemplate calendarConfig) {
        /*
         *  firstDayInFirstYear - День недели 1 числа 1 месяца 0 года
         *  yearCycleSize - Длина цикла годов
         *  weekSize - Длина недели
         *  differenceBetweenYear - Разница между годом привязки и введенным годом
         *  weekAndYearCycleSize - Разница в годах относительно цикла ГодxНеделя
         *  number - Номер года в большом цикле лет
         */
        IDayTemplate firstDayInFirstYear = calendarConfig.getAnchorWeekDay();
        Integer yearCycleSize = calendarConfig.getYearList().size();
        Integer weekSize = calendarConfig.getWeek().getWeekDayCount();
        Integer differenceBetweenYear = numberYear - calendarConfig.getBeginningYear();
        //TODO refactor DONE
        /*
         * Отвечает за то, какое количество раз нужно изменить firstDayInFirstDay, чтобы получить день недели года под номером numberYear
         */
        Integer shiftBetweenYears = differenceBetweenYear % (weekSize * yearCycleSize);
        //TODO magic? DONE
        /*
         * Отвечает за то, чтобы можно было с любого года начинать, не только с того, который знаменует начало цикла лет
         * Сначала же создается последовательность ... 0 1 2 3 0 1 2 3 ...
         * Так вот begin показывает с какого числа должна начинаться данная последовательность
         * 0 1 2 3 0 ...
         * 1 2 3 0 1 ...
         * 2 3 0 1 2 ...
         * 3 0 1 2 3 ...
         */
        Integer begin = calendarConfig.getBeginningYear() % yearCycleSize;

        /*
        return weekService.getOffsetDayFrom(firstDayInFirstYear, Stream.iterate(begin, iterator -> (iterator + 1) % yearCycleSize)
                                                                          .limit(number)
                                                                          .map(integer -> this.calendarConfig.getYearList()
                                                                                                             .get(integer)
                                                                                                             .getDayQuantity())
                                                                          .reduce(0, (sum, add) -> sum + add), calendarConfig.getWeek());
        */
        //TODO вернуть цикл DONE
        for (int iterator = 1; iterator < shiftBetweenYears; iterator++) {
            firstDayInFirstYear = weekService.getOffsetDayFrom(firstDayInFirstYear,
                    calendarConfig.getYearList().get((iterator+begin) % yearCycleSize).getDayQuantity() % weekSize, calendarConfig.getWeek());
        }
        return firstDayInFirstYear;
    }

    //TODO рефактор

    /**
     * Выдает по номеру дня и названию месяца день недели
     * @param intDay Номер дня в месяце (начинается с 1)
     * @param stringMonth Название месяца
     * @return Объект дня
     * @throws Exception
     */
    public IDay getWeekDay(Integer intDay, String stringMonth) {
        IYearTemplate year = calendarConfig.getYearList().get(indexOfYearInConfig);
        Integer monthIndex = yearService.getIndexOfMonthByName(stringMonth, year);
        //TODO исправить DONE
        Integer dayQuantityInMonth = year.getMonthList().get(monthIndex).getDayCount();
        if ((intDay > dayQuantityInMonth)||(intDay < 1)) {
            throw new RuntimeException("Количество дней за границей допустимых значений");
        }
        return arrayOfMonth.get(monthIndex).getDayByNumberInMonth(intDay - 1);
    }
}