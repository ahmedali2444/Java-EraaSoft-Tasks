package com.example.springtasks.task2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task2JavaApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(Task2JavaConfig.class)) {
            AccountService accountService = context.getBean(AccountService.class);
            accountService.getSavePerson("Omar");
        }
    }
}
