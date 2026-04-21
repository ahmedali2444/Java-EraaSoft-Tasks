package level4;

import java.util.HashMap;

public class TestCar {
    public static void main(String[] args) {
        HashMap<Car, String> carOwners = new HashMap<>();

        Car firstReading = new Car("ABC123", "Red");
        Car secondReading = new Car("ABC123", "Blue");
        Car anotherCar = new Car("XYZ999", "Black");

        carOwners.put(firstReading, "Nour");
        String previousOwner = carOwners.put(secondReading, "Mariam");
        carOwners.put(anotherCar, "Omar");

        System.out.println("Car used as a key in a vehicle registry:");
        System.out.println("Previous value for plate ABC123 -> " + previousOwner);
        System.out.println("Registry size -> " + carOwners.size());
        System.out.println("Lookup using a new object with same plate -> " + carOwners.get(new Car("ABC123", "White")));
        System.out.println("Color changes, but plate number identifies the car in the map.");
    }
}
