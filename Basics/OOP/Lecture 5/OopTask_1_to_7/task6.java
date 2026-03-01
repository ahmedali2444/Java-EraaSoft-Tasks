package OopTask_1_to_7;


class SchoolStudent{

    private int id;
    private String name;

    public SchoolStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printDetails() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

class PrivateSchool extends SchoolStudent{
    private String privateSchoolName;

    public PrivateSchool(int id, String name, String privateSchoolName) {
        super(id, name);
        this.privateSchoolName = privateSchoolName;
    }

    public String getPrivateSchool() {
        return privateSchoolName;
    }

    public void setPrivateSchool(String privateSchool) {
        this.privateSchoolName = privateSchool;
    }
}

class PublicSchool extends SchoolStudent{
    private String publicSchoolName;


    public PublicSchool(int id, String name, String publicSchoolName) {
        super(id, name);
        this.publicSchoolName = publicSchoolName;
    }

    public String getPublicSchool() {
        return publicSchoolName;
    }

    public void setPublicSchool(String publicSchool) {
        this.publicSchoolName = publicSchool;
    }
}


public class task6 {
    public static void main(String[] args) {
        int privateId = 1;
        String privateName = "yousef";
        String privateSchoolName = "Amr";

        PrivateSchool privateStudent = new PrivateSchool(privateId, privateName, privateSchoolName);

        int publicId = 2;
        String publicName = "ahmed ali";
        String publicSchoolName = "omar khaled";

        PublicSchool publicStudent = new PublicSchool(publicId, publicName, publicSchoolName);

        System.out.println("Private School Student Details:");
        privateStudent.printDetails();

        System.out.println("Public School Student Details:");
        publicStudent.printDetails();
    }
}
