package template;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ret.IMonthTemplate;
import ret.IYearTemplate;

import java.util.ArrayList;
import java.util.List;
public class YearTemplate implements IYearTemplate {
    private List<IMonthTemplate> monthList;
    private Integer numberOfMonth;

    private Integer dayQuantity;
    YearTemplate(JSONObject yearConfig){
        FieldsNames field = FieldsNames.numberOfMonth;
        this.numberOfMonth = ((Long) yearConfig.get(field.getFieldName())).intValue();
        field = FieldsNames.dayQuantity;
        this.dayQuantity = ((Long) yearConfig.get(field.getFieldName())).intValue();
        field = FieldsNames.monthList;
        this.monthList = new ArrayList<IMonthTemplate>();
        JSONArray monthListConfig = (JSONArray) yearConfig.get(field.getFieldName());
        for(int iterator=0; iterator<numberOfMonth;iterator++)
        {
            this.monthList.add(new MonthTemplate((JSONObject) monthListConfig.get(iterator)));
        }
    }
    /*
    YearTemplate(int numberYear, Week week) {
        boolean isLeapYear = (numberYear % 4 == 0);
        List<String> dataMonth = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
        List<Integer> dataQuantity = new ArrayList<Integer>(Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));
        if (isLeapYear) {
            dataQuantity.set(1, 29);
        }
        numberOfMonth = 12;
        //TODO подумать как уменьшить число зависимых списков
        this.monthList = new ArrayList<IMonthTemplate>();
        int beginningOfYear = setFirstWeekDay(numberYear);
        int offset = 0;
        for (int i = 0; i < numberOfMonth; i++)
        {
            monthList.add(new MonthTemplate(dataMonth.get(i), dataQuantity.get(i), new ArrayList<Integer>(), new ArrayList<Integer>()));
        }
    }
    /*----------------------------*/

    /**
     * Расчет номера дня недели 1 января по году
     *
     * @param number - номер года
     * @return - номер дня в недели
     */
    //TODO rename
    private int setFirstWeekDay(int number) { /* При дальнейшем развитии надо переделать*/
        int diff = number - 2020;
        int diffLeap = (diff + 3) / 4;
        return ((diff - diffLeap) + 2 * diffLeap + 2) % 7;
    }

    @Override
    public Integer getDayQuantity() {
        return dayQuantity;
    }
    @Override
    public Integer getNumberOfMonth() {
        return this.numberOfMonth;
    }
    @Override
    public List<IMonthTemplate> getMonthList() {
        return this.monthList;
    }

    @Override
    public Integer getIndexOfMonthByName(String name) {
        for(int iterator=0; iterator<numberOfMonth; iterator++){
            if(monthList.get(iterator).getName().equals(name)){
                return iterator;
            }
        }
        return null;
    }

}
