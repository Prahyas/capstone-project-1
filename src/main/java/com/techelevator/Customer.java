package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class Customer {

    // Customer's current balance, statring with $0
    private static BigDecimal currentBalance = new BigDecimal("0.00");
    // Total purchase cost by Customer, starting with $0
    private static BigDecimal totalCost = new BigDecimal("0.00");
    // Value of a quarter, dime and nickel coin
    private static final BigDecimal QUARTER = new BigDecimal("0.25");
    private static final BigDecimal DIME = new BigDecimal("0.10");
    private static final BigDecimal NICKEL = new BigDecimal("0.05");

    // Return the current balance of the customer
    public static BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    // Return the current total cost
    public static BigDecimal getTotalCost() {
        return totalCost;
    }

    // Feed money and update the current balance
    public static void deposit(int amount) {
        BigDecimal amountToDeposit = new BigDecimal(amount);
        currentBalance = currentBalance.add(amountToDeposit);

    }

    // Subtract product price from the current balance and update the current balance
    public static void purchase(BigDecimal itemPrice) {
        currentBalance = currentBalance.subtract(itemPrice);
        //Log.log(String.format("productName productLocation: $%.2f $%.2f", productPrice, currentBalance));
    }

    // After finishing transaction, run this method to make current balance to zero and record in the log file
    public static void zeroBalance() {
        currentBalance = currentBalance.subtract(currentBalance);
    }

    // Return the customer's money using quarters, dimes and nickels in the smallest amount of coins possible
    public static void returnChange() {
        int numberOfQuarters = (currentBalance.divide(QUARTER)).intValue();
        int numberOfDimes = ((currentBalance.subtract(QUARTER.multiply(new BigDecimal(numberOfQuarters)))).divide(DIME)).intValue();
        int numberOfNickels = ((currentBalance.subtract((QUARTER.multiply(new BigDecimal(numberOfQuarters))).add(DIME.multiply(new BigDecimal(numberOfDimes))))).divide(NICKEL)).intValue();
        int numberOfPenny = (currentBalance.subtract((QUARTER.multiply(new BigDecimal(numberOfQuarters))).add(DIME.multiply(new BigDecimal(numberOfDimes))).add(NICKEL.multiply(new BigDecimal(numberOfNickels))))).intValue();
        System.out.println(
                String.format("Returned customer %d quarter(s), %d dime(s), %d nickel(s) and %d penny(ies)",
                        numberOfQuarters, numberOfDimes, numberOfNickels, numberOfPenny));
    }

    // When dispensing an item, add the purchased product price to the current total cost
    public static void addToTotalCost(BigDecimal itemPrice) {
        totalCost = totalCost.add(itemPrice);
    }
}
