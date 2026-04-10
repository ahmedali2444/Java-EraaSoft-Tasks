package level2;

import java.util.HashSet;

public class TestHashSetAfter {
    public static void main(String[] args) {
        System.out.println("=== HashSet بعد Override ===\n");
        
        HashSet<StudentWithMethods> set = new HashSet<>();
        
        for (int i = 0; i < 10; i++) {
            if (i < 3) {
                set.add(new StudentWithMethods(1, "Ali"));
            } else if (i < 7) {
                set.add(new StudentWithMethods(2, "Sara"));
            } else {
                set.add(new StudentWithMethods(3, "Omar"));
            }
        }
        
        System.out.println("أضفنا 10 objects");
        System.out.println("عدد العناصر الفعلي: " + set.size());
        System.out.println("المتوقع: 3");
    }
}
