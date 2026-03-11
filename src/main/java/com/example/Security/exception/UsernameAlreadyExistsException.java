package com.example.Security.exception;

// Code written by Dimitri Liubomudrov

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}