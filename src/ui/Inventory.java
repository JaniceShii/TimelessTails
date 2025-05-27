package ui;
import java.util.HashMap;

/*
 * Inventory class represents the entire eventory of a player
 * It will store a hashMap of Items to keep track of what items a player has 
 */

public class Inventory {

    // HashMap of items that stores all the items that a user has
    // item name -> item object
    private HashMap<String, Item> itemMap;
    private String msg = "";
    private String itemFound = "";

    // inventory ui
    private InventoryUI ui;

    // create instances of all items that can be added (10 items)
    Item berry = new Item("Berry", "berryItem.png", "food", 0, 30);
    Item iceCream = new Item("Ice Cream", "casteliaconeItem.png", "food", 0, 30);
    Item curry = new Item("Curry", "CurryItem.png", "food", 0, 50);
    Item poffin = new Item("Poffin", "poffinItem.png", "food", 0, 40);
    Item ball = new Item("Ball", "ball.jpeg", "gift", 35, 0);
    Item comb = new Item("Comb", "combItem.png", "gift", 40, 0);
    Item gear = new Item("Gear", "gearItem.png", "gift", 40, 0);
    Item flower = new Item("Flower", "flower.jpeg", "gift", 20, 0);
    Item bone = new Item("Bone", "bone.jpeg", "gift", 40, 0);
    Item pokepuff = new Item("Pokepuff", "pokepuffItem.png", "food", 0, 40);

    // constructor to initialize the hashMap
    public Inventory() {
        itemMap = new HashMap<>();
        //populateInventory();
    }

    // addItem method adds an item to the hashMap
    public void addItem(Item item) {

        // check if item already exists in hashMap to add it
        if (itemMap.containsKey(item.getName())){
            // if item exists in hashMap
            // then increase the number of items by one
            item.increaseNumItems();

        } else {
            // if item does not exist in hashMap
            // then add it and increase number of items by one
            itemMap.put(item.getName(), item);
            item.increaseNumItems();
        }
        
        // update the ui
        ui.updateInventoryDisplay();
        ui.createItemDisplayPanel();
    }

    // addRandItem method adds an item from when exercise button is clicked
    public void addRandItem(int randInt) {
        // add items according to randNum passsed
        switch (randInt) {
            case 0:
                addItem(berry);
                itemFound = "Berry";
                break;

            case 1:
                addItem(iceCream);
                itemFound = "Ice Cream";
                break;


            case 2:
                addItem(curry);
                itemFound = "Curry";
                break;


            case 3:
                addItem(poffin);
                itemFound = "Poffin";
                break;


            case 4:
                addItem(flower);
                itemFound = "Flower";
                break;


            case 5:
                addItem(ball);
                itemFound = "Ball";
                break;


            case 6:
                addItem(comb);
                itemFound = "Comb";
                break;


            case 7:
                addItem(gear);
                itemFound = "gear";
                break;


            case 8:
                addItem(bone);
                itemFound = "Bone";
                break;

            case 9:
                addItem(pokepuff);
                itemFound = "Pokepuff";

            default:
                break;

        }

        // update the inventoryUI with new items
        ui.updateInventoryDisplay();
        ui.createItemDisplayPanel();
    }

    // removeItem method removes an item from the array
    public void removeItem(String itemName) {

        // check if the item exists in the hashMap to remove it
        if (itemMap.containsKey(itemName)){

            // check the number of this item to determine if the image should be removed from the UI
            Item item = itemMap.get(itemName); 
            if (item.getNumItems() == 1){
                // if there is only one of this item
                // then the item will be removed from the hashmap
                item.decreaseNumItems();
                itemMap.remove(itemName);

            } else {
                // if there is more than one of this item
                // then the item will stay on the hashMap
                // and the number of this item will decrease by one
                item.decreaseNumItems();
            }    

        } else {
            // when item is not found  
        }
    }

    // populateInventory method inserts preexisting items into the inventory
    public void populateInventory() {
        addItem(iceCream);
        addItem(curry);
        addItem(flower);
        addItem(ball);
        ui.updateInventoryDisplay();
        ui.createItemDisplayPanel();
    }

    // getter methods below ----------------------------------------------------

    // getItem method gets the item from the itemName
    public Item getItem(String itemName){
        if (itemMap.containsKey(itemName)){
            return itemMap.get(itemName);
        } else {
            return null;
        }
    }


    // getItemMap method return the itemMap
    public HashMap<String,Item> getItemMap() {
        return itemMap;
    }

    // NEW: let us overwrite the entire map (if needed)
    public void setItemMap(HashMap<String, Item> newMap) {
        this.itemMap = newMap;
    }

    public void setInventoryUI(InventoryUI ui) {
        this.ui = ui;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public String getMessage(String petName) {
        msg = petName + " found a " + itemFound + " while on the walk! Item added to Inventory.";
        return msg;
    }

}
