package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeClasstest {
    private static DateTime testDateTime;

    @BeforeAll
    public static void initializeDateTime() {
        testDateTime = new DateTime(10, 3, 2024);
    }

    @Test
    public void GetTimetest() {
        long expectedTime = testDateTime.getTime();
        assertEquals(expectedTime, testDateTime.getTime());
    }

    @Test
    public void GetNameOfDaytest() {
        String expectedDay = "Sunday";
        assertEquals(expectedDay, testDateTime.getNameOfDay());
    }

    @Test
    public void ToStringtest() {
        String expectedString = "10/03/2024";
        assertEquals(expectedString, testDateTime.toString());
    }

    @Test
    public void GetCurrentTimetest() {
        String expectedCurrentTime = DateTime.getCurrentTime();
        assertEquals(expectedCurrentTime, DateTime.getCurrentTime());
    }

    @Test
    public void GetFormattedDatetest() {
        String expectedFormattedDate = "10/03/2024";
        assertEquals(expectedFormattedDate, testDateTime.getFormattedDate());
    }

    @Test
    public void GetEightDigitDatetest() {
        String expectedEightDigitDate = "10032024";
        assertEquals(expectedEightDigitDate, testDateTime.getEightDigitDate());
    }

    @Test
    public void DiffDaystest() {
        DateTime startDate = new DateTime(10, 3, 2024);
        DateTime endDate = new DateTime(15, 3, 2024);
        int expectedDiffDays = 5;
        assertEquals(expectedDiffDays, DateTime.diffDays(endDate, startDate));
    }

}
