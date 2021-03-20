package com.example.lab2.exceptions;

public class EmptyBookFieldException extends RuntimeException{
    public EmptyBookFieldException(String message) {
        super(message);
    }
}
