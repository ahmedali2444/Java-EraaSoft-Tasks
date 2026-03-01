package OopTask_1_to_7;
class Base{
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id > 0)
            this.id = id;
        else
            System.out.println("id should be > 0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

class Player4 extends Base{
    private Long number;

    public Long getNumber() {
        return number;
    }
    public void setNumber(Long number) {
        if(number>=0 && number<=99)
            this.number = number;
        else
            System.out.println("number length should be <=2");
    }
}


class Student extends Base{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >7 && age < 30)
            this.age = age;
        else
            System.out.println("age should be >7 and <30");
    }
}

public class task4 {
    public static void main(String[] args) {

        System.out.println("===== Player4 =====");

        Player4 player = new Player4();

        player.setId(10L);
        player.setName("Ahmed");
        player.setNumber(7L);

        System.out.println("Player ID: " + player.getId());
        System.out.println("Player Name: " + player.getName());
        System.out.println("Player Number: " + player.getNumber());

        player.setId(-5L);
        player.setNumber(150L);


        System.out.println("\n===== Student =====");

        Student student = new Student();

        student.setId(20L);
        student.setName("Mohamed");
        student.setAge(22);

        System.out.println("Student ID: " + student.getId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student Age: " + student.getAge());

        student.setAge(5);
        student.setId(0L);
    }
}
