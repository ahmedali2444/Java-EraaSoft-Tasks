public class Main {
    public static void main(String[] args) {
        PrivateSchoolStudent privateSchoolStudent = new PrivateSchoolStudent(101, "Yaseen Abdulghany");
        PublicSchoolStudent publicSchoolStudent = new PublicSchoolStudent(115, "Ahmed Omar");

        System.out.println("Private school student: " + privateSchoolStudent.display());
        System.out.println("Public school student: " + publicSchoolStudent.display());
    }
}
