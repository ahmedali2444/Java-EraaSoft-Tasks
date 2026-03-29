public class SchoolImplB extends School {
    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void showStudents() {
        System.out.println("Showing students in School B");
        for (Student currentStudent : studentList) {
            System.out.println("Student ID: " + currentStudent.studentId + " and Name: " + currentStudent.studentName);
        }
    }
}
