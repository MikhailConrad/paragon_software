package com.example.paragon_software_task.exception;

import com.example.paragon_software_task.entity.UserIncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserIncorrectData> userNotFound(UserNotFoundException exception) {

        UserIncorrectData userIncorrectData = new UserIncorrectData(exception.getMessage());
        return new ResponseEntity<>(userIncorrectData, HttpStatus.NOT_FOUND);
    }
}
