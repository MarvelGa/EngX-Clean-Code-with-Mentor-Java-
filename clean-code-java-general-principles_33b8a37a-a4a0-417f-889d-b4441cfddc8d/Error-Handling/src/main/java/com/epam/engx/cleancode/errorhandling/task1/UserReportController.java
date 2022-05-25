package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        String totalMessage;
        try {
            totalMessage = getUserTotalMessage(userId);
        } catch (UserDaoNotExistsException ex) {
            return ex.getMessage();
        }

        model.addAttribute("userTotalMessage", totalMessage);
        return "userTotal";
    }

    private String getUserTotalMessage(String userId)  {
        Double amount = 0.0;
        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        } catch (UserReportException ex) {
            return ex.getMessage();
        }
        return "User Total: " + amount + "$";
    }

    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
