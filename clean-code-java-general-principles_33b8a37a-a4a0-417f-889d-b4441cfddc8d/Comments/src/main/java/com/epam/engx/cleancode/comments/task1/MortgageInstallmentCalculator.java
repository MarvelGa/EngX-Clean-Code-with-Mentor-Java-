package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {
    private static final int MONTHS_IN_YEAR = 12;

    public static double calculateMonthlyPayment(int principalAmount, int termsInYear, double rate) {

        validatePaymentInputs(principalAmount, termsInYear, rate);

        double rateOfInterestInDecimal = rate / 100.0;

        double termsInMonths = termsInYear * MONTHS_IN_YEAR;

        if (rateOfInterestInDecimal == 0) {
            return calculatePaymentWithoutInterest(termsInMonths, principalAmount);
        }

        return calculateMonthlyPaymentWithInterest(principalAmount, termsInMonths, rateOfInterestInDecimal);
    }

    private static double calculateMonthlyPaymentWithInterest(int principalAmount, double termsInMonths, double rateOfInterestInDecimal) {
        double monthlyRate = rateOfInterestInDecimal / MONTHS_IN_YEAR;
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termsInMonths));
    }

    private static double calculatePaymentWithoutInterest(double termsInMonths, double principalAmount) {
        return principalAmount / termsInMonths;
    }

    private static void validatePaymentInputs(int principalAmount, int term, double rate) {
        if (isValidPaymentInputs(principalAmount, term, rate)) {
            throw new InvalidInputException("Invalid payments inputs");
        }
    }

    private static boolean isValidPaymentInputs(int principalAmount, int term, double rate) {
        return principalAmount < 0 || term <= 0 || rate < 0;
    }
}
