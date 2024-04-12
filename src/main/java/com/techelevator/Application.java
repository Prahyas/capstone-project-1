package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.*;

public class Application {

    private static final Scanner inputScanner = new Scanner(System.in);
    private static final Items items = new Items();

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
                    // Display items
                    System.out.println("-----------------------------------");
                    System.out.println("Code - Name - Price - Type - Stock");
                    System.out.println("-----------------------------------");
                    for (Items itemDetail : items.getItemsDetails()) {
                        System.out.println(itemDetail);
                    }
                    // Input
                    System.out.println("Input your snack code:");
                    String slotInput = inputScanner.next();
                    // Begin Checking
                    if(hasSlot(items, slotInput)){
                        Items chosenItem = items.getItemBySlotLocation(slotInput);

                        if(hasMoney(chosenItem, Customer.getCurrentBalance())){

                            if(hasStock(chosenItem)){ // Only does operations after passing the 3 tests
                                System.out.println("Dispensing " + chosenItem.getItemName() + ", " + snackMessage(chosenItem));
                                System.out.println("Charging $" + chosenItem.getItemPrice());

                                Customer.purchase(chosenItem.getItemPrice());
                                // UPDATE STOCK HERE
                                Log.log(chosenItem.getItemName() + " " + slotInput.toUpperCase() + " $" + chosenItem.getItemPrice() + " $" + Customer.getCurrentBalance());

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
                    return;
                case 3:
                    Customer.returnChange(); // Add to Log
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
        /*
        Track all purchases and how many purchased somehow.
        Also all sales so far. Print to console, then exit.

        Potato Crisps|0
        Stackers|1
        Grain Waves|0
        Cloud Popcorn|0
        Moonpie|3
        Cowtales|0
        Wonka Bar|0
        Crunchie|0
        Cola|2
        Dr. Salt|0
        Mountain Melter|0
        Heavy|0
        U-Chews|0
        Little League Chew|1
        Chiclets|1
        Triplemint|0

        **TOTAL SALES** $11.05
         */
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

