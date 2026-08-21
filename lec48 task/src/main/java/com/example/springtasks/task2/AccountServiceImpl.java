package com.example.springtasks.task2;

public class AccountServiceImpl implements AccountService {

    private final UserService userService;

    public AccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getSavePerson(String name) {
        userService.save(name);
    }
}
