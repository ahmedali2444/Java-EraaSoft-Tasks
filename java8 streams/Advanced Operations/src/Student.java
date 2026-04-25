public class Student {
    // This class stores student data for the pass/fail task.
    String name;
    String department;
    double grade;

    Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        // Returning name and grade so the printed result is easy to read.
        return name + "(" + grade + ")";
    }
}
