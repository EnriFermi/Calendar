import java.util.ArrayList;

public class Month implements interfaceMonth{
    Month(String initialMonth, String initialWeekDay, int initialDayQuantity) {
        setMonth(initialMonth, initialWeekDay, initialDayQuantity);
    }
    private String monthName;

    private ArrayList<Day> arrayOfDays = new ArrayList<Day>();
    private int dayQuantity = 0; // количество дней в месяцев

    public String getMonthName() {
        return monthName;
    }

    public int getDayQuantity() {
        return dayQuantity;
    }
    public Day getDay(int date) {
        return arrayOfDays.get(date);
    }
    public void setMonth(String stringMonth, String stringWeekDay, int intDayQuantity){
        monthName = stringMonth;
        dayQuantity = intDayQuantity;
        int begin = Day.setNumber(stringWeekDay);
        /*
        Цикл for сильно усложняет понимание. В любом случае при создании месяца передаваться должен день недели первого числа.
        Иначе я придумать не могу. Чтобы забить весь список, надо последовательно менять день недели. Либо надо метод который меняет и выдает следующий
        день недели за текущим, либо можно в месяце сделать массив с названиями дней недели. НО! В таком случае нужен
        цикл для поиска индекса первого дня недели в месяце, так как мы на входе получаем строку. Мне для понимания лучше моя реализация. Если она
        в чем-то хуже, то изменю.
         */
        for(int date = 0; date < intDayQuantity; date++) {
            arrayOfDays.add(new Day(date + begin));
        }
    }



}
