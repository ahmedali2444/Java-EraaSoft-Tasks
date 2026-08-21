package com.example.springtasks.task3;

public class PersonService implements UserService {

    public void init() {
        System.out.println("PersonService init method");
    }

    @Override
    public void save(String name) {
        System.out.println("PersonService saved: " + name);
    }

    public void destroy() {
        System.out.println("PersonService destroy method");
    }
}
