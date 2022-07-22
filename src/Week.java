import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week implements IWeek {
    //TODO по возможности везде использовать в переменных интерфейс
    private final List<Boolean> dataWork = new ArrayList<Boolean>();
    private List<String> dataWeek = new ArrayList<String>();

    Week() {
        //TODO перевести на enum
        List<String> initialWeek = new ArrayList<String>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        List<Boolean> initialWork = Arrays.asList(false, false, false, false, false, true, true);
        setWeek(initialWeek);
    }

    //TODO убрать аналогичные
    private void setWeek(List<String> strDataWeek) {
        dataWeek = strDataWeek;
    }

    public int getIntFromString(String strWeekDay) {
        return dataWeek.indexOf(strWeekDay);
    }

    public String getStringFromInt(int intWeekDay) {
        return dataWeek.get(intWeekDay);
    }

    public boolean getIsWorking(int number) {
        return dataWork.get(number);
    }
}
