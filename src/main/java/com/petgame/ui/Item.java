package com.petgame.ui;

/*
 * item class represents items that can be used on the pet
 * items are collected by the pet from taking a walk 
 * items are stored in the inventory that the user can access 
 * 
 * @version 1.0 
 * @author Jessica Yang
 * 
 */
public class Item {
    private String name;                    // describes the name of the item
    private String itemImageFile;           // stores the name of the image file
    private int numItems;                   // stores the number of item the player has collected
    private int happinessNum;               // stores the amount that increases the pet's happiness stat
    private int fullnessNum;                // stores the amount that increases the pet's fullness stat
    private String type;

    /**
     * Constructs an Item with the specified attributes. The number of items is initialized to 0.
     * 
     * @param newName   The name of the item.
     * @param fileName  The name of the image file associated with the item.
     * @param typeItem  The type of the item (e.g., food, toy).
     * @param happy     The amount by which the item increases happiness.
     * @param fullness  The amount by which the item increases fullness.
     */
    public Item (String newName, String fileName, String typeItem, int happy, int fullness) {
        name = newName;
        itemImageFile = fileName;
        type = typeItem;
        happinessNum = happy;
        fullnessNum = fullness;
        numItems = 0;
    }

    /**
     * Constructs an Item with the specified attributes, including the number of items.
     * 
     * @param newName   The name of the item.
     * @param fileName  The name of the image file associated with the item.
     * @param typeItem  The type of the item (e.g., food, toy).
     * @param happy     The amount by which the item increases happiness.
     * @param fullness  The amount by which the item increases fullness.
     * @param num       The initial quantity of the item.
     */
    public Item (String newName, String fileName, String typeItem, int happy, int fullness, int num) {
        name = newName;
        itemImageFile = fileName;
        type = typeItem;
        happinessNum = happy;
        fullnessNum = fullness;
        numItems = num;
    }

    /**
     * Increases the number of items by one.
     */
    public void increaseNumItems() {
        numItems++;
    }

    /**
     * Decreases the number of items by one.
     */
    public void decreaseNumItems() {
        if (numItems > 0) numItems--;
    }

    /**
     * Returns the name of the item.
     * 
     * @return The item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the file name of the item's image.
     * 
     * @return The file name of the item's image.
     */
    public String getFileName() {
        return itemImageFile;
    }

    /**
     * Returns the number of items the player currently has.
     * 
     * @return The number of items.
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * Returns the value by which the item increases happiness.
     * 
     * @return The happiness value associated with the item.
     */
    public int getHappy() {
        return happinessNum;
    }

    /**
     * Returns the value by which the item increases fullness.
     * 
     * @return The fullness value associated with the item.
     */
    public int getFullness() {
        return fullnessNum;
    }

    /**
     * Returns the type of the item.
     * 
     * @return The item's type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the name of the item.
     * 
     * @param name The new name of the item.
     */
    public void setName(String name)                    { this.name = name; }
    /**
     * Sets the file name of the item's image.
     * 
     * @param fileName The new file name of the item's image.
     */
    public void setFileName(String fileName)            { this.itemImageFile = fileName; }
    /**
     * Sets the number of items the player has.
     * 
     * @param numItems The new number of items.
     */
    public void setNumItems(int numItems)               { this.numItems = numItems; }
    /**
     * Sets the value by which the item increases happiness.
     * 
     * @param happinessNum The new happiness value.
     */
    public void setHappinessNum(int happinessNum)       { this.happinessNum = happinessNum; }
    /**
     * Sets the value by which the item increases fullness.
     * 
     * @param fullnessNum The new fullness value.
     */
    public void setFullnessNum(int fullnessNum)         { this.fullnessNum = fullnessNum; }
    /**
     * Sets the type of the item.
     * 
     * @param type The new type of the item.
     */
    public void setType(String type)                    { this.type = type; }



}
