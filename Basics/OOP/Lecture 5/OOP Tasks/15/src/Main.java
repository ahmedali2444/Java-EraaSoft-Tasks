public class Main {
    public static void main(String[] args) {
        Student currentStudent = new Student(1, "Ahmed");
        currentStudent.addCourse(new Course(1, "Math"));
        currentStudent.addCourse(new Course(2, "Science"));
        currentStudent.addCourse(new Course(3, "English"));
        currentStudent.showStudent();
    }
}
