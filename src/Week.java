import java.util.ArrayList;
import java.util.Arrays;


public class Week implements IWeek {
    private ArrayList<String> dataWeek = new ArrayList<String>();
    private ArrayList<Boolean> dataWork = new ArrayList<Boolean>();
    Week(){
        ArrayList<String> initialWeek= new ArrayList<String>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        ArrayList<Boolean> initialWork = new ArrayList<Boolean>(Arrays.asList(false,false,false,false,false,true,true));
        setWeek(initialWeek);
    }

    private void setWeek(ArrayList<String> strDataWeek) {
        dataWeek = strDataWeek;

    }
    public int getIntFromString(String strWeekDay){
        return dataWeek.indexOf(strWeekDay);
    }
    public String getStringFromInt(int intWeekDay){
        return dataWeek.get(intWeekDay);
    }
    public boolean getIsWorking(int number){
        return dataWork.get(number);
    }
}
