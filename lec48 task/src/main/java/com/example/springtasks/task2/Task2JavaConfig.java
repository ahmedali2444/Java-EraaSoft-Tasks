package com.example.springtasks.task2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Task2JavaConfig {

    @Bean
    public UserService personService() {
        return new PersonService();
    }

    @Bean
    public AccountService accountService(UserService personService) {
        return new AccountServiceImpl(personService);
    }
}
