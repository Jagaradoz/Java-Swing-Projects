import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // LOCAL DATE
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2000, 12, 31);
        LocalDate localDate3 = LocalDate.of(1970, Month.JANUARY, 1);


        // LOCAL TIME
        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.of(3, 40, 0);
        LocalTime localTime3 = LocalTime.of(12, 0, 0);


        // LOCAL DATE AND TIME
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate1, localTime1);
        LocalDateTime localDateTime3 = LocalDateTime.of(2000, Month.JANUARY, 31, 0, 0, 0);


        // ZONE ID FOR ZONED DATE TIME
        ZoneId zoneId1 = ZoneId.of("America/New_York");
        ZoneId zoneId2 = ZoneId.of("Europe/London");


        // ZONED DATE TIME
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zoned1 = ZonedDateTime.now(zoneId1);
        ZonedDateTime zoned2 = ZonedDateTime.now(zoneId2);


        // DATE TIME FORMATTER
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate1 = LocalDateTime.now().format(dateTimeFormatter);


        // SIMPLE DATE FORMAT
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate2 = simpleDateFormat.format(new Date());


        // DATE CALCULATIONS
        LocalDate tomorrow = localDate1.plusDays(1);
        LocalDate nextWeek = localDate1.plusWeeks(1);
        LocalDate previousMonth = localDate1.minusMonths(1);


        // TIME CALCULATIONS
        LocalTime timePlusHours = localTime1.plusHours(2);
        LocalTime timePlusMinutes = localTime1.plusMinutes(30);
        LocalTime timeMinusSeconds = localTime1.minusSeconds(45);

        // PERIOD AND DURATION
        Period period = Period.between(localDate2, LocalDate.now());
        Duration duration = Duration.between(localTime2, LocalTime.now());


        // DATE AND TIME COMPARISON
        boolean isBefore = localDateTime2.isBefore(localDateTime3);
        boolean isAfter = localDateTime2.isAfter(localDateTime3);
        boolean isEqual = localDateTime2.isEqual(localDateTime3);


        // TIME STAMP
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());


        // TIME STAMP
        Instant instant = Instant.now();
        Instant fromEpoch = Instant.ofEpochSecond(instant.getEpochSecond());


        // ADJUSTING DATE
        LocalDate firstDayOfMonth = localDate1.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfYear = localDate1.with(TemporalAdjusters.lastDayOfYear());
        LocalDate nextMonday = localDate1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));


        // PARSE DATE AND TIME
        LocalDate parsedDate = LocalDate.parse("2024-11-25", DateTimeFormatter.ISO_DATE);
        LocalTime parsedTime = LocalTime.parse("15:30:00", DateTimeFormatter.ISO_TIME);
    }
}
