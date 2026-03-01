package OopTask_1_to_7;


import OopTask1.Student;

class Student1 {

    int Id ;
    String Name;
    int Age;

    Student1(int id , String name , int age){
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

public class task1 {
    public static void main(String[] args){
        Student1 s1 = new Student1(1 , "ahmed ali" , 22);
        s1.showInfo();
    }
}
