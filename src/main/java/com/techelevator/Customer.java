package com.techelevator;

import java.util.Scanner;

public class Customer {

    private static double currentBalance = 0;
    private static final double quarter = 0.25;
    private static final double dime = 0.10;
    private static final double nickel = 0.01;

    // Return the current balance of the customer
    public static double getCurrentBalance() {
        return currentBalance;
    }

    // Feed money and update the current balance
    public static void deposit(int amountToDeposit) {
        currentBalance += amountToDeposit;
        Log.log(String.format("FEED MONEY: $%d.00 $%.2f", amountToDeposit, currentBalance));
    }

    // Subtract product price from the current balance and update the current balance
    public static void dispense(double productPrice) {
        currentBalance -= productPrice;
        // Need to get productName, productSlotLocation, productPrice here by reading product code input
        // or, in the Application class and I will implement log method in the application as well

        /* Also, need to print out the following messages
           All chip items print "Crunch Crunch, Yum!"
           All candy items print "Munch Munch, Yum!"
           All drink items print "Glug Glug, Yum!"
           All gum items print "Chew Chew, Yum!"
         */

        //Log.log(String.format("productName productLocation: $%.2f $%.2f", productPrice, currentBalance));
    }

    // After finishing transaction, run this method to make current balance to zero and record in the log file
    public static void zeroBalance() {
        Log.log(String.format("GIVE CHANGE: $%.2f $%.2f", currentBalance, 0.00));
        currentBalance = 0;
    }

    // Return the customer's money using quarters, dimes and nickels in the smallest amount of coins possible
    public static void returnChange() {

    }

}
