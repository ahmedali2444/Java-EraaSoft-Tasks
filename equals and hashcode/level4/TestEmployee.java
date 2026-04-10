package level4;

import java.util.HashSet;

public class TestEmployee {
    public static void main(String[] args) {
        HashSet<Employ> employees = new HashSet<>();
        employees.add(new Employ(1, "ali@mail.com"));
        employees.add(new Employ(1, "ali2@mail.com"));
        employees.add(new Employ(2, "mostafa@mail.com"));
        employees.add(new Employ(3, "omar@mail.com"));
        
        System.out.println("Added 4 employees");
        System.out.println("Total: " + employees.size());
        System.out.println("Expected: 3 (only one empId=1)");
    }
}
