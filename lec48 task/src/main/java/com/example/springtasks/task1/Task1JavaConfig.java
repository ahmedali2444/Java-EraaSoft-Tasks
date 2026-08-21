package com.example.springtasks.task1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Task1JavaConfig {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }

    @Bean
    public MangerService mangerService() {
        return new MangerService();
    }
}
