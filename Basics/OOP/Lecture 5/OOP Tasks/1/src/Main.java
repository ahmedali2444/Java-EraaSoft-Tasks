import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int studentId = input.nextInt();
        String studentName = input.next();
        int studentAge = input.nextInt();

        Student currentStudent = new Student(studentId, studentName, studentAge);
        currentStudent.showStudent();
    }
}
