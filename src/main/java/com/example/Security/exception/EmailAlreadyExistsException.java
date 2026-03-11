package com.example.Security.exception;

// Code written by Dimitri Liubomudrov

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
