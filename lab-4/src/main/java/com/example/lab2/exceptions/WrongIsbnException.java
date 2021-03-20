package com.example.lab2.exceptions;

public class WrongIsbnException extends RuntimeException {

    public WrongIsbnException(String message) {
        super(message);
    }
}
