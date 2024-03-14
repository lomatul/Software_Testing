package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VanClasstest {
    public static Van van;
    public static Van van2;
    public static Van VanUnderMaintanace;
    @BeforeAll
    public static void setup() {
        van= new Van("V_abc", 2023, "Toyota", "Hilux", 0, new VehicleType(15, new DateTime(2, 2, 2024)));
        van2= new Van("V_abcd", 2023, "Toyota", "Hilux", 1, new VehicleType(15, new DateTime(2, 2, 2024)));
        VanUnderMaintanace= new Van("V_abcde", 2023, "Ford", "Transit", 2, new VehicleType(15, new DateTime(2, 2, 2024)));
    }

    @Test
    public void LateFeeSameDayTest() {
        DateTime startDate = new DateTime(1, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, van.getLateFee(endDate, startDate));
    }

    @Test
    public void LateFeePositiveDaysTest() {
        DateTime startDate = new DateTime(14, 4, 2024);
        DateTime endDate = new DateTime(16, 4, 2024);
        assertEquals(598.0 , van.getLateFee(endDate, startDate));
    }

    @Test
    public void LateFeeNegativeDaysTest() {
        DateTime startDate = new DateTime(10, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, van.getLateFee(endDate, startDate));
    }

    @Test
    public void GetLateFee_NullStartDatetest() {
        DateTime startDate = null;
        DateTime endDate = new DateTime(2024, 3, 10);

        assertThrows(NullPointerException.class, () -> {
            van.getLateFee(endDate, startDate);
        });
    }

    @Test
    public void ReturnVehicletest() {
        van2.records[0] = new RentalRecord("R_001", new DateTime(12, 3, 2024), new DateTime(15, 3, 2024));
        assertTrue(van2.returnVehicle(new DateTime(16, 3, 2024)));
        assertEquals(0, van2.vehicleStatus);
    }
    @Test
    public void ReturnVehicleBeforeminimumdaystest() {
        van2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));
        assertFalse(van2.returnVehicle(new DateTime(4, 3, 2024)));
    }
    @Test
    public void ReturnNotRentedVehicletest() {
        assertFalse(van.returnVehicle(new DateTime(14, 4, 2024)));
    }

    @Test
    public void VanNotUnderMaintenanceTest() {
        assertFalse(van.completeMaintenance(new DateTime(5, 2, 2024)));
        assertEquals(0, van.vehicleStatus);
    }

    @Test
    public void VanUnderMaintenanceTest() {
        assertTrue(VanUnderMaintanace.completeMaintenance(new DateTime(5, 2, 2024)));
        assertEquals(0, VanUnderMaintanace.vehicleStatus);
    }


    @Test
    public void GetDetailsEmptyRecordsTest() {
        String expectedValue = "Vehicle ID:\tV_abc\n" +
                " Year:\t 2023\n" +
                " Make:\tToyota\n" +
                " Model:\tHilux\n" +
                " Number of Seats:\t15\n" +
                " Status:\tAvailable\n" +
                "Last maintenance date:\t02/02/2024\n" +
                "RENTAL RECORD:\tempty";


        String actualValue = van.getDetails();

        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void GetDetailsWithRecordsTest() {
        van2.records[0] = new RentalRecord("R_001", new DateTime(2, 3, 2024), new DateTime(5, 3, 2024));
        van2.vehicleStatus=1;
        String expectedValue = "Vehicle ID:\tV_abcd\n" +
                " Year:\t 2023\n" +
                " Make:\tToyota\n" +
                " Model:\tHilux\n" +
                " Number of Seats:\t15\n" +
                " Status:\tRented\n" +
                "Last maintenance date:\t02/02/2024\n" +
                "RENTAL RECORD:\n" +
                "Record ID:             R_001\n" +
                "Rent Date:             02/03/2024\n" +
                "Estimated Return Date: 05/03/2024                     \n----------                     \n";

        String actualValue = van2.getDetails();

        assertEquals(expectedValue, actualValue);
    }

}
