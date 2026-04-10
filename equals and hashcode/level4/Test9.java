package level4;

import java.util.HashMap;

public class Test9 {
    public static void main(String[] args) {
        HashMap<Car, String> cars = new HashMap<>();
        
        Car car1 = new Car("ABC123", "Red");
        cars.put(car1, "Toyota");
        System.out.println("Added: ABC123 -> Toyota");
        
        Car car2 = new Car("ABC123", "Blue");
        String value = cars.put(car2, "Honda");
        System.out.println("Added: ABC123 -> Honda");
        System.out.println("Previous value: " + value);
        System.out.println("Total: " + cars.size());
        System.out.println("Current value: " + cars.get(new Car("ABC123", "Green")));
    }
}
