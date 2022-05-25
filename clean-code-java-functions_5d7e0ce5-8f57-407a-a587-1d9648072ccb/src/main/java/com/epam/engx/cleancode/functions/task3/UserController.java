package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        User user = userAuthenticator.login(userName, password);
        generateResponse(userName, user);
    }

    private void generateResponse(String userName, User user) {
        if (user == null){
            generateFailLoginResponse();
        }else{
            generateSuccessLoginResponse(userName);
        }
    }

    public void setUserAuthenticator(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }
}
