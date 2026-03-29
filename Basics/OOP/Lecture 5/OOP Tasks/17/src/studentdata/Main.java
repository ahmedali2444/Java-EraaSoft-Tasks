package studentdata;

public class Main {
    public static void main(String[] args) {
        Student currentStudent = new Student(1, "Ahmed", 90);
        System.out.println(currentStudent.studentId);
        System.out.println(currentStudent.studentName);
        System.out.println(currentStudent.gradeValue);
    }
}
