import studentdata.Student;
import teacherdata.Teacher;

public class Main {
    public static void main(String[] args) {
        Student firstStudent = new Student(1, "Ahmed", 90);
        System.out.println(firstStudent.studentId);
        System.out.println(firstStudent.studentName);

        Teacher mainTeacher = new Teacher(1, "Mr Ali", true);
        System.out.println(mainTeacher.teacherId);
        System.out.println(mainTeacher.teacherName);
    }
}
