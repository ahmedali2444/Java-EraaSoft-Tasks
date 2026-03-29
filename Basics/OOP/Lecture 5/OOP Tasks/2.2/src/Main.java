public class Main {
    public static void main(String[] args) {
        Teacher firstTeacher = new Teacher();

        firstTeacher.setId(5L);
        firstTeacher.setName("ahmed");
        firstTeacher.setAge(30);
        firstTeacher.setPhoneNumber("+201113906600");
        firstTeacher.setSalary(5000);

        System.out.println("Valid teacher data:");
        System.out.println("Id: " + firstTeacher.getId());
        System.out.println("Name: " + firstTeacher.getName());
        System.out.println("Age: " + firstTeacher.getAge());
        System.out.println("Phone: " + firstTeacher.getPhoneNumber());
        System.out.println("Salary: " + firstTeacher.getSalary());

        System.out.println("----------------");

        Teacher secondTeacher = new Teacher();

        secondTeacher.setId(-1L);
        secondTeacher.setName("ab1");
        secondTeacher.setAge(20);
        secondTeacher.setPhoneNumber("01113906600");
        secondTeacher.setSalary(2000);

        System.out.println("Invalid teacher data:");
        System.out.println("Id: " + secondTeacher.getId());
        System.out.println("Name: " + secondTeacher.getName());
        System.out.println("Age: " + secondTeacher.getAge());
        System.out.println("Phone: " + secondTeacher.getPhoneNumber());
        System.out.println("Salary: " + secondTeacher.getSalary());
    }
}
