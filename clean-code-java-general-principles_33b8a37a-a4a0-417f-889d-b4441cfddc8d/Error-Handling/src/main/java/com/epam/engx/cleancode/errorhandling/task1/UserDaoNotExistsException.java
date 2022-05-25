package com.epam.engx.cleancode.errorhandling.task1;

public class UserDaoNotExistsException extends RuntimeException {
    protected String message ="technicalError";

    @Override
    public String getMessage() {
        return message;
    }
}
