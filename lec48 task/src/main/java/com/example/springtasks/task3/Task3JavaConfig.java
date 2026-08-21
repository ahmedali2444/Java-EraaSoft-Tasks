package com.example.springtasks.task3;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Task3JavaConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PersonService personService() {
        return new PersonService();
    }
}
