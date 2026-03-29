public class Student extends Info {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (!(age > 7 && age < 30)) {
            System.out.println("age must be between 8 and 29");
            return;
        }
        this.age = age;
    }
}
