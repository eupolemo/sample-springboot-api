package com.samplespring.exception;

public class CompraNotFoundException extends RuntimeException {
    public CompraNotFoundException(String message) {
        super(message);
    }
}
