package teacherdata;

public class Main {
    public static void main(String[] args) {
        Teacher currentTeacher = new Teacher(1, "Mr. Ali", true);
        System.out.println(currentTeacher.teacherId);
        System.out.println(currentTeacher.teacherName);
        System.out.println(currentTeacher.teacherActive);
    }
}
