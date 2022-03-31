package ru.kata.spring.boot_security.demo.ExseptionHandling;

public class NoUserByIdException extends RuntimeException{

    public NoUserByIdException(String message) {
        super(message);
    }
}
