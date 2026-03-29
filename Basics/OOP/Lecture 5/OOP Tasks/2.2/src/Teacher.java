public class Teacher {
    private Long id;
    private String name;
    private float age;
    private String phoneNumber;
    private float salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
        } else id = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        boolean validName = true;
        for (char ch : name.toCharArray()) {
            if (!(ch >= 'a' && ch <= 'z')) {
                validName = false;
                break;
            }
        }
        if (!validName || name.length() < 3) {
            System.out.println("name must be at least 3 letters from a to z");
            return;
        }
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        if (!(age >= 25 && age <= 60)) {
            System.out.println("age must be between 25 and 60");
            return;
        }
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!(phoneNumber.length() == 13 && phoneNumber.startsWith("+20"))) {
            System.out.println("phone number must be 13 chars and start with +20");
            return;
        }
        this.phoneNumber = phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        if (!(salary >= 3000)) {
            System.out.println("salary must be at least 3000");
            return;
        }
        this.salary = salary;
    }
}
