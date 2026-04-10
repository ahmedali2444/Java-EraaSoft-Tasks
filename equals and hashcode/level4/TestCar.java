package level4;

import java.util.HashMap;

public class TestCar {
    public static void main(String[] args) {
        HashMap<Code, String> cars = new HashMap<>();
        
        Code car1 = new Code("ABC123", "Red");
        car1 = cars.put(car1, "Toyota");
        System.out.println("أضفنا: ABC123 -> Toyota");
        
        Code car2 = new Code("ABC123", "Blue");
        String value = cars.put(car2, "Honda");
        System.out.println("أضفنا: ABC123 -> Honda");
        System.out.println("القيمة السابقة: " + value);
        System.out.println("عدد العناصر: " + cars.size());
        System.out.println("القيمة الحالية: " + cars.get(new Code("ABC123", "Green")));
    }
}
