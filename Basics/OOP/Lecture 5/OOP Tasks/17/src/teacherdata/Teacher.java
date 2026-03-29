package teacherdata;

public class Teacher {
    public int teacherId;
    public String teacherName;
    boolean teacherActive;

    public Teacher(int teacherId, String teacherName, boolean teacherActive) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherActive = teacherActive;
    }
}
