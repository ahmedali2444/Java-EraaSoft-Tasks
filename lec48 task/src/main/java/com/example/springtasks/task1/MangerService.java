package com.example.springtasks.task1;

// The name follows the spelling used in the task description.
public class MangerService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("MangerService saved: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("MangerService updated: " + name);
    }
}
