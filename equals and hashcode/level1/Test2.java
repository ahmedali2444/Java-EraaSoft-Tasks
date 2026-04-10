package level1;

public class Test2 {
    public static void main(String[] args) {
        Person2 p1 = new Person2(1, "Ahmed");
        Person2 p2 = new Person2(1, "Ahmed");
        Person2 p3 = new Person2(2, "Mahmoud");
        
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode());
    }
}
