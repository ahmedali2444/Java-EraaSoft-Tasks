package com.example.springtasks.task2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task2XmlApplication {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("task2-context.xml")) {
            AccountService accountService = context.getBean("accountService", AccountService.class);
            accountService.getSavePerson("Omar");
        }
    }
}
