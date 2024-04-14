package com.techelevator;

import com.techelevator.log.Log;

import java.math.BigDecimal;
import java.util.*;

public class Application {

    private static final Scanner inputScanner = new Scanner(System.in);
    private static Items items = new Items();
    private static List<Items> itemsList = new ArrayList<>();
    private static ShoppingCart shoppingCart;


    public static void main(String[] args) {
        itemsList = items.getItemDetails();
        shoppingCart = new ShoppingCart(itemsList);
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
                        for (Items itemDetail : itemsList) {
                            System.out.println(itemDetail);
                        }
                        mainMenu();
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
                    return;
                case 2:
                    // Display items
                    System.out.println("-----------------------------------");
                    System.out.println("Code - Name - Price - Type - Stock");
                    System.out.println("-----------------------------------");
                    for (Items itemDetail : itemsList) {
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
                                Customer.addToTotalCost(chosenItem.getItemPrice());
                                shoppingCart.addToCart(chosenItem);
                                chosenItem.setCurrentItemStock(chosenItem.getCurrentItemStock() - 1); // Not updating stock
                                Log.log(String.format("%s %s $%s $%s", chosenItem.getItemName(), slotInput, chosenItem.getItemPrice(), Customer.getCurrentBalance()));
                                System.out.println(chosenItem.getItemName() + "'s stock is now " + chosenItem.getCurrentItemStock());
                                System.out.println("-----------------------------------");
                                purchaseMenu();
                                return;
                            } else {
                                System.out.println("Item not in stock... Returning to Purchase Menu.");
                                System.out.println("-----------------------------------");
                                purchaseMenu();
                                return;
                            }
                        } else {
                            System.out.println("Not enough money... Returning to Purchase Menu.");
                            System.out.println("-----------------------------------");
                            purchaseMenu();
                            return;
                        }
                    } else {
                        System.out.println("Item slot doesn't exist... Returning to Purchase Menu.");
                        System.out.println("-----------------------------------");
                        purchaseMenu();
                        return;
                    }
                case 3:
                    Customer.returnChange(); // Add to Log
                    Customer.zeroBalance();
                    purchaseMenu();
                    return;
                case 4:
                    mainMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static void hiddenMenu() {
        String salesReport = shoppingCart.printSalesReport();
        Log.salesReportLog(String.format(salesReport));
        mainMenu();
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

