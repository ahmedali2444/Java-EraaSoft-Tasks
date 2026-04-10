package level2;

import java.util.HashSet;

public class Test4 {
    public static void main(String[] args) {
        HashSet<Stu2> set = new HashSet<>();
        
        for (int i = 0; i < 10; i++) {
            if (i < 3) {
                set.add(new Stu2(1, "Ali"));
            } else if (i < 7) {
                set.add(new Stu2(2, "Mostafa"));
            } else {
                set.add(new Stu2(3, "Omar"));
            }
        }
        
        System.out.println("Added 10 objects");
        System.out.println("Total: " + set.size());
        System.out.println("Expected: 3");
    }
}
