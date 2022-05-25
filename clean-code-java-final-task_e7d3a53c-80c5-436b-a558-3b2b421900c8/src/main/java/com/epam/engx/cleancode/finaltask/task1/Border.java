package com.epam.engx.cleancode.finaltask.task1;

enum Border {
    TOP("╔", "╦", "╗"),
    MIDDLE("╠", "╬", "╣"),
    BOTTOM("╚", "╩", "╝");

    Border(String left, String middle, String right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public final String left;
    public final String middle;
    public final String right;
}
