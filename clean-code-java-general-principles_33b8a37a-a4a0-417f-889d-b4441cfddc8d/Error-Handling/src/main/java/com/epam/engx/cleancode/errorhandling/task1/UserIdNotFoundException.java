package com.epam.engx.cleancode.errorhandling.task1;

public class UserIdNotFoundException extends UserReportException {
    protected String message = "WARNING: User ID doesn't exist.";

    @Override
    public String getMessage() {
        return message;
    }
}
