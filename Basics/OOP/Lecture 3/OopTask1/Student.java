package OopTask1;

public class Student {

    int Id ;
    String Name;
    int Age;

    Student(int id , String name , int age){
        Id = id;
        Age = age;
        Name = name;
    }

    void showInfo(){
        System.out.println("ID:" + Id);
        System.out.println("Name:" + Name);
        System.out.println("Age:" + Age);
    }
}
