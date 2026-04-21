package level2;

import java.util.HashSet;
import java.util.List;

public class TestHashSetAfter {
    public static void main(String[] args) {
        List<PersonById> people = List.of(
            new PersonById(1, "Ali"),
            new PersonById(1, "Ali"),
            new PersonById(2, "Mostafa"),
            new PersonById(3, "Omar"),
            new PersonById(3, "Omar Updated"),
            new PersonById(4, "Sara"),
            new PersonById(5, "Mona"),
            new PersonById(5, "Mona"),
            new PersonById(5, "Mona Ibrahim"),
            new PersonById(6, "Youssef")
        );

        HashSet<PersonById> set = new HashSet<>(people);

        System.out.println("HashSet after overriding equals() and hashCode() by id:");
        System.out.println("Added 10 objects.");
        System.out.println("HashSet size -> " + set.size());
        System.out.println("Remaining objects -> " + set);
        System.out.println("Only 6 remain because unique ids are: 1, 2, 3, 4, 5, 6.");
    }
}
