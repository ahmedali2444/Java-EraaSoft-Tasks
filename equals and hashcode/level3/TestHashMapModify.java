package level3;

import java.util.HashMap;

public class TestHashMapModify {
    public static void main(String[] args) {
        HashMap<PersonKey, String> roles = new HashMap<>();

        PersonKey employee = new PersonKey(10, "Ali");
        roles.put(employee, "Employee");

        System.out.println("Before modifying the key:");
        System.out.println("roles.get(employee) -> " + roles.get(employee));
        System.out.println("roles.get(new PersonKey(10, \"Ali\")) -> " + roles.get(new PersonKey(10, "Ali")));

        employee.setName("Ali Updated");
        System.out.println();
        System.out.println("After changing name only:");
        System.out.println("roles.get(employee) -> " + roles.get(employee));
        System.out.println("Changing name does not break retrieval because equality uses id only.");

        employee.setId(99);
        System.out.println();
        System.out.println("After changing id:");
        System.out.println("roles.get(employee) -> " + roles.get(employee));
        System.out.println("roles.get(new PersonKey(10, \"Ali Updated\")) -> " + roles.get(new PersonKey(10, "Ali Updated")));
        System.out.println("roles.get(new PersonKey(99, \"Ali Updated\")) -> " + roles.get(new PersonKey(99, "Ali Updated")));
        System.out.println("Map entries -> " + roles);
        System.out.println("The entry is still inside the map, but it is now stored in the wrong bucket.");
    }
}
