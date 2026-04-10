package level4;

import java.util.HashSet;

public class Test8 {
    public static void main(String[] args) {
        HashSet<Work> employees = new HashSet<>();
        employees.add(new Work(1, "ali@mail.com"));
        employees.add(new Work(1, "ali2@mail.com"));
        employees.add(new Work(2, "mostafa@mail.com"));
        employees.add(new Work(3, "omar@mail.com"));
        
        System.out.println("Added 4 employees");
        System.out.println("Total: " + employees.size());
        System.out.println("Expected: 3 (only one empId=1)");
    }
}
