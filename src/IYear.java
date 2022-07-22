public interface IYear {
    /*
    Выдает количество дней в месяце под номером offset
     */
    public int getDayQuantityInMonth(int offset);
    /*
    Выдает день недели первого дня месяца под номером offset
     */
    public String getNameOfFirstDay(int offset);
    /*
    Выдает название месяца под номером offset
     */
    public String getMonthName(int offset);
    /*
    По названию месяца выдает его номер
     */
    public int getMonthNumber(String name);
}
