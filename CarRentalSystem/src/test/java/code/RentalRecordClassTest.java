package code;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RentalRecordClassTest {
    private static RentalRecord rentalRecordFirst;
    private static RentalRecord rentalRecordSecond;

    @BeforeAll
    public static void initializeRecords() {
        DateTime rentDateFirst = new DateTime(15, 3, 2024);
        DateTime estimatedReturnDateFirst = new DateTime(20, 3, 2024);
        rentalRecordFirst = new RentalRecord("R_abc", rentDateFirst, estimatedReturnDateFirst);

        DateTime rentDateSecond = new DateTime(18, 3, 2024);
        DateTime estimatedReturnDateSecond = new DateTime(25, 3, 2024);
        rentalRecordSecond = new RentalRecord("R_def", rentDateSecond, estimatedReturnDateSecond);
    }

    @Test
    public void testGetEstimatedReturnDate_RecordFirst() {
        DateTime expectedEstimatedReturnDate = new DateTime(20, 3, 2024);
        DateTime actualEstimatedReturnDate = rentalRecordFirst.getEstimatedReturnDate();

        assertEquals(expectedEstimatedReturnDate.getFormattedDate(), actualEstimatedReturnDate.getFormattedDate());
    }

    @Test
    public void testGetRentDate_RecordFirst() {
        DateTime expectedRentDate = new DateTime(15, 3, 2024);
        DateTime actualRentDate = rentalRecordFirst.getRentDate();

        assertEquals(expectedRentDate.getFormattedDate(), actualRentDate.getFormattedDate());
    }

    @Test
    @Order(1)
    public void testToString_NoAdditionalData_RecordFirst() {
        String expected = "R_abc:15/03/2024:20/03/2024:none:none:none";
        String actual = rentalRecordFirst.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void testGetDetails_NoAdditionalData_RecordFirst() {
        String expected = "Record ID:             R_abc\n" +
                "Rent Date:             15/03/2024\n" +
                "Estimated Return Date: 20/03/2024";

        String actual = rentalRecordFirst.getDetails();
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void testToString_WithAdditionalData_RecordSecond() {
        DateTime actualReturnDate = new DateTime(22, 3, 2024);
        Double rentalFee = 60.0;
        Double lateFee = 15.0;

        rentalRecordSecond.setData(actualReturnDate, rentalFee, lateFee);

        String expected = "R_def:18/03/2024:25/03/2024:22/03/2024:60.0:15.0";
        String actual = rentalRecordSecond.toString();

        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void testGetDetails_WithAdditionalData_RecordSecond() {
        DateTime actualReturnDate = new DateTime(22, 3, 2024);
        Double rentalFee = 60.0;
        Double lateFee = 15.0;

        rentalRecordSecond.setData(actualReturnDate, rentalFee, lateFee);

        String expected = "Record ID:             R_def\n" +
                "Rent Date:             18/03/2024\n" +
                "Estimated Return Date: 25/03/2024\n" +
                "Actual Return Date:    22/03/2024\n" +
                "Rental Fee:            60.00\n" +
                "Late Fee:              15.00";

        String actual = rentalRecordSecond.getDetails();
        assertEquals(expected, actual);
    }


}