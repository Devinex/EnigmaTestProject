package com.example.demo.Exceptions;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
