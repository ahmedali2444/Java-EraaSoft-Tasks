package level2;

import java.util.HashSet;

public class TestDifferentEquality {
    public static void main(String[] args) {
        HashSet<Product> products = new HashSet<>();
        products.add(new Product("P001", "Laptop"));
        products.add(new Product("P001", "Desktop"));
        products.add(new Product("P002", "Phone"));
        
        System.out.println("Added 3 products");
        System.out.println("Total: " + products.size());
        System.out.println("Expected: 2 (only one P001)");
    }
}
