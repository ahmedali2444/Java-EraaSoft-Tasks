package com.example.springtasks.task3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task3JavaApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(Task3JavaConfig.class)) {
            PersonService firstPerson = context.getBean(PersonService.class);
            PersonService secondPerson = context.getBean(PersonService.class);

            System.out.println("Different prototype objects: " + (firstPerson != secondPerson));
            firstPerson.save("Sara");
            secondPerson.save("Youssef");

            firstPerson.destroy();
            secondPerson.destroy();
        }
    }
}
