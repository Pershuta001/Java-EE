package com.example.lab7.exceptions;

public class EmptyBookFieldException extends RuntimeException{
    public EmptyBookFieldException(String message) {
        super(message);
    }
}
