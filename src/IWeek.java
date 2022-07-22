public interface IWeek { // Этот класс хранит всю информацию о дне, как части месяца
    /*
    Получает на вход названия дня недели и выдает его номер в неделе, начиная с 0
     */
    int getIntFromString(String strWeekDay);
    /*
    Получает на вход номер дня недели и выдает его название
     */
    String getStringFromInt(int intWeekDay);
    /*
    Получает на вход номер дня недели и выдает рабочий он или нет
    */
    public boolean getIsWorking(int number);
}
