package com.epam.engx.cleancode.naming.task3;

public class HarshadNumberPrinter {
    private final static Long COUNT_OF_NUMBERS = 200l;
    private final static String NEW_LINE = "\n";
    private final static Integer DECIMAL_BASE = 10;

    public String printNumber() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= COUNT_OF_NUMBERS; i++) {
            if (i % sumDigitsOfNumber(i) == 0) {
                builder.append(i).append(NEW_LINE);
            }
        }
        return builder.toString();
    }

    private int sumDigitsOfNumber(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % DECIMAL_BASE;
            number /= DECIMAL_BASE;
        }
        return sum;
    }

}
