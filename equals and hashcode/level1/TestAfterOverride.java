package level1;

public class TestAfterOverride {
    public static void main(String[] args) {
        PersonWithEqualsAndHashCode p1 = new PersonWithEqualsAndHashCode(1, "Ahmed");
        PersonWithEqualsAndHashCode p2 = new PersonWithEqualsAndHashCode(1, "Ahmed");
        PersonWithEqualsAndHashCode p3 = new PersonWithEqualsAndHashCode(1, "Aly");
        PersonWithEqualsAndHashCode p4 = new PersonWithEqualsAndHashCode(2, "Mahmoud");

        System.out.println("After overriding equals() and hashCode() by id only:");
        System.out.println("p1.equals(p2) -> " + p1.equals(p2));
        System.out.println("p1.equals(p3) -> " + p1.equals(p3));
        System.out.println("p1.equals(p4) -> " + p1.equals(p4));
        System.out.println("p1 == p2 -> " + (p1 == p2));
        System.out.println("p1.hashCode() -> " + p1.hashCode());
        System.out.println("p2.hashCode() -> " + p2.hashCode());
        System.out.println("p3.hashCode() -> " + p3.hashCode());
        System.out.println("Objects with the same id are equal even if the name is different.");
    }
}
