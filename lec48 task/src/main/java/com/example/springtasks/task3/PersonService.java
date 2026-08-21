package com.example.springtasks.task3;

public class PersonService implements UserService {

    private boolean initialized;
    private boolean destroyed;

    public void init() {
        initialized = true;
        System.out.println("PersonService init method");
    }

    @Override
    public void save(String name) {
        System.out.println("PersonService saved: " + name);
    }

    public void destroy() {
        destroyed = true;
        System.out.println("PersonService destroy method");
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
