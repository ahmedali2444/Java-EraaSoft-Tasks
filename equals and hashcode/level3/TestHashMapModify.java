package level3;

import java.util.HashMap;

public class TestHashMapModify {
    public static void main(String[] args) {
        HashMap<User, String> map = new HashMap<>();
        
        User u1 = new User(1, "Ahmed");
        map.put(u1, "Manager");
        
        System.out.println("Added: id=1, name=Ahmed");
        System.out.println("Search with same object: " + map.get(u1));
        
        User u2 = new User(1, "Mohamed");
        System.out.println("Search with new object (same id, different name): " + map.get(u2));
        
        u1.name = "Ahm";
        System.out.println("Changed u1.name to Ahm");
        System.out.println("Search with modified u1: " + map.get(u1));
        System.out.println("Problem! Key exists but we cannot access it");
    }
}
