package com.example.springtasks.task1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task1JavaApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(Task1JavaConfig.class)) {
            UserService personService = context.getBean("personService", UserService.class);
            UserService mangerService = context.getBean("mangerService", UserService.class);

            personService.save("Ahmed");
            personService.update("Ahmed Ali");

            mangerService.save("Mona");
            mangerService.update("Mona Hassan");
        }
    }
}
