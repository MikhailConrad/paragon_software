package com.example.paragon_software_task.exception;

public class UserNotFoundException extends RuntimeException {

    private final static String EXCEPTION_MESSAGE = "Пользователя с таким ID не существует.";

    public UserNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
