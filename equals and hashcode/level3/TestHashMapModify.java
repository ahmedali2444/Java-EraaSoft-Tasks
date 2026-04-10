package level3;

import java.util.HashMap;

public class TestHashMapModify {
    public static void main(String[] args) {
        HashMap<User, String> map = new HashMap<>();
        
        User u1 = new User(1, "Ahmed");
        map.put(u1, "Manager");
        
        System.out.println("أضفنا: id=1, name=Ahmed");
        System.out.println("البحث برجل object نفسه: " + map.get(u1));
        
        User u2 = new User(1, "Amira");
        System.out.println("البحث برجل object جديد (نفس id, اسم مختلف): " + map.get(u2));
        
        System.out.println("\n--- تعديل الكائن بعد الإضافة ---");
        u1.name = "Ahm";
        System.out.println("غيرنا u1.name إلى Ahm");
        System.out.println("البحث بنفس u1: " + map.get(u1));
        System.out.println("هذا مشكلة! الـ key موجود لكن لا نستطيع الوصول إليه");
    }
}
