public class Person {
    int personId;
    String personName;
    Bill personBill;

    public Person(int personId, String personName, Bill personBill) {
        this.personId = personId;
        this.personName = personName;
        this.personBill = personBill;
    }

    public void showPerson() {
        System.out.println("Person ID: " + personId);
        System.out.println("Person Name: " + personName);
        personBill.showBill();
    }
}
