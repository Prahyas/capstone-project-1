package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.*;

public class Application {

    private static final Scanner inputScanner = new Scanner(System.in);
    private static Items items = new Items();

    public static void main(String[] args) {
        mainMenu();
        inputScanner.close();
    }

    private static void mainMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");

            try {
                int input = inputScanner.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("Run display code");

                        // there is a method getItemsDetails() in Items class
                        // that returns the array of items and
                        // can be used to display the items, by using a for loop

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
        System.out.println("Current Balance: $" + Customer.getCurrentBalance());
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("(4) Main Menu");

        try {
            int input = inputScanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Insert cash (whole dollar amounts):");
                    int amountToDeposit = inputScanner.nextInt();
                    Customer.deposit(amountToDeposit);
                    purchaseMenu();
                case 2:
                    // Display items, including itemQuantity
                    System.out.println("-----------------------------------");
                    System.out.println("Code - Name - Price - Type - Stock");
                    System.out.println("-----------------------------------");
                    for (Items itemDetail : items.getItemsDetails()) {
                        System.out.println(itemDetail);
                    }
                    // Allow user to choose snack
                    System.out.println("Enter code to dispense item:");
                    String slotInput = inputScanner.next();
                    // Begin Checking
                    if(hasSlot(items, slotInput)){
                        Items chosenItem = items.getItemBySlotLocation(slotInput);
                        if(hasMoney(chosenItem, Customer.getCurrentBalance())){
                            if(hasStock(chosenItem)){ // Only does operations after passing the 3 tests
                                System.out.println("Dispensing " + chosenItem.getItemName() + ", " + snackMessage(chosenItem));
                                System.out.println("Charging $" + chosenItem.getItemPrice());

                                Customer.purchase(chosenItem.getItemPrice());
                                //items.setCurrentItemStock(items.getItemBySlotLocation(slotInput).getCurrentItemStock() - 1); // Not updating stock

                                System.out.println(chosenItem.getItemName() + "'s stock is now " + chosenItem.getCurrentItemStock());
                                System.out.println("-----------------------------------");
                                purchaseMenu();
                            } else {
                                System.out.println("Item not in stock... Returning to Purchase Menu.");
                                System.out.println("-----------------------------------");
                                purchaseMenu();
                            }
                        } else {
                            System.out.println("Not enough money... Returning to Purchase Menu.");
                            System.out.println("-----------------------------------");
                            purchaseMenu();
                        }
                    } else {
                        System.out.println("Item slot doesn't exist... Returning to Purchase Menu.");
                        System.out.println("-----------------------------------");
                        purchaseMenu();
                    }

                    // Still need to track sales report/log/hidden menu

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

    private static boolean hasSlot(Items items, String slotInput){
        return items.getItemBySlotLocation(slotInput) != null;
    }

    private static boolean hasMoney(Items items, BigDecimal balance){
        return balance.compareTo(items.getItemPrice()) >= 0;
    }

    private static boolean hasStock(Items items){
        return items.getCurrentItemStock() > 0;
    }

    private static String snackMessage(Items items){
        String snackMessage = null;
        switch (items.getItemCategory()) {
            case "Chip":
                snackMessage = "'Crunch Crunch, Yum!'";
                break;
            case "Candy":
                snackMessage = "'Munch Munch, Yum!'";
                break;
            case "Drink":
                snackMessage = "'Glug Glug, Yum!'";
                break;
            case "Gum":
                snackMessage = "'Chew Chew, Yum!'";
                break;
        }
        return snackMessage;
    }
}

