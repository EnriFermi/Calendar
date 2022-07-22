public class Main {
    public static void main(String[] args) {
        Calendar x = new Calendar(2020);
        System.out.println(x.getWeekDay(30, "June"));
        System.out.print(x.getIsWorking(30, "June"));
    }
}
