package com.petgame;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items;

    public Inventory() {
        // For example, a simple map of itemName -> quantity
        items = new HashMap<>();
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    // If you want a helper method to add items:
    public void addItem(String itemName, int quantity) {
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
    }

    public void removeItem(String itemName, int quantity) {
        if (items.containsKey(itemName)) {
            int current = items.get(itemName);
            int newAmount = current - quantity;
            if (newAmount <= 0) {
                items.remove(itemName);
            } else {
                items.put(itemName, newAmount);
            }
        }
    }
}