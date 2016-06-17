package time_api;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Парсинг строк с датами и временем.
 */
public class Parsing {
    @Test
    public void date() {
        assertNotNull(LocalDate.parse("2015-03-25"));
    }

    @Test
    public void dateTime() {
        //Without formatter
        assertNotNull(LocalDateTime.parse("2015-03-25T10:15:30"));

        //With formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        assertNotNull(LocalDateTime.parse("25.03.2015 10:40:50", formatter));
    }

    @Test
    public void instant() {
        assertNotNull(Instant.parse("2007-03-25T10:15:30.00Z"));
    }

    @Test
    public void yearMonth() {
        assertNotNull(YearMonth.parse("2007-03"));
    }

    @Test
    public void zonedDateTime() {
        ZonedDateTime exp1 = ZonedDateTime.of(2007, 12, 3, 10, 15, 30, 0, ZoneId.of("Europe/Paris"));
        assertThat(ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"), equalTo(exp1));

        ZonedDateTime exp2 = ZonedDateTime.of(2007, 12, 3, 10, 15, 30, 0, ZoneId.of("+01:00"));
        assertThat(ZonedDateTime.parse("2007-12-03T10:15:30+01:00"), equalTo(exp2));

        ZonedDateTime.parse("2016-01-01T14:01:00Z");
    }
}
