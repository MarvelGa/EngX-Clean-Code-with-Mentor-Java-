package com.epam.engx.cleancode.naming.task2;

public final class UserTestUtility {
    private UserTestUtility() {
        throw new AssertionError();
    }

    public static User getAdminUser(User user) {
        user.isAdmin = true;
        return user;
    }
}
