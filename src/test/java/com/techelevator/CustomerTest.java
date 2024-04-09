package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CustomerTest {

    private BigDecimal currentBalance = new BigDecimal("0.00");
    private BigDecimal feedMoneyValue = new BigDecimal("5.00");
    private BigDecimal itemPrice = new BigDecimal("4.15");

    @Before
    public void makeCurrentBalanceToZero() {
        Customer.zeroBalance();
    }

    @Test
    public void checkDeposit() {
        Customer.deposit(feedMoneyValue.intValue());
        System.out.println("Expected: " + currentBalance.add(feedMoneyValue));
        System.out.println("Actual: " + Customer.getCurrentBalance());
        Assert.assertEquals(currentBalance.add(feedMoneyValue), Customer.getCurrentBalance());
        Customer.zeroBalance();
    }

    @Test
    public void checkPurchase() {
        Customer.purchase(itemPrice);
        System.out.println("Expected: " + currentBalance.subtract(itemPrice));
        System.out.println("Actual: " + Customer.getCurrentBalance());
        Assert.assertEquals(currentBalance.subtract(itemPrice), Customer.getCurrentBalance());
    }

    @Test
    public void checkReturnChange() {
        Customer.deposit(feedMoneyValue.intValue());
        Customer.purchase(itemPrice); // current balance = 5.00 - 4.15 = 0.85 -> 3 quarters and 1 dime
        System.out.println("Expected: Returned customer 3 quarter(s), 1 dime(s), 0 nickel(s) and 0 penny(ies)");
        System.out.print("Actual: ");
        Customer.returnChange();
    }

    @Test
    public void zerorizeBalance() {
        Customer.deposit(feedMoneyValue.intValue());
        Customer.zeroBalance();
        Assert.assertEquals(new BigDecimal("0.00"), Customer.getCurrentBalance());
    }
}

