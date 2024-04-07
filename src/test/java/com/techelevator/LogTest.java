package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LogTest {
    private double currentBalance = 0.00;
    private int feedMoneyValue = 5;
    private double productPrice = 4;

//    @Before
//    public void initializeBalance() {
//        currentBalance = 0.00;
//        // I am not using the value below, but this will be my input value for the test
//        feedMoneyValue = 5;
//        productPrice = 4.00;
//    }

    @Test
    public void checkLogTextFileDateFeedMoney() {
        Customer.deposit(feedMoneyValue);
        System.out.println(Customer.getCurrentBalance());
        Assert.assertEquals(currentBalance+feedMoneyValue, Customer.getCurrentBalance(), 0);
    }

    // Need to work on this
    @Test
    public void checkLogTextFileDateDispense() {
        Customer.dispense(productPrice);
        System.out.println(Customer.getCurrentBalance());
    }

    @Test
    public void zerorizeBalance() {
        Customer.zeroBalance();
    }
}
