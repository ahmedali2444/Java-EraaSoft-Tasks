public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Player();
        firstPlayer.setId(10);
        firstPlayer.setName("Ali");
        firstPlayer.setNumber(25);

        System.out.println(firstPlayer.getId());
        System.out.println(firstPlayer.getName());
        System.out.println(firstPlayer.getNumber());

        Player secondPlayer = new Player();
        secondPlayer.setId(-5);
        secondPlayer.setName("Omar");
        secondPlayer.setNumber(150);

        System.out.println(secondPlayer.getId());
        System.out.println(secondPlayer.getName());
        System.out.println(secondPlayer.getNumber());

        Student firstStudent = new Student();
        firstStudent.setId(7);
        firstStudent.setName("Mona");
        firstStudent.setAge(20);

        System.out.println(firstStudent.getId());
        System.out.println(firstStudent.getName());
        System.out.println(firstStudent.getAge());

        Student secondStudent = new Student();
        secondStudent.setId(0);
        secondStudent.setName("Sara");
        secondStudent.setAge(5);

        System.out.println(secondStudent.getId());
        System.out.println(secondStudent.getName());
        System.out.println(secondStudent.getAge());
    }
}
