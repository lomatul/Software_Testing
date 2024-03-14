package code;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehicleClassTest {

    private static Vehicle carFirst;
    private static Vehicle carSecond;
    private static Vehicle vanFirst;
    private static Vehicle vanSecond;

    private static Vehicle carThird;
    private static Vehicle vanThird;

    @BeforeAll
    public static void initializeVehicles() {
        carFirst = new Car("C_abc", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        vanFirst = new Van("V_abc", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
        carSecond = new Car("C_def", 2022, "Toyota", "Corolla", 1, new VehicleType(4));
        vanSecond = new Van("V_def", 2022, "Ford", "Transit", 1, new VehicleType(15, new DateTime(12, 2, 2023)));
        carThird = new Car("C_ghi", 2022, "Toyota", "Corolla", 0, new VehicleType(4));
        vanThird = new Van("V_ghi", 2022, "Ford", "Transit", 0, new VehicleType(15, new DateTime(12, 2, 2023)));
    }

    @Test
    public void testRentCar() {
        assertTrue(carFirst.rent("1", new DateTime(10, 3, 2024), 5));
        assertEquals(1, carFirst.vehicleStatus);
    }

    @Test
    public void testRentVan() {
        assertTrue(vanFirst.rent("2", new DateTime(13, 2, 2023), 7));
        assertEquals(1, vanFirst.vehicleStatus);
    }

    @Test
    public void testRentInvalidDaysCar() {
        assertFalse(carFirst.rent("4", new DateTime(20, 3, 2024), 1));
    }

    @Test
    public void testRentInvalidDaysVan() {
        assertFalse(vanSecond.rent("1", new DateTime(22, 3, 2024), 15));
    }

    @Test
    public void testRentAlreadyRentedCar() {
        assertFalse(carSecond.rent("2", new DateTime(25, 3, 2024), 4));
    }

    @Test
    public void testRentAlreadyRentedVan() {
        assertFalse(vanSecond.rent("2", new DateTime(25, 3, 2024), 4));
    }

    @Test
    public void testRentMaxDaysCar() {
        assertFalse(carFirst.rent("8", new DateTime(30, 3, 2024), 15));
    }

    @Test
    public void testRentZeroDaysVan() {
        assertFalse(vanFirst.rent("10", new DateTime(5, 4, 2024), 0));
    }

    @Test
    public void testRentNegativeDays() {
        assertFalse(vanFirst.rent("11", new DateTime(8, 4, 2024), -3));
        assertFalse(carFirst.rent("11", new DateTime(8, 4, 2024), -3));
    }

    @Test
    public void testGetVehicleId() {
        assertEquals("C_abc", carFirst.getVehicleId());
        assertEquals("V_abc", vanFirst.getVehicleId());
    }

    @Test
    @Order(1)
    public void testPerformMaintenanceCar() {
        assertTrue(carThird.performMaintenance());
        assertEquals(2, carThird.vehicleStatus);
    }

    @Test
    @Order(2)
    public void testPerformMaintenanceVan() {
        assertTrue(vanThird.performMaintenance());
        assertEquals(2, vanThird.vehicleStatus);
    }

    @Test
    @Order(3)
    public void testPerformMaintenance() {
        assertFalse(vanThird.performMaintenance());
        assertEquals(2, vanThird.vehicleStatus);
        assertFalse(carThird.performMaintenance());
        assertEquals(2, carThird.vehicleStatus);
    }

    @Test
    public void testToStringCar() {
        String expectedCarString = "C_abc:2022:Toyota:Corolla:4:Available";
        assertEquals(expectedCarString, carFirst.toString());
    }

    @Test
    public void testToStringVan() {
        String expectedVanString = "V_abc:2022:Ford:Transit:15:Available:12/02/2023";
        assertEquals(expectedVanString, vanFirst.toString());
    }

    @Test
    public void testGetDetailsCar() {
        String expectedCarDetails = "Vehicle ID:\tC_def\n" +
                " Year:\t 2022\n" +
                " Make:\tToyota\n" +
                " Model:\tCorolla\n" +
                " Number of Seats:\t4\n" +
                " Status:\tRented\n" +
                "RENTAL RECORD:\tempty";
        assertEquals(expectedCarDetails, carSecond.getDetails());
    }

    @Test
    public void testGetDetailsVan() {
        String expectedVanDetails = "Vehicle ID:\tV_def\n" +
                " Year:\t 2022\n" +
                " Make:\tFord\n" +
                " Model:\tTransit\n" +
                " Number of Seats:\t15\n" +
                " Status:\tRented\n" +
                "Last maintenance date:\t12/02/2023\n" +
                "RENTAL RECORD:\tempty";
        assertEquals(expectedVanDetails, vanSecond.getDetails());
    }

    @Test
    public void testGetDetailsCarWithRecord() {
        carThird.records[0] = new RentalRecord("R_001", new DateTime(10, 3, 2024), new DateTime(15, 3, 2024));
        String expectedCarDetails = "Vehicle ID:\tC_ghi\n" +
                " Year:\t 2022\n" +
                " Make:\tToyota\n" +
                " Model:\tCorolla\n" +
                " Number of Seats:\t4\n" +
                " Status:\tMaintenance\n" +
                "RENTAL RECORD\n" +
                "Record ID:             R_001\n" +
                "Rent Date:             10/03/2024\n" +
                "Estimated Return Date: 15/03/2024                     \n" +
                "----------                     \n";
        assertEquals(expectedCarDetails, carThird.getDetails());
    }

    @Test
    public void testGetDetailsVanWithRecords() {
        vanThird.records[0] = new RentalRecord("R_001", new DateTime(12, 3, 2024), new DateTime(18, 3, 2024));
        String expectedVanDetails = "Vehicle ID:\tV_ghi\n" +
                " Year:\t 2022\n" +
                " Make:\tFord\n" +
                " Model:\tTransit\n" +
                " Number of Seats:\t15\n" +
                " Status:\tMaintenance\n" +
                "Last maintenance date:\t12/02/2023\n" +
                "RENTAL RECORD:\n" +
                "Record ID:             R_001\n" +
                "Rent Date:             12/03/2024\n" +
                "Estimated Return Date: 18/03/2024                     \n" +
                "----------                     \n";
        assertEquals(expectedVanDetails, vanThird.getDetails());
    }

    @Test
    public void testGetLastElementIndex() {
        assertEquals(-1, carFirst.getLastElementIndex());

        carFirst.records[0] = new RentalRecord("R_001", new DateTime(10, 3, 2024), new DateTime(15, 3, 2024));
        assertEquals(0, carFirst.getLastElementIndex());

        carFirst.records[1] = new RentalRecord("R_002", new DateTime(20, 3, 2024), new DateTime(25, 3, 2024));
        carFirst.records[2] = new RentalRecord("R_003", new DateTime(1, 4, 2024), new DateTime(5, 4, 2024));
        assertEquals(2, carFirst.getLastElementIndex());

        assertEquals(-1, vanFirst.getLastElementIndex());

        vanFirst.records[0] = new RentalRecord("R_101", new DateTime(2024, 3, 12), new DateTime(2024, 3, 18));
        assertEquals(0, vanFirst.getLastElementIndex());

        vanFirst.records[1] = new RentalRecord("R_102", new DateTime(2024, 3, 22), new DateTime(2024, 3, 28));
        vanFirst.records[2] = new RentalRecord("R_103", new DateTime(2024, 4, 5), new DateTime(2024, 4, 10));
        assertEquals(2, vanFirst.getLastElementIndex());
    }
}
