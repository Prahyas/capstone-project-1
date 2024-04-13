package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    // Purchased items are
    // String: purchased item name, Integer: how many each item was purchased
    private static Map<String, Integer> salesReportList = new HashMap<>();
    private final int INITIAL_QUANTITY_OF_PURCHASED_ITEMS = 0;

    public ShoppingCart(List<Items> itemsList) {
        for(Items item : itemsList) {
            salesReportList.put(item.getItemName(), INITIAL_QUANTITY_OF_PURCHASED_ITEMS);
        }
    }

    public void addToCart(Items chosenItem) {
        int currentNumberOfThePurchasedItem = salesReportList.get(chosenItem.getItemName());
        salesReportList.put(chosenItem.getItemName(), currentNumberOfThePurchasedItem + 1);
    }

    public void printSalesReport() {
        StringBuilder salesReport = new StringBuilder();
        System.out.println("**SALES REPORT**");
        System.out.println();
        for(Map.Entry<String, Integer> item : salesReportList.entrySet()) {
            salesReport.append(item.getKey()).append(" | ").append(item.getValue()).append("\n");
            System.out.printf("%s | %s\n", item.getKey(), item.getValue());
        }
        System.out.println();
        salesReport.append("\n").append("TOTAL SALES $").append(Customer.getTotalCost());
        System.out.println("TOTAL SALES $" + Customer.getTotalCost());
        Log.salesReportLog(String.format("**SALES REPORT**\n\n%s", salesReport));
    }

}
