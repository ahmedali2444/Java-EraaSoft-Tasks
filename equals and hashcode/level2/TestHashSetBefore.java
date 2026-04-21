package level2;

import java.util.HashSet;

public class TestHashSetBefore {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<>();

        set.add(new Person(1, "Ali"));
        set.add(new Person(1, "Ali"));
        set.add(new Person(2, "Mostafa"));
        set.add(new Person(2, "Mostafa"));

        System.out.println("HashSet before overriding equals() and hashCode():");
        System.out.println("Added 4 objects with repeated data.");
        System.out.println("HashSet size -> " + set.size());
        System.out.println("All 4 remain because each object has a different reference.");
    }
}
