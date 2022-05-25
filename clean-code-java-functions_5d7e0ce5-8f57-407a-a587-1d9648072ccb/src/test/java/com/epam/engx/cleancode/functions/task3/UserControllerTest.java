package com.epam.engx.cleancode.functions.task3;

import org.junit.Assert;
import org.junit.Test;

public class UserControllerTest {

    private final UserControllerMock userController = new UserControllerMock();

    @Test
    public void shouldNotAuthenticateUser() {
        userController.setUserAuthenticator(new FalseUserAuthenticator());
        try {
            userController.authenticateUser("admin", "123");
            userController.assertGenerateFailLoginResponseCalled();
        } catch (FailedPasswordException ex) {
            Assert.assertEquals("Password is not correct", ex.getMessage());
        }
    }


    @Test
    public void shouldAuthenticateUser() {
        TrueUserAuthenticatorMock trueUserAuthenticatorMock = new TrueUserAuthenticatorMock();
        userController.setUserAuthenticator(trueUserAuthenticatorMock);
        userController.authenticateUser("admin", "123");
        userController.assertGenerateSuccessLoginResponseCalled();
        trueUserAuthenticatorMock.assertSetCurrentUserToSession();
    }

}
