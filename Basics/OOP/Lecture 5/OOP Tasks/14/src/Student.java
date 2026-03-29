public class Student {
    int studentId;
    String studentName;
    Email emailInfo;

    public Student(int studentId, String studentName, Email emailInfo) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.emailInfo = emailInfo;
    }

    public void showStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Email: " + emailInfo.emailAddress);
        System.out.println("Password: " + emailInfo.emailPassword);
    }
}
