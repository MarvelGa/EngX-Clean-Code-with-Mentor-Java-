package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String PLUS_SIGN = "+";
    private static final String PIPE_SIGN = "|";
    private static final String MINUS_SIGN = "-";
    private static final String UNDERSCORE_SIGN = " _ ";
    private static final String NEW_LINE_SIGN = "\n";

    public String formatKeyValue(String key, String value) {
        String content = key + UNDERSCORE_SIGN + value;
        String minuses = repeat(MINUS_SIGN, content.length());
        return PLUS_SIGN +  minuses + PLUS_SIGN + NEW_LINE_SIGN
                + PIPE_SIGN + content + PIPE_SIGN + NEW_LINE_SIGN
                + PLUS_SIGN + minuses + PLUS_SIGN + NEW_LINE_SIGN;
    }

    public String repeat(String symbol, int occasions) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < occasions; i++){
            result.append(symbol);
        }
        return result.toString();
    }
}
