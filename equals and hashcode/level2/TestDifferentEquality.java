package level2;

import java.util.HashSet;

public class TestDifferentEquality {
    public static void main(String[] args) {
        System.out.println("=== Test Product بـ code ===\n");
        
        HashSet<Product> products = new HashSet<>();
        products.add(new Product("P001", "Laptop"));
        products.add(new Product("P001", "Desktop"));
        products.add(new Product("P002", "Phone"));
        
        System.out.println("أضفنا 3 products");
        System.out.println("عدد العناصر: " + products.size());
        System.out.println("المتوقع: 2 (P001 واحد فقط)");
    }
}
