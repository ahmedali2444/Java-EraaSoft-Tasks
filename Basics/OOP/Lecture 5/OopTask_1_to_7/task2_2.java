package OopTask_1_to_7;


class Teacher {
    private Long id; // id > 0
    private String name; // size >=3 and all digits char(a-z)
    private float age; // age >= 25 and age <= 60
    private String phoneNumber; //  +20111390660 13 cher and start with +20
    private float salary;  // salary >= 3000

    public void setId(Long t_id){
        if (t_id > 0)
            id = t_id;
        else
            System.out.println("id should be > 0");
    }
    public  void setName(String t_name){
        if (t_name.length() >=3){
            for(char ch : t_name.toCharArray()){
                if (!(ch >= 'a' && ch <= 'z')){
                    System.out.println("name should be letters");
                    return;
                }
            }
            name = t_name;
        }
        else
            System.out.println("name length should be >= 3");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        if (age >=25 && age <=60)
            this.age = age;
        else
            System.out.println("age should be >= 25 and <= 60");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("+20") && phoneNumber.length() == 13)
            this.phoneNumber = phoneNumber;
        else
            System.out.println("Invalid phone number");
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        if(salary >= 3000)
            this.salary = salary;
        else
            System.out.println("salary should be >= 3000");
    }
}

public class task2_2 {
    public static void main(String[] args){

        Teacher t1 = new Teacher();

        t1.setId(5L);
        t1.setName("ahmed");
        t1.setAge(30);
        t1.setPhoneNumber("+201113906600");
        t1.setSalary(5000);

        System.out.println("Valid Data:");
        System.out.println("Id: " + t1.getId());
        System.out.println("Name: " + t1.getName());
        System.out.println("Age: " + t1.getAge());
        System.out.println("Phone: " + t1.getPhoneNumber());
        System.out.println("Salary: " + t1.getSalary());

        System.out.println("---------------");

        Teacher t2 = new Teacher();

        t2.setId(-1L);
        t2.setName("ab1");
        t2.setAge(20);
        t2.setPhoneNumber("01113906600");
        t2.setSalary(2000);

        System.out.println("Invalid Data:");
        System.out.println("Id: " + t2.getId());
        System.out.println("Name: " + t2.getName());
        System.out.println("Age: " + t2.getAge());
        System.out.println("Phone: " + t2.getPhoneNumber());
        System.out.println("Salary: " + t2.getSalary());
    }
}
