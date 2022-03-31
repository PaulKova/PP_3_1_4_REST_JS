package ru.kata.spring.boot_security.demo.ExseptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserIncorrectId> handleExseption(NoUserByIdException exseption){
        UserIncorrectId user = new UserIncorrectId();
        user.setInfo(exseption.getMessage());
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectId> handleExseption(Exception exseption){
        UserIncorrectId user = new UserIncorrectId();
        user.setInfo(exseption.getMessage());
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }
}
