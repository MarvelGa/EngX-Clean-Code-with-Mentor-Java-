package com.epam.engx.cleancode.naming.task3;

public final class HarshadTestUtility {
    private HarshadTestUtility() {
        throw new AssertionError();
    }

    public static String getHarshadNumbers() {
        return new HarshadNumberPrinter().printNumber();
    }
}
