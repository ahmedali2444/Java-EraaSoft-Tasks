package level3;

import java.util.HashMap;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<User, String> map = new HashMap<>();
        
        User u1 = new User(1, "Ahmed");
        User u2 = new User(1, "Ahmed");
        User u3 = new User(2, "Fatima");
        
        map.put(u1, "Manager");
        System.out.println("أضفنا u1 -> Manager");
        
        map.put(u2, "Employee");
        System.out.println("أضفنا u2 -> Employee");
        System.out.println("عدد العناصر: " + map.size());
        System.out.println("المتوقع: 1 (لأن u1 و u2 متساويين)");
        
        map.put(u3, "Admin");
        System.out.println("\nأضفنا u3 -> Admin");
        System.out.println("عدد العناصر: " + map.size());
        
        User u4 = new User(1, "Ahmed");
        System.out.println("\nنبحث ب u4 (id=1)");
        System.out.println("الـ value: " + map.get(u4));
    }
}
