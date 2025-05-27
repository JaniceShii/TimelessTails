package ui;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InventoryLoader {
    /**
     * Loads inventory data from a text file in key=value format into the given Inventory instance.
     * The file (e.g., inventory.txt) should follow the format:
     *
     * item.count=3
     *
     * item.1.name=Berry
     * item.1.fileName=berryItem.png
     * item.1.type=food
     * item.1.happy=0
     * item.1.fullness=10
     * item.1.numItems=3
     *
     * item.2.name=Ice Cream
     * item.2.fileName=casteliaconeItem.png
     * item.2.type=gift
     * item.2.happy=0
     * item.2.fullness=10
     * item.2.numItems=2
     *
     * item.3.name=Comb
     * item.3.fileName=combItem.png
     * item.3.type=food
     * item.3.happy=0
     * item.3.fullness=10
     * item.3.numItems=1
     *
     * @param filePath the path to the text file (e.g., "inventory.txt")
     * @param inventory the Inventory instance to load data into
     */
    public static void loadInventory(String filePath, Inventory inventory) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Inventory file " + filePath + " does not exist.");
            return;
        }


        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Get the count of items
        int count = Integer.parseInt(properties.getProperty("item.count", "0").trim());
        
        // Clear the existing inventory by replacing the map with a new one.
        inventory.setItemMap(new java.util.HashMap<>());
        
        // Loop through each item record in the file.
        for (int i = 1; i <= count; i++) {
            String prefix = "item." + i + ".";
            String name = properties.getProperty(prefix + "name");
            String fileName = properties.getProperty(prefix + "fileName");
            String type = properties.getProperty(prefix + "type");
            int happy = Integer.parseInt(properties.getProperty(prefix + "happy", "0").trim());
            int fullness = Integer.parseInt(properties.getProperty(prefix + "fullness", "0").trim());
            int numItems = Integer.parseInt(properties.getProperty(prefix + "numItems", "0").trim());
            
            // Create a new item using the constructor that accepts numItems.
            Item item = new Item(name, fileName, type, happy, fullness, numItems);
            
            // Add the item to the inventory.
            // Note: The addItem method increases the item count automatically,
            // so we explicitly set the number afterward to preserve the loaded value.
            inventory.addItem(item);
            item.setNumItems(numItems);
        }
    }

}
