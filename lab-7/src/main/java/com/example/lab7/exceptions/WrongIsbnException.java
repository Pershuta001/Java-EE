package com.example.lab7.exceptions;

public class WrongIsbnException extends RuntimeException {

    public WrongIsbnException(String message) {
        super(message);
    }
}
