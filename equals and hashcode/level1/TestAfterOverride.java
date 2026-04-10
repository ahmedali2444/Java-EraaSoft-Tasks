package level1;

public class TestAfterOverride {
    public static void main(String[] args) {
        PersonWithEquals p1 = new PersonWithEquals(1, "Ahmed");
        PersonWithEquals p2 = new PersonWithEquals(1, "Ahmed");
        PersonWithEquals p3 = new PersonWithEquals(2, "Fatima");
        
        System.out.println("=== بعد Override equals فقط ===");
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println("p1 == p2: " + (p1 == p2));
        
        System.out.println("\n=== hashCode مختلف رغم equals ===");
        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode());
    }
}
