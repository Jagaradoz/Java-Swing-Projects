public class Main {
    public static void main(String[] args) {
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day + " => " + day.getDayNumber());
        }
    }
}
