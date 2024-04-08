package com.techelevator;

import java.util.*;

public class Application {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");

            try {
                int input = SCANNER.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("Run display code");
                        //display

                        // there is a method getItemsDetails() in Items class
                        // that returns the array of items and
                        // can be used to display the items, by using a for loop
                        ;
                        return;
                    case 2:
                        purchaseMenu();
                        return;
                    case 3:
                        System.out.println("Thank you! Come again!");
                        keepRunning = false;
                        break;
                    case 4:
                        hiddenMenu();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: " + e.getMessage());
                break;
            }
        }
    }

    private static void purchaseMenu() {
        System.out.println("Current Money Provided: " + Customer.getCurrentBalance()); //add user's money
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("(4) Main Menu");

        try {
            int input = SCANNER.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Run feed money code");
                    //feedMoney
                    int amountToDeposit = SCANNER.nextInt();
                    Customer.deposit(amountToDeposit);
                    return;
                case 2:
                    System.out.println("Run select product code");
                    //selectProduct
                    return;
                case 3:
                    System.out.println("Run finish transaction code");
                    //finishTransaction
                    Customer.returnChange();
                    Customer.zeroBalance();
                    return;
                case 4:
                    mainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static void hiddenMenu() {
        System.out.println("Write sales report with date/time appended to end of file's name");
    }
}
