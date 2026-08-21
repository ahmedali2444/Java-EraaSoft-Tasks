package com.example.springtasks.task1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task1XmlApplication {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("task1-context.xml")) {
            UserService personService = context.getBean("personService", UserService.class);
            UserService mangerService = context.getBean("mangerService", UserService.class);

            personService.save("Ahmed");
            personService.update("Ahmed Ali");

            mangerService.save("Mona");
            mangerService.update("Mona Hassan");
        }
    }
}
