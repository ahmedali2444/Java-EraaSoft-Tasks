package level1;

public class Test1 {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ahmed");
        Person p3 = p1;
        
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("p1 == p3: " + (p1 == p3));
        System.out.println("p1.equals(p3): " + p1.equals(p3));
    }
}
