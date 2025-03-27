import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Local Date
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2000, 12, 31);
        LocalDate localDate3 = LocalDate.of(1970, Month.JANUARY, 1);

        // Local Time
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(3, 40, 0);
        LocalTime localTime3 = LocalTime.of(12, 0, 0);

        // Local Date and Time
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate1, localTime1);
        LocalDateTime localDateTime3 = LocalDateTime.of(2000, Month.JANUARY, 31, 0, 0, 0);

        // Zone Id
        ZoneId zoneId1 = ZoneId.of("America/New_York");
        ZoneId zoneId2 = ZoneId.of("Europe/London");

        // Zoned Date Time
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zoned1 = ZonedDateTime.now(zoneId1);
        ZonedDateTime zoned2 = ZonedDateTime.now(zoneId2);

        // Date Time Formatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate1 = LocalDateTime.now().format(dateTimeFormatter);

        // Simple Date Format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate2 = simpleDateFormat.format(new Date());

        // Date Calculations
        LocalDate tomorrow = localDate1.plusDays(1);
        LocalDate nextWeek = localDate1.plusWeeks(1);
        LocalDate previousMonth = localDate1.minusMonths(1);

        // Time Calculations
        LocalTime timePlusHours = localTime1.plusHours(2);
        LocalTime timePlusMinutes = localTime1.plusMinutes(30);
        LocalTime timeMinusSeconds = localTime1.minusSeconds(45);

        // Period and Duration
        Period period = Period.between(localDate2, LocalDate.now());
        Duration duration = Duration.between(localTime2, LocalTime.now());

        // Date and Time Comparison
        boolean isBefore = localDateTime2.isBefore(localDateTime3);
        boolean isAfter = localDateTime2.isAfter(localDateTime3);
        boolean isEqual = localDateTime2.isEqual(localDateTime3);

        // Timestamp
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Timestamp
        Instant instant = Instant.now();
        Instant fromEpoch = Instant.ofEpochSecond(instant.getEpochSecond());

        // Adjusting Date
        LocalDate firstDayOfMonth = localDate1.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfYear = localDate1.with(TemporalAdjusters.lastDayOfYear());
        LocalDate nextMonday = localDate1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));


        // Parse Date and Time
        LocalDate parsedDate = LocalDate.parse("2024-11-25", DateTimeFormatter.ISO_DATE);
        LocalTime parsedTime = LocalTime.parse("15:30:00", DateTimeFormatter.ISO_TIME);
    }
}
