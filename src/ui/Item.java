package ui;

/*
 * item class represents items that can be used on the pet
 * items are collected by the pet from taking a walk 
 * items are stored in the inventory that the user can access 
 * 
 */

public class Item {
    private String name;                    // describes the name of the item
    private String itemImageFile;           // stores the name of the image file
    private int numItems;                   // stores the number of item the player has collected
    private int happinessNum;               // stores the amount that increases the pet's happiness stat
    private int fullnessNum;                // stores the amount that increases the pet's fullness stat
    private String type;

    // constructor intializes all variables with values
    // sets numItems as 0 if not given
    public Item (String newName, String fileName, String typeItem, int happy, int fullness) {
        name = newName;
        itemImageFile = fileName;
        type = typeItem;
        happinessNum = happy;
        fullnessNum = fullness;
        numItems = 0;
    }

    // constrcutor that intializes all variables with values
    // sets numItems if given
    public Item (String newName, String fileName, String typeItem, int happy, int fullness, int num) {
        name = newName;
        itemImageFile = fileName;
        type = typeItem;
        happinessNum = happy;
        fullnessNum = fullness;
        numItems = num;
    }

    // increaseNumItems method increases the number of items by one
    public void increaseNumItems() {
        numItems++;
    }

    // decreaseNumItems method decreases thenumber of items by one
    public void decreaseNumItems() {
        if (numItems > 0) numItems--;
    }


    // getter methods below ---------------------------------------------------------------------

    // getName returns the name of the item
    public String getName() {
        return name;
    }

    // getFileName returns the string of the image's file name 
    public String getFileName() {
        return itemImageFile;
    }

    // getNumItems returns the number of items the player has
    public int getNumItems() {
        return numItems;
    }

    // getHappy returns the value that increases the happiness stat
    public int getHappy() {
        return happinessNum;
    }

    // getFullness returns the value that increases the fullness stat
    public int getFullness() {
        return fullnessNum;
    }

    // getType returns the type of item it is
    public String getType() {
        return type;
    }

    public void setName(String name)                    { this.name = name; }
    public void setFileName(String fileName)            { this.itemImageFile = fileName; }
    public void setNumItems(int numItems)               { this.numItems = numItems; }
    public void setHappinessNum(int happinessNum)       { this.happinessNum = happinessNum; }
    public void setFullnessNum(int fullnessNum)         { this.fullnessNum = fullnessNum; }
    public void setType(String type)                    { this.type = type; }



}
