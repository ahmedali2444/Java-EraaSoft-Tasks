package level3;

import java.util.HashMap;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<PersonKey, String> roles = new HashMap<>();

        PersonKey firstKey = new PersonKey(1, "Ahmed");
        PersonKey duplicateKey = new PersonKey(1, "Ahmed");
        PersonKey anotherPerson = new PersonKey(2, "Mahmoud");

        roles.put(firstKey, "Employee");
        System.out.println("After inserting firstKey -> " + roles);

        roles.put(duplicateKey, "Manager");
        System.out.println("After inserting duplicateKey with same id -> " + roles);
        System.out.println("Map size after duplicate id -> " + roles.size());

        roles.put(anotherPerson, "Admin");

        System.out.println("HashMap with Person as a key:");
        System.out.println("Inserted two keys with the same id.");
        System.out.println("Map size -> " + roles.size());
        System.out.println("Value for id=1 -> " + roles.get(new PersonKey(1, "Any Name")));
        System.out.println("Value for id=2 -> " + roles.get(new PersonKey(2, "Different Name")));
        System.out.println("Because equality is based on id, the second put() replaces the first value.");
    }
}
