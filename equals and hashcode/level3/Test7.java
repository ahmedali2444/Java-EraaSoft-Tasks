package level3;

import java.util.HashMap;

public class Test7 {
    public static void main(String[] args) {
        HashMap<Usr, String> map = new HashMap<>();
        
        Usr u1 = new Usr(1, "Ahmed");
        map.put(u1, "Manager");
        
        System.out.println("Added: id=1, name=Ahmed");
        System.out.println("Search with same object: " + map.get(u1));
        
        Usr u2 = new Usr(1, "Mohamed");
        System.out.println("Search with new object (same id, different name): " + map.get(u2));
        
        u1.name = "Ahm";
        System.out.println("Changed u1.name to Ahm");
        System.out.println("Search with modified u1: " + map.get(u1));
        System.out.println("Problem! Key exists but we cannot access it");
    }
}
