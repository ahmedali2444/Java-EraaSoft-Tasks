package level2;

import java.util.HashSet;

public class Test3 {
    public static void main(String[] args) {
        HashSet<Stu> set = new HashSet<>();
        
        Stu s1 = new Stu(1, "Ali");
        Stu s2 = new Stu(1, "Ali");
        Stu s3 = new Stu(2, "Mostafa");
        Stu s4 = new Stu(2, "Mostafa");
        
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        
        System.out.println("Total: " + set.size());
        System.out.println("Expected: 4");
    }
}
