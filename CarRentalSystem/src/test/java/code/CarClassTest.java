package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarClassTest {
    public static Car car;
    public static Car car2;
    public static Car CarUnderMaintanace;
    @BeforeAll
    public static void setup() {
        car= new Car("C_abc", 2022, "Honda", "Accord", 0, new VehicleType(4));
        car2= new Car("C_abcd", 2022, "Honda", "Accord", 1, new VehicleType(4));
        CarUnderMaintanace= new Car("C_abcde", 2022, "Honda", "Accord", 2, new VehicleType(4));
    }

    @Test
    public void SameDayGetLateFeetest() {
        DateTime startDate = new DateTime(1, 3, 2024);
        DateTime endDate = new DateTime(1, 3, 2024);
        assertEquals(0.0, car.getLateFee(endDate, startDate));
    }

    @Test
    public void LateFeePositiveDaystest() {
        DateTime startDate = new DateTime(12, 3, 2024);
        DateTime endDate = new DateTime(15, 3, 2024);
        assertEquals(292.5 , car.getLateFee(endDate, startDate));
    }

    @Test
    public void LateFeeNegativeDaystest() {
        DateTime startDate = new DateTime(20, 3, 2024);
        DateTime endDate = new DateTime(10, 3, 2024);
        assertEquals(0.0, car.getLateFee(endDate, startDate));
    }

    @Test
    public void GetLateFee_NullStartDatetest() {
        DateTime startDate = null;
        DateTime endDate = new DateTime(2024, 3, 10);

        assertThrows(NullPointerException.class, () -> {
            car.getLateFee(endDate, startDate);
        });
    }

    @Test
    public void ReturnVehicletest() {

        car2.records[0] = new RentalRecord("R_001", new DateTime(12, 3, 2024), new DateTime(15, 3, 2024));
        assertTrue(car2.returnVehicle(new DateTime(16, 3, 2024)));
        assertEquals(0, car2.vehicleStatus);


    }

    @Test
    public void ReturnNotRentedVehicletest() {

        assertFalse(car.returnVehicle(new DateTime(24, 3, 2024)));

    }



    @Test
    public void ReturnVehicleBeforeminimumdaystest() {

        car2.records[0] = new RentalRecord("R_001", new DateTime(12, 3, 2024), new DateTime(15, 3, 2024));
        assertFalse(car2.returnVehicle(new DateTime(14, 3, 2024)));


    }


    @Test
    public void CarNotUnderMaintanaceTest() {
        assertFalse(car.completeMaintenance());
    }

    @Test
    public void CarUnderMaintanaceTest() {
        assertTrue(CarUnderMaintanace.completeMaintenance());
        assertEquals(0, CarUnderMaintanace.vehicleStatus);
    }

    @Test
    public void GetDetailsEmptyRecordstest() {
        String expectedValue = "Vehicle ID:\tC_abc\n Year:\t 2022\n Make:\tHonda\n Model:\tAccord\n Number of Seats:\t4\n Status:\tAvailable\nRENTAL RECORD:\tempty";


        String actualValue = car.getDetails();

        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void GetDetailsWithRecordstest() {
        car2.records[0] = new RentalRecord("R_001", new DateTime(12, 3, 2024), new DateTime(15, 3, 2024));
        car2.vehicleStatus=1;
        String expectedValue = "Vehicle ID:\tC_abcd\n Year:\t 2022\n Make:\tHonda\n Model:\tAccord\n Number of Seats:\t4\n Status:\tRented\n" +
                "RENTAL RECORD\n" +
                "Record ID:             R_001\n" +
                "Rent Date:             12/03/2024\n" +
                "Estimated Return Date: 15/03/2024                     \n----------                     \n";
        String actualValue = car2.getDetails();
        assertEquals(expectedValue, actualValue);
    }




}
