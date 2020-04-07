package parking;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {
    private static Car car1size1;
    private static Car car2size1;
    private static Car car3size1;
    private static Car car4size2;
    private static Car car5size2;
    private static Car car6size2;
    private static Car car7size2;
    private static Car car8size3;
    private static Car car9size3;
    private static Car car10size3;

    @BeforeAll
    static void init() {
        car1size1 = new Car("car1", "001", 1);
        car2size1 = new Car("car2", "002", 1);
        car3size1 = new Car("car3", "003", 1);
        car4size2 = new Car("car4", "004", 2);
        car5size2 = new Car("car5", "005", 2);
        car6size2 = new Car("car6", "006", 2);
        car7size2 = new Car("car7", "007", 2);
        car8size3 = new Car("car8", "008", 3);
        car9size3 = new Car("car9", "009", 3);
        car10size3 = new Car("car10", "010", 3);
    }

    @Test
    void parkingTest() {
        Parking parking = new Parking(
                "Parking1",
                List.of(
                        new Place("1", 1),
                        new Place("2", 1),
                        new Place("3", 1),
                        new Place("4", 1),
                        new Place("5", 1),
                        new Place("6", 1),
                        new Place("7", 1),
                        new Place("8", 1),
                        new Place("9", 1),
                        new Place("10C", 2),
                        new Place("11C", 2),
                        new Place("12C", 2),
                        new Place("13C", 3)
                )
        );
        assertTrue(parking.accept(car1size1));
        assertTrue(parking.accept(car4size2));
        assertTrue(parking.accept(car5size2));
        assertTrue(parking.accept(car6size2));
        assertTrue(parking.acceptToPlace(car2size1, "3"));
        assertTrue(parking.acceptToPlace(car3size1, "6"));
        assertTrue(parking.accept(car8size3));
        assertTrue(parking.accept(car9size3));
        assertTrue(parking.accept(car7size2));

        assertFalse(parking.accept(car10size3));
        assertFalse(parking.accept(car1size1));
    }

    @Test
    void reportCarsTest() {
        Parking parking = new Parking(
                "Parking1",
                List.of(
                        new Place("1", 1),
                        new Place("2", 1),
                        new Place("3", 2),
                        new Place("4", 2)
                )
        );
        assertTrue(parking.accept(car1size1));
        assertTrue(parking.accept(car2size1));
        assertTrue(parking.accept(car4size2));
        assertEquals(
                new StringBuilder()
                .append("Place name: 1; size: 1; Status: [Car: car1 - 001];")
                .append("Place name: 2; size: 1; Status: [Car: car2 - 002];")
                        .toString(),
                new ReportEngine<>(parking, new ReportCars()).generate()
        );
    }

    @Test
    void reportTrucksTest() {
        Parking parking = new Parking(
                "Parking1",
                List.of(
                        new Place("1", 1),
                        new Place("2", 1),
                        new Place("3", 2),
                        new Place("4", 2)
                )
        );
        assertTrue(parking.accept(car1size1));
        assertTrue(parking.accept(car2size1));
        assertTrue(parking.accept(car4size2));
        assertEquals(
                new StringBuilder()
                        .append("Place name: 3; size: 2; Status: [Car: car4 - 004];")
                        .toString(),
                new ReportEngine<>(parking, new ReportTrucks()).generate()
        );
    }

    @Test
    void reportEmptyPlacesTest() {
        Parking parking = new Parking(
                "Parking1",
                List.of(
                        new Place("1", 1),
                        new Place("2", 1),
                        new Place("3", 2),
                        new Place("4", 2)
                )
        );
        assertTrue(parking.accept(car4size2));
        assertEquals(
                new StringBuilder()
                        .append("Place name: 1; size: 1; Status: empty place;")
                        .append("Place name: 2; size: 1; Status: empty place;")
                        .append("Place name: 4; size: 2; Status: empty place;")
                        .toString(),
                new ReportEngine<>(parking, new ReportEmptyPlaces()).generate()
        );
    }

}