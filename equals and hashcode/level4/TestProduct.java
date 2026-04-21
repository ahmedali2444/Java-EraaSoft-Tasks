package level4;

import java.util.HashSet;

public class TestProduct {
    public static void main(String[] args) {
        HashSet<Product> products = new HashSet<>();
        products.add(new Product("P001", 1000.0));
        products.add(new Product("P001", 1200.0));
        products.add(new Product("P002", 2000.0));

        System.out.println("Product equality based on code only:");
        System.out.println("HashSet size -> " + products.size());
        System.out.println("Products -> " + products);
        System.out.println("The second P001 is treated as the same product even with a different price.");
    }
}
