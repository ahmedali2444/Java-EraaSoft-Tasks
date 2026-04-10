package level3;

import java.util.HashMap;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<User, String> map = new HashMap<>();
        
        User u1 = new User(1, "Ahmed");
        User u2 = new User(1, "Ahmed");
        User u3 = new User(2, "Mahmoud");
        
        map.put(u1, "Manager");
        System.out.println("Added u1 -> Manager");
        
        map.put(u2, "Employee");
        System.out.println("Added u2 -> Employee");
        System.out.println("Total: " + map.size());
        System.out.println("Expected: 1 (because u1 and u2 are equal)");
        
        map.put(u3, "Admin");
        System.out.println("Added u3 -> Admin");
        System.out.println("Total: " + map.size());
        
        User u4 = new User(1, "Ahmed");
        System.out.println("Search with u4 (id=1)");
        System.out.println("Value: " + map.get(u4));
    }
}
