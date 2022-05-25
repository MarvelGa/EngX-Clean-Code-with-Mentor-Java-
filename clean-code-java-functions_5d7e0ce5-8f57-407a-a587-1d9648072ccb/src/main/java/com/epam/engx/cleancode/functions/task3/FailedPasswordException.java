package com.epam.engx.cleancode.functions.task3;

public class FailedPasswordException extends RuntimeException {
    public FailedPasswordException() {
        super();
    }

    public FailedPasswordException(String message) {
        super(message);
    }
}
