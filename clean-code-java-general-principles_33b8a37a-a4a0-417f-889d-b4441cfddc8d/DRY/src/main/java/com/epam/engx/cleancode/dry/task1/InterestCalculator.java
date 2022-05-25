package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int SENIOR_ELIGIBLE_AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_INTEREST_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        return isAccountStartedAfterBonusAge(accountDetails) ? calculateInterestAfterBonusAge(accountDetails) : BigDecimal.ZERO;
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        int ageDuringAccountOpening = getDurationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate());
        return ageDuringAccountOpening > BONUS_AGE;
    }

    private int getDurationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = getChangedCalendar(from);
        Calendar endCalendar = getChangedCalendar(to);
        return calculateDifference(startCalendar, endCalendar);
    }

    private Calendar getChangedCalendar(Date date) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        return startCalendar;
    }

    private int calculateDifference(Calendar startCalendar, Calendar endCalendar) {
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (isLeapYear(startCalendar, endCalendar)) {
            return diffYear - 1;
        }
        return diffYear;
    }

    private boolean isLeapYear(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private BigDecimal calculateInterestAfterBonusAge(AccountDetails accountDetails) {
        double interest = 0;
        if (SENIOR_ELIGIBLE_AGE <= getDurationBetweenDatesInYears(accountDetails.getBirth(), new Date())) {
            interest = calculatePayment(accountDetails) * SENIOR_INTEREST_PERCENT / 100;
        } else {
            interest = calculatePayment(accountDetails) * INTEREST_PERCENT / 100;
        }
        return BigDecimal.valueOf(interest);
    }

    private double calculatePayment(AccountDetails accountDetails) {
        return accountDetails.getBalance().doubleValue()
                * getDurationSinceStartDateInYears(accountDetails.getStartDate());
    }

    private int getDurationSinceStartDateInYears(Date startDate) {
        return getDurationBetweenDatesInYears(startDate, new Date());
    }
}
