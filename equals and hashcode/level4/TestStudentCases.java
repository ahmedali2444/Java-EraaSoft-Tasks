package level4;

import java.util.HashSet;

public class TestStudentCases {
    public static void main(String[] args) {
        HashSet<StudentById> studentsById = new HashSet<>();
        studentsById.add(new StudentById(1, "ali@mail.com"));
        studentsById.add(new StudentById(1, "ali-new@mail.com"));
        studentsById.add(new StudentById(2, "mostafa@mail.com"));

        HashSet<StudentByEmail> studentsByEmail = new HashSet<>();
        studentsByEmail.add(new StudentByEmail(1, "ali@mail.com"));
        studentsByEmail.add(new StudentByEmail(2, "ali@mail.com"));
        studentsByEmail.add(new StudentByEmail(2, "mostafa@mail.com"));
        studentsByEmail.add(new StudentByEmail(3, "sara@mail.com"));

        System.out.println("Student equality case 1: by id");
        System.out.println("HashSet size -> " + studentsById.size());
        System.out.println("Students -> " + studentsById);
        System.out.println();
        System.out.println("Student equality case 2: by email");
        System.out.println("HashSet size -> " + studentsByEmail.size());
        System.out.println("Students -> " + studentsByEmail);
        System.out.println("The collection result changes because the equality rule changed.");
    }
}
