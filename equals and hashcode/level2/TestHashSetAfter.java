package level2;

import java.util.HashSet;

public class TestHashSetAfter {
    public static void main(String[] args) {
        HashSet<StudentWithMethods> set = new HashSet<>();
        
        for (int i = 0; i < 10; i++) {
            if (i < 3) {
                set.add(new StudentWithMethods(1, "Ali"));
            } else if (i < 7) {
                set.add(new StudentWithMethods(2, "Mostafa"));
            } else {
                set.add(new StudentWithMethods(3, "Omar"));
            }
        }
        
        System.out.println("Added 10 objects");
        System.out.println("Actual total: " + set.size());
        System.out.println("Expected: 3");
    }
}
