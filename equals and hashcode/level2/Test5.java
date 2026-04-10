package level2;

import java.util.HashSet;

public class Test5 {
    public static void main(String[] args) {
        System.out.println("=== Test Product by code ===\n");
        
        HashSet<Prod> products = new HashSet<>();
        products.add(new Prod("P001", "Laptop"));
        products.add(new Prod("P001", "Desktop"));
        products.add(new Prod("P002", "Phone"));
        
        System.out.println("Added 3 products");
        System.out.println("Total: " + products.size());
        System.out.println("Expected: 2 (only one P001)");
    }
}
