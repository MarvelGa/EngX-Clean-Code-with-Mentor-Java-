package com.epam.engx.cleancode.errorhandling.task1;

public class InvalidTotalAmountException extends UserReportException {
    protected String message = "ERROR: Wrong order amount.";

    @Override
    public String getMessage() {
        return message;
    }
}
