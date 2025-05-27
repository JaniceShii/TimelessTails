package com.petgame.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;          // actionEvent and ActionListener used to handle button clicks or user input
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

import java.net.URL;

import com.petgame.game.GameManager;

/*
 *  InventoryUI class represents the UI of the inventory of a player  
 *  includes displaying what items and how many items a player currently has
 *  player can use items from their inventory
 *  player can exit from the inventory to return to the main game
 * 
 * @version 1.0
 * @author Jessica Yang
 */
public class InventoryUI extends AbstractScreen {
    private GameManager gm;
    private JButton[] itemSlots;                    // buttons that represent inventory slots for items
    private int selectedItemIndex = -1;             // keeps track of selected item
    private Inventory itemInventory;                // stores itemName-item (key value) of all items in inventory   
    private String msg;                             // stores the msg when pet gets an item  
    

    // ui components 
    private JPanel gridPanel;
    private JPanel panel;
    private JLabel inventoryLabel;
    private JButton useItemButton;
    private JButton exitInventoryButton;
    private GamePlayUI gameUI;

    /**
     * Constructs the InventoryUI with the given game manager, inventory, and gameplay UI.
     *
     * @param gm The GameManager object managing game logic.
     * @param inventory The Inventory object containing items to be displayed.
     * @param ui The GamePlayUI object handling gameplay interactions.
     */
    public InventoryUI(GameManager gm, Inventory inventory, GamePlayUI ui) {
        this.gm = gm;
        this.gameUI = ui;
        itemInventory = inventory;
        createUIElements();
    }

    /**
     * Creates the background for the inventory screen.
     *
     * @param petType The type of pet to customize the background.
     */
    public void createBackground(String petType) {
        super.createBackground("Furfrou"); 
        bgLabel.remove(uiCircle);       // removes previous stuff on on bgLabel
        uiCircle = new JPanel() {       // create a new blue circle
            @Override
            protected void paintComponent(Graphics g){
                // use paintComponent to make sure the background shines through on other elements 
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // create the navy blue colour circle (with transparency)
                Color navyBlue = new Color(28, 57, 102, 230); 
                g2d.setColor(navyBlue);

                // create the navy oval
                int ovalWidth = 600;
                int ovalHeight = 500;
                int x = 190;             
                int y = 40;            
                g2d.fillOval(x, y, ovalWidth, ovalHeight);
                g2d.dispose();
            }
        };

        uiCircle.setBounds(0, 0, 1000, 600);
        uiCircle.setOpaque(false);
        bgLabel.add(uiCircle);
        bgLabel.revalidate();
        bgLabel.repaint();

    }

    /**
     * Creates and updates the UI elements for displaying the inventory.
     */
    public void createUIElements() {
        // fill inventory with items
        // if itemInventory is initialized (not null) then continue updateInventoryDisplay as normal
        if (itemInventory != null) {        
            // create the title text for the inventory
            createTitleText();
            
            // update item slots
            updateInventoryDisplay();

            // create item counter under each item
            createItemDisplayPanel();

            // create use item button and exit inventory button
            createUseItemButton();
            createExitInventoryButton(); 
            
            uiCircle.revalidate();
            uiCircle.repaint();

        }           
        // if itemInventory is null then dont create any UIElements
        // inventory will update once the inventoryUI constructor is initialized and UIElements will be drawn
    }

    /**
     * Creates the "Use Item" button for applying items from the inventory.
     */
    private void createUseItemButton() {
        useItemButton = new JButton("Use Item");
        useItemButton.setBounds(430, 410, 100, 60);
        useItemButton.setOpaque(true);
        useItemButton.setBorderPainted(true);
        useItemButton.setBackground(Color.WHITE);
        useItemButton.setFont(new Font("Inter", Font.BOLD, 14));
        useItemButton.addActionListener(e -> { 
            useItem(gm.getCurrentPet());
            // gameUI.updateScore();
            
        });
        uiCircle.add(useItemButton);
    }

    /**
     * Creates the "Exit" button for exiting the inventory UI.
     */
    private void createExitInventoryButton() {
        exitInventoryButton = new JButton("Exit");
        exitInventoryButton.setBounds(445, 480, 70, 30);
        exitInventoryButton.setOpaque(true);
        exitInventoryButton.setBorderPainted(true);
        exitInventoryButton.setBackground(Color.WHITE);
        exitInventoryButton.setFont(new Font("Inter", Font.BOLD, 14));
        exitInventoryButton.addActionListener(e -> { 
            gm.setVariable(false);
            gm.previous();
            //gameUI.updateScore();
        });
        uiCircle.add(exitInventoryButton);
        
    }

    /**
     * Creates the title text for the inventory screen.
     */
    private void createTitleText() {
        inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setBounds(380, 90, 200, 30);
        inventoryLabel.setForeground(Color.WHITE);
        inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inventoryLabel.setFont(new Font("Inter", Font.BOLD, 25));
        uiCircle.add(inventoryLabel);
    }

    /*
     * Updates the inventory display by creating a grid layout for item slots and populating them with item icons.
    */
    public void updateInventoryDisplay() {
        if (gridPanel != null) {
            uiCircle.remove(gridPanel);
        }

        // create grid layout for item slots
        gridPanel = new JPanel(new GridLayout(2, 6, 15, 60));
        gridPanel.setBounds(280, 150, 400, 200);
        gridPanel.setOpaque(false);
        uiCircle.setLayout(null);
        uiCircle.add(gridPanel);

        // create the buttons for the item slots
        itemSlots = new JButton[12];
        for (int i = 0; i < 12; i++) {
            int index = i;
            itemSlots[i] = new JButton();

            // create each button for each inventory slot
            itemSlots[i].setPreferredSize(new Dimension(60, 60));
            itemSlots[i].setBackground(Color.white);
            itemSlots[i].setOpaque(true);
            itemSlots[i].setBorderPainted(true);
            itemSlots[i].addActionListener(e -> selectItem(index));
            gridPanel.add(itemSlots[i]);
        }

        // clear all button icons and text
        for (int i = 0; i < itemSlots.length; i++) {
            itemSlots[i].setIcon(null);
            itemSlots[i].setText("");
        }

        // go through all items in inventory hashMap to populate the inventory UI
        int i = 0;
        for (Map.Entry<String, Item> entry : itemInventory.getItemMap().entrySet()) {
            if (i >= 12) break;

            Item item = entry.getValue();
            // check if the number of items has reached 0 to reset item slot button as empty
            if (item.getNumItems() > 0) {
                String imageName = item.getFileName();
                if (imageName != null) {
                    try {
                        // Use the class loader to load the image from the resources folder
                        URL imageUrl = getClass().getClassLoader().getResource("com/petgame/assets/" + imageName);

                        if (imageUrl != null) {
                            // Load the image from the resource
                            ImageIcon unscaledImg = new ImageIcon(imageUrl);
                            Image scaledImg = unscaledImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(scaledImg);

                            itemSlots[i].setIcon(icon);  // set image to button
                            itemSlots[i].setText("");  // remove text
                        } else {
                            // Handle missing image scenario
                            System.err.println("Image not found: " + imageName);
                        }
                    } catch (Exception e) {
                        // Catch any other potential exceptions and log them
                        System.err.println("Error loading image: " + imageName);
                        e.printStackTrace();
                    }
                }
            } else {
                // when there is no item, create an empty cell
                itemSlots[i].setIcon(null);
                itemSlots[i].setText("");
            }
            i++;
        }

        // add to screen
        uiCircle.add(gridPanel);
        uiCircle.revalidate();
        uiCircle.repaint();
    }


    /**
     * Creates the item display panel that shows the item count and name.
     */
    public void createItemDisplayPanel () {
        if (panel != null) {
            uiCircle.remove(panel);
        }

        panel = new JPanel() { 
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // create the item count rectangles
                int columns = 6;
                int spaceX = 70;
                int spaceY = 130;
                int startX = 265;
                int startY = 30;
                int textOffSetY = 10;

                int i = 0;
                for (Map.Entry<String, Item> entry : itemInventory.getItemMap().entrySet()) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = startX + col * spaceX;
                    int y = startY + (row * spaceY) + 110;

                    // draw the rounded rectangle
                    g2d.setColor(new Color(0, 0, 0, 150));
                    g2d.fillRoundRect(x + 20, y + 85, 40, 20, 10, 10);

                    // draw the item count text
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Inter", Font.BOLD, 14));
                    g2d.drawString("" + entry.getValue().getNumItems(), x + 36, y + 100);

                    // draw the item name text under the rectangles
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Inter", Font.PLAIN, 12));
                    g2d.drawString(entry.getKey(), x + 20, y + 110 + textOffSetY);

                    i++;
                }
                g2d.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 600);
        
        // add to screen
        uiCircle.add(panel);
        uiCircle.revalidate();
        uiCircle.repaint();
    }


    /**
     * Selects an item from the inventory based on the index.
     *
     * @param index The index of the item to select.
     */
    private void selectItem(int index){
        String name = gm.getCurrentPet().getName();

        // ensure index is within bounds and that the slot has an item
        if (index < 0 || index >= itemSlots.length || itemInventory.getItemMap().size() <= index) {
            // exits if no item exists in the selected slot
            return;
        }

        selectedItemIndex = index;
        for (JButton button : itemSlots){
            // create a hover message that describes the selected item
            String selectedItemName = (String) itemInventory.getItemMap().keySet().toArray()[selectedItemIndex];
            Item selectedItem = itemInventory.getItemMap().get(selectedItemName);
            String itemType = selectedItem.getType();
            int happiness = selectedItem.getHappy();
            int fullness = selectedItem.getFullness();
            String description = selectedItemName + " is a " + itemType + " item.\n It increases happiness by " + happiness + " points.\n It increases fullness by " + fullness + " points.";

            // ensure only the selected item has a hover text
            for (int i = 0; i < itemSlots.length; i++){
                if (i == index) {
                    itemSlots[i].setToolTipText(description);
                    itemSlots[i].setBackground(Color.BLUE);

                    // immediately show the hover text when item is selected
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                    ToolTipManager.sharedInstance().mouseMoved(
                        new java.awt.event.MouseEvent(itemSlots[i], java.awt.event.MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 0, 0, 0, false));

                } else {
                    itemSlots[i].setToolTipText(null);
                    itemSlots[i].setBackground(Color.WHITE);
                }
            }
        }
    }

    /**
     * Uses the selected item on the pet.
     *
     * @param pet The pet to use the item on.
     */
    private void useItem(Pet pet) {
        if (selectedItemIndex != -1 && selectedItemIndex < itemInventory.getItemMap().size()){
            String selectedItemName = (String) itemInventory.getItemMap().keySet().toArray()[selectedItemIndex];
            Item selectedItem = itemInventory.getItemMap().get(selectedItemName);
            if (pet.getState().equals("sleep")) {
                JOptionPane.showMessageDialog(null, pet.getName() + " is asleep! Please wait for " + pet.getName() +" to wake up first!");
            } else if (pet.getState().equals("angry") && selectedItem.getType().equals("food")) {
                JOptionPane.showMessageDialog(null, pet.getName() + " is angry! Please increase its happiness first by playing with " + pet.getName() +" or giving it a gift!");
            } else {
                pet.giveItem(selectedItem);
                    
                // remove item from inventory
                itemInventory.removeItem(selectedItemName);

            // check if it was the last item to be removed 
            // to clear the button icon and text
            if (selectedItem.getNumItems() == 0) {
                itemSlots[selectedItemIndex].setIcon(null);
                itemSlots[selectedItemIndex].setText("");
            }
            itemSlots[selectedItemIndex].setBackground(Color.white);
            selectedItemIndex = -1;

            // message popup describes the item they used and its effect on the pet
            // message popup describes the item they used and its effect on the pet
            int fullness = selectedItem.getFullness();
            int happy = selectedItem.getHappy();
            String statsAffects = "";
            if (fullness > 0) {
                statsAffects = "+" + fullness + " fullness!";
            } else {
                statsAffects = "+" + happy + " happiness!";
            }
            JOptionPane.showMessageDialog(null, "You used " + selectedItemName +"! " + statsAffects);

                // refresh UI
                updateInventoryDisplay();

                gm.previous();
            } 
        } else {
                JOptionPane.showMessageDialog(null, "Select an item to use.");
        }
            
    }

}
