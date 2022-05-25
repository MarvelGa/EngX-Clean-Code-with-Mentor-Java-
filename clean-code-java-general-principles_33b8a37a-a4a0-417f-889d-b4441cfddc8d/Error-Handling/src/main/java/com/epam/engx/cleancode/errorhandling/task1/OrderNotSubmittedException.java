package com.epam.engx.cleancode.errorhandling.task1;

public class OrderNotSubmittedException extends UserReportException {
    protected String message = "WARNING: User have no submitted orders.";

    @Override
    public String getMessage() {
        return message;
    }
}
