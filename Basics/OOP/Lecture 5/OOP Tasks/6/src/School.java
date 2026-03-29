public class School {
    private int studentId;
    private String studentName;

    public School(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public int getId() {
        return studentId;
    }

    public void setId(int id) {
        this.studentId = id;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public String display() {
        return "ID: " + studentId + ", Name: " + studentName;
    }
}
