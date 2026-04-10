package level3;

import java.util.HashMap;

public class Test6 {
    public static void main(String[] args) {
        HashMap<Usr, String> map = new HashMap<>();
        
        Usr u1 = new Usr(1, "Ahmed");
        Usr u2 = new Usr(1, "Ahmed");
        Usr u3 = new Usr(2, "Mahmoud");
        
        map.put(u1, "Manager");
        System.out.println("Added u1 -> Manager");
        
        map.put(u2, "Employee");
        System.out.println("Added u2 -> Employee");
        System.out.println("Total: " + map.size());
        System.out.println("Expected: 1 (u1 and u2 are equal)");
        
        map.put(u3, "Admin");
        System.out.println("Added u3 -> Admin");
        System.out.println("Total: " + map.size());
        
        Usr u4 = new Usr(1, "Ahmed");
        System.out.println("Search with u4 (id=1)");
        System.out.println("Value: " + map.get(u4));
    }
}
