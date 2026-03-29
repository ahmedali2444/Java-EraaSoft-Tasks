public class Main {
    public static void main(String[] args) {
        Email studentEmail = new Email("ahmed@gmail.com", "12345");
        Student currentStudent = new Student(1, "Ahmed", studentEmail);
        currentStudent.showStudent();
    }
}
