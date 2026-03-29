public class SchoolImplA extends School {
    public void addStudent(Student student) {
        System.out.println("Adding student in School A");
        studentList.add(student);
    }

    public void showStudents() {
        for (Student currentStudent : studentList) {
            System.out.println("Student ID: " + currentStudent.studentId + " and Name: " + currentStudent.studentName);
        }
    }
}
