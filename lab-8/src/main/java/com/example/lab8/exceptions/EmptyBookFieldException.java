package com.example.lab8.exceptions;

public class EmptyBookFieldException extends RuntimeException{
    public EmptyBookFieldException(String message) {
        super(message);
    }
}
