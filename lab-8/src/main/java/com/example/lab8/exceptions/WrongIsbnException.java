package com.example.lab8.exceptions;

public class WrongIsbnException extends RuntimeException {

    public WrongIsbnException(String message) {
        super(message);
    }
}
