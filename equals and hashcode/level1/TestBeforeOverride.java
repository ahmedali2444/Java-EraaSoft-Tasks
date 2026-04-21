package level1;

public class TestBeforeOverride {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ahmed");
        Person p3 = new Person(1, "Ahmed");

        System.out.println("Before overriding equals():");
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p1 == p2 -> " + (p1 == p2));
        System.out.println("p1.equals(p2) -> " + p1.equals(p2));
        System.out.println("p2.equals(p3) -> " + p2.equals(p3));
        System.out.println("Although the data is the same, Object.equals() compares references.");
    }
}
