public class Main {
    public static void main(String[] args) {
        SchoolImplA firstSchool = new SchoolImplA();
        firstSchool.addStudent(new Student(1, "Ahmed"));
        firstSchool.addStudent(new Student(2, "Mohamed"));
        firstSchool.showStudents();

        SchoolImplB secondSchool = new SchoolImplB();
        secondSchool.addStudent(new Student(3, "Ali"));
        secondSchool.addStudent(new Student(4, "Omar"));
        secondSchool.showStudents();
    }
}
