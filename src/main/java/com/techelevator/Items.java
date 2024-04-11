package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class Items {
    //    private static final int DEFAULT_STOCK_QUANTITY = 5;
    private String itemSlot;
    private String itemName;
    private BigDecimal itemPrice;
    private String itemCategory;
    private int currentItemStock;

    public Items() {
        //itemDetails = getItemsDetails();
    }

    public Items(String itemSlot, String itemName, BigDecimal itemPrice, String itemCategory, int currentItemStock) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.currentItemStock = currentItemStock;
    }


    public String getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(String itemSlot) {
        this.itemSlot = itemSlot;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getCurrentItemStock() {
        return currentItemStock;
    }

    public void setCurrentItemStock(int currentItemStock) {
        this.currentItemStock = currentItemStock;
    }

    // getItemDetails method return the list of items
    // and its details like item slot, item name, item price, item category, item stock
    // that is read from the given csv file
    public List<Items> getItemsDetails() {
        String filePath = "vendingmachine.csv";
        List<Items> itemDetails = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split("\\|");
                String itemSlot = lineArray[0];
                String itemName = lineArray[1];
                BigDecimal itemPrice = new BigDecimal(lineArray[2]);
                String itemCategory = lineArray[3];
                int currentItemStock = Integer.parseInt(lineArray[4]);
                Items items = new Items(itemSlot, itemName, itemPrice, itemCategory, currentItemStock);
                itemDetails.add(items);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error! Invalid format for Price " + e.getMessage());
        }
        return itemDetails;
    }


    // adding a method that returns the item according to the slot location.
    public Items getItemBySlotLocation(String slotLocation) {
        for (Items item : getItemsDetails()) {
            if (item.getItemSlot().equalsIgnoreCase(slotLocation)) {
                return item;
            }
        }
        return null;
    }


    // overrides the default toSring() method
    // to return the items from the array in a String format
    @Override
    public String toString() {
        //return String.format("Item Slot: %s, Item Name: %s, Item Price: $%.2f, Item Category: %s, Item Stock: %s", itemSlot, itemName, itemPrice, itemCategory, itemQuantity);
        return String.format("%s - %s - $%.2f - %s - %s", itemSlot, itemName, itemPrice, itemCategory, currentItemStock);
    }

}


// dummy code

//                        Items items = new Items();
//                        for (Items item : items.getItemsDetails()) {
//                            System.out.println(item.toString());
//                        }
//
//                        Items selectedItem = items.getItemBySlotLocation("A2");
//                        if (selectedItem != null) {
//                            System.out.println("This is the selected item : " + selectedItem);
//                        } else {
//                            System.out.println("Cannot find the item");
//                        }

