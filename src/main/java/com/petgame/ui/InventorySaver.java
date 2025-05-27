package com.petgame.ui;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/** 
 * InventorySaver class is responsible for saving inventory data to a text file
 * in key=value format. The text file will contain item details such as name, type,
 * and quantities.
 *
 * @version 1.0
 * @author Jugraj Khangura
 */
public class InventorySaver {
    /**
     * Saves inventory data to a text file in key=value format.
     * The file will contain entries such as:
     *
     * item.count=3
     * item.1.name=Berry
     * item.1.fileName=berryItem.png
     * item.1.type=food
     * item.1.happy=0
     * item.1.fullness=10
     * item.1.numItems=3
     *
     * @param filePath the path to the output text file (e.g., "inventory.txt")
     * @param inventory the Inventory object whose data is to be saved
     */

    public static void saveInventory(String filePath, Inventory inventory) {
        Properties properties = new Properties();
        HashMap<String, Item> itemMap = inventory.getItemMap();
        
        // Write out the total count of distinct items.
        properties.setProperty("item.count", Integer.toString(itemMap.size()));
        
        int index = 1;
        // Iterate over the items in the inventory.
        for (Item item : itemMap.values()) {
            String prefix = "item." + index + ".";
            properties.setProperty(prefix + "name", item.getName());
            properties.setProperty(prefix + "fileName", item.getFileName());
            properties.setProperty(prefix + "type", item.getType());
            properties.setProperty(prefix + "happy", Integer.toString(item.getHappy()));
            properties.setProperty(prefix + "fullness", Integer.toString(item.getFullness()));
            properties.setProperty(prefix + "numItems", Integer.toString(item.getNumItems()));
            index++;
        }
        
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, "Inventory Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}