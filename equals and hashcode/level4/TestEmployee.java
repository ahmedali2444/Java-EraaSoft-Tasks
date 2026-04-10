package level4;

import java.util.HashSet;

public class TestEmployee {
    public static void main(String[] args) {
        System.out.println("=== Test بـ empId ===\n");
        
        HashSet<Employ> employees = new HashSet<>();
        employees.add(new Employ(1, "ali@mail.com"));
        employees.add(new Employ(1, "ali2@mail.com"));
        employees.add(new Employ(2, "sara@mail.com"));
        employees.add(new Employ(3, "omar@mail.com"));
        
        System.out.println("أضفنا 4 employees");
        System.out.println("عدد العناصر: " + employees.size());
        System.out.println("المتوقع: 3 (empId=1 واحد فقط)");
    }
}
