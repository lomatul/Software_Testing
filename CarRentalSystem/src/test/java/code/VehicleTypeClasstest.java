package code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTypeClasstest {
    public static VehicleType cartype;

    public static VehicleType vantype;
    @BeforeAll
    public static void setup() {
        cartype=new VehicleType(4);
        vantype=new VehicleType(15, new DateTime(2, 2, 2024));
    }

    @Test
    public void GetSeatsCartest() {
        assertEquals(4, cartype.getSeats("car"));
    }

    @Test
    public void GetSeatsVantest() {
        assertEquals(15, vantype.getSeats("van"));
    }
    @Test
    public void GetSeatsInvalidTypeTest() {
        assertEquals(15, cartype.getSeats("invalid"));
    }

    @Test
    public void IndexOftest() {

        assertEquals(2, cartype.indexOf("Tuesday"));
        assertEquals(3, vantype.indexOf("Wednesday"));
        assertEquals(-1, vantype.indexOf("InvalidDay"));
    }

    @Test
    public void IndexOfNullDayTest() {
        assertEquals(-1, cartype.indexOf(null));
    }

    @Test
    public void GetLastMaintenanceCarTest() {
        DateTime lastMaintenanceCar = new DateTime(10, 3, 2024);
        cartype.setLastMaintenance(lastMaintenanceCar);
        assertEquals(lastMaintenanceCar, cartype.getLastMaintenance());
    }

    @Test
    public void GetLastMaintenanceVanTest() {
        DateTime lastMaintenanceVan = new DateTime(2, 2, 2024);
        vantype.setLastMaintenance(lastMaintenanceVan);
        assertEquals(lastMaintenanceVan, vantype.getLastMaintenance());
    }


    @Test
    public void CanBeRentedForMinimumDaysCarWeekdaytest() {
        DateTime date = new DateTime(4, 3, 2024);
        assertEquals(2, cartype.canBeRentedForMinimumDays(date, "car"));
    }

    @Test
    public void CanBeRentedForMinimumDaysCarWeekendtest() {
        DateTime date = new DateTime(16, 3, 2024);
        assertEquals(3, cartype.canBeRentedForMinimumDays(date, "car"));
    }

    @Test
    public void CanBeRentedForMinimumDaysInvalidTypeTest() {
        DateTime date = new DateTime(4, 3, 2024);
        assertEquals(1, cartype.canBeRentedForMinimumDays(date, "invalid"));
    }
    @Test
    public void CanBeRentedForMinimumDaysVantest() {
        DateTime date = new DateTime(8, 3, 2024);
        assertEquals(1, vantype.canBeRentedForMinimumDays(date, "van"));
    }
    @Test
    public void IsUnderMaintenanceVanWithinMaintenancePeriodtest() {
        DateTime rentDate = new DateTime(4, 2, 2024);
        assertFalse(vantype.IsUnderMaintenance(rentDate, "van", 9));
    }

    @Test
    public void IsUnderMaintenanceVanExceedsMaintenancePeriodtest() {
        DateTime rentDate = new DateTime(1, 3, 2024);
        assertTrue(vantype.IsUnderMaintenance(rentDate, "van", 15));
    }

    @Test
    public void IsUnderMaintenanceCartest() {
        DateTime rentDate = new DateTime(14, 3, 2024);
        cartype.setLastMaintenance(rentDate);
        assertFalse(cartype.IsUnderMaintenance(rentDate, "car", 5));
    }


}
