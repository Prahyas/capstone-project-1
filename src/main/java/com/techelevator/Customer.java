package com.techelevator;

import com.techelevator.log.Log;

import java.math.BigDecimal;

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
        if(amount > 0) {
            BigDecimal amountToDeposit = new BigDecimal(amount);
            currentBalance = currentBalance.add(amountToDeposit);
            Log.log(String.format("FEED MONEY: $%s.00 $%s", amountToDeposit, currentBalance));
        }
        else {
            System.out.println("Please input whole dollar amounts.");
        }
    }

    // Subtract product price from the current balance and update the current balance
    // I need to find product name and slot location as well, but if I want to find both info here, I will have to call list of items here
    // and be better to use product code (slot location) as a parameter in the purchase method.
    // Or, I can implement log method in the Application class, that will make easy to find product name.
    public static void purchase(BigDecimal itemPrice) {
        currentBalance = currentBalance.subtract(itemPrice);
        //Log.log(String.format("%s %s: $%s $%s", itemPrice, currentBalance));
    }

    // After finishing transaction, run this method to make current balance to zero and record in the log file
    public static void zeroBalance() {
        currentBalance = currentBalance.subtract(currentBalance);
    }

    // Return the customer's money using quarters, dimes and nickels in the smallest amount of coins possible
    public static void returnChange() {
        Log.log(String.format("GIVE CHANGE: $%s, $0.00", currentBalance));
        int numberOfQuarters = (currentBalance.divide(QUARTER)).intValue();
        int numberOfDimes = ((currentBalance.subtract(QUARTER.multiply(new BigDecimal(numberOfQuarters)))).divide(DIME)).intValue();
        int numberOfNickels = ((currentBalance.subtract((QUARTER.multiply(new BigDecimal(numberOfQuarters))).add(DIME.multiply(new BigDecimal(numberOfDimes))))).divide(NICKEL)).intValue();
        System.out.println(
                String.format("Returned customer %d quarter(s), %d dime(s), %d nickel(s)",
                        numberOfQuarters, numberOfDimes, numberOfNickels));
    }

    // When dispensing an item, add the purchased product price to the current total cost
    public static void addToTotalCost(BigDecimal itemPrice) {
        totalCost = totalCost.add(itemPrice);
    }
}
