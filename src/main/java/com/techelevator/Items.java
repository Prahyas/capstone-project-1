package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class Items {
    private static final int DEFAULT_STOCK_QUANTITY = 5;
    private String itemSlot;
    private String itemName;
    private String itemCategory;
    private BigDecimal itemPrice;
    private int itemQuantity;
    private int currentItemStock;
    private static List<Items> itemDetails;

    public Items() {
        itemDetails = getItemsDetails();
    }

    public Items(String itemSlot, String itemName, BigDecimal itemPrice, String itemCategory) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
    }

    // and extra constructor that maybe can be used to
    // create and object for the purchase option for the code
    public Items(String itemSlot, String itemName, BigDecimal itemPrice, String itemCategory, int itemQuantity) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.currentItemStock = DEFAULT_STOCK_QUANTITY;
    }

    // getItemDetails method return the list of items
    // and its details like item slot, item name, item price and item category
    // that is read from the given csv file
    public static List<Items> getItemsDetails() {
        String filePath = "vendingmachine.csv";
        itemDetails = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split("\\|");
                String itemSlot = lineArray[0];
                String itemName = lineArray[1];
                BigDecimal itemPrice = new BigDecimal(lineArray[2]);
                String itemCategory = lineArray[3];

                Items items = new Items(itemSlot, itemName, itemPrice, itemCategory);
                itemDetails.add(items);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error! Invalid format for Price " + e.getMessage());
        }
        return itemDetails;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getCurrentItemStock() {
        return currentItemStock;
    }

    public void setCurrentItemStock(int currentItemStock) {
        this.currentItemStock = currentItemStock;
    }

    // overrides the default toSring() method
    // to return the items from the array in a String format
    @Override
    public String toString() {
        return String.format("Item Slot: %s, Item Name: %s, Item Price: $%.2f, Item Category: %s", itemSlot, itemName, itemPrice, itemCategory);
    }
}
