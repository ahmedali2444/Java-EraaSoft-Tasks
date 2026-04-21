package level1;

import java.util.HashSet;

public class TestEqualsWithoutHashCode {
    public static void main(String[] args) {
        PersonWithEqualsOnly p1 = new PersonWithEqualsOnly(1, "Ahmed");
        PersonWithEqualsOnly p2 = new PersonWithEqualsOnly(1, "Ahmed");

        HashSet<PersonWithEqualsOnly> people = new HashSet<>();
        people.add(p1);
        people.add(p2);

        System.out.println("Override equals() only:");
        System.out.println("p1.equals(p2) -> " + p1.equals(p2));
        System.out.println("p1.hashCode() -> " + p1.hashCode());
        System.out.println("p2.hashCode() -> " + p2.hashCode());
        System.out.println("HashSet size -> " + people.size());
        System.out.println("Because hashCode() was not overridden, HashSet treats them as different buckets.");
    }
}
