package com.example.springtasks.task3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task3XmlApplication {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("task3-context.xml")) {
            PersonService firstPerson = context.getBean("personService", PersonService.class);
            PersonService secondPerson = context.getBean("personService", PersonService.class);

            System.out.println("Different prototype objects: " + (firstPerson != secondPerson));
            firstPerson.save("Sara");
            secondPerson.save("Youssef");

            // Spring does not destroy prototype beans automatically.
            firstPerson.destroy();
            secondPerson.destroy();
        }
    }
}
