import java.util.ArrayList;

public class Student {
    int studentId;
    String studentName;
    ArrayList<Course> courseList = new ArrayList<>();

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void showStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Course List:");
        for (Course courseItem : courseList) {
            System.out.println("  Course ID: " + courseItem.courseId + " and Name: " + courseItem.courseName);
        }
    }
}
