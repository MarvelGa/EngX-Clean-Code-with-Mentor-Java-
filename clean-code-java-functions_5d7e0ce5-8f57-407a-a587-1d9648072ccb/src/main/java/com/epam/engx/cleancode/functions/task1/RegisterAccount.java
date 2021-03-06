package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccount {

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        if (account.getName().length() <= 5) {
            throw new WrongAccountNameException();
        }
        String password = account.getPassword();
        if (password.length() <= 8) {
            throw new TooShortPasswordException();
        }
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }

        setDataToAccount(account);
    }

    private void setDataToAccount(Account account) {
        account.setCreatedDate(new Date());
        List<Address> addresses = new ArrayList<Address>();
        putAddressToAccount(account, addresses);
        accountManager.createNewAccount(account);
    }

    private void putAddressToAccount(Account account, List<Address> addresses) {
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

}
