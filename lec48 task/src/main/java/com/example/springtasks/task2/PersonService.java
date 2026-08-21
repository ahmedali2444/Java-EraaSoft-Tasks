package com.example.springtasks.task2;

public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("PersonService saved: " + name);
    }
}
