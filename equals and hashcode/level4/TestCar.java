package level4;

import java.util.HashMap;

public class TestCar {
    public static void main(String[] args) {
        HashMap<Code, String> cars = new HashMap<>();
        
        Code car1 = new Code("ABC123", "Red");
        cars.put(car1, "Toyota");
        System.out.println("Added: ABC123 -> Toyota");
        
        Code car2 = new Code("ABC123", "Blue");
        String value = cars.put(car2, "Honda");
        System.out.println("Added: ABC123 -> Honda");
        System.out.println("Previous value: " + value);
        System.out.println("Total: " + cars.size());
        System.out.println("Current value: " + cars.get(new Code("ABC123", "Green")));
    }
}
