public class Student {
    int studentId;
    String studentName;
    int studentAge;

    public Student(int studentId, String studentName, int studentAge) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    public void showStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Student Age: " + studentAge);
    }
}

