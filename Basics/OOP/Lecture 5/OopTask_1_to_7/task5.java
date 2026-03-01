package OopTask_1_to_7;



class BaseEntity{
    private int id;
    private String name;

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
}
class ShareData extends BaseEntity{
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

class Person extends BaseEntity{

}

class Player5 extends ShareData{

}

class Student5 extends  ShareData{

}

public class task5 {
    public static void main(String[] args){
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(1);
        baseEntity.setName("Base Entity");

        System.out.println("BaseEntity ID: " + baseEntity.getId());
        System.out.println("BaseEntity Name: " + baseEntity.getName());

        ShareData shareData = new ShareData();
        shareData.setId(2);
        shareData.setName("Share Data");
        shareData.setPhone("1234567890");

        System.out.println("ShareData ID: " + shareData.getId());
        System.out.println("ShareData Name: " + shareData.getName());
        System.out.println("ShareData Phone: " + shareData.getPhone());

        Person person = new Person();
        person.setId(3);
        person.setName("John Doe");

        System.out.println("Person ID: " + person.getId());
        System.out.println("Person Name: " + person.getName());

        Player5 player5 = new Player5();
        player5.setId(4);
        player5.setName("Player One");
        player5.setPhone("9876543210");

        System.out.println("Player5 ID: " + player5.getId());
        System.out.println("Player5 Name: " + player5.getName());
        System.out.println("Player5 Phone: " + player5.getPhone());

        Student5 student5 = new Student5();
        student5.setId(5);
        student5.setName("Student A");
        student5.setPhone("5551234");

        System.out.println("Student5 ID: " + student5.getId());
        System.out.println("Student5 Name: " + student5.getName());
        System.out.println("Student5 Phone: " + student5.getPhone());
    }
}
