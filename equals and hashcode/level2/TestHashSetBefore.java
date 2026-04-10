package level2;

import java.util.HashSet;

public class TestHashSetBefore {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();
        
        Student s1 = new Student(1, "Ali");
        Student s2 = new Student(1, "Ali");
        Student s3 = new Student(2, "Mostafa");
        Student s4 = new Student(2, "Mostafa");
        
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        
        System.out.println("Total: " + set.size());
        System.out.println("Expected: 4");
    }
}
