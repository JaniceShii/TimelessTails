package com.petgame.ui;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.petgame.game.GameManager;

/**
 * The SettingsScreen class represents the settings menu UI for the game.
 * It has options for navigating back, accessing the main menu, viewing instructions, 
 * enabling or disabling parental controls, saving the game, and closing the application.
 * 
 * @version 1.0
 * @author Jugraj
 */
public class SettingsScreen extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JButton backButton;
    private JButton parentalControls;
    private JButton instructions;
    private JButton saveGame;
    private JButton mainMenu;

    /**
     * Constructs a SettingsScreen with a specified GameManager.
     * 
     * @param gm The GameManager instance used for controlling game flow and accessing save functionality.
     */
    public SettingsScreen(GameManager gm) {
        this.gm = gm;
        createUIElements();

    }

    /**
     * Creates and initializes all UI components for the settings screen.
     */
    @Override
    public void createUIElements() {
        titleLabel = new JLabel("Settings");
        titleLabel.setBounds((400 - 150) / 2, 250, 150, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24));
        uiCircle.add(titleLabel);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(100, 254, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.previous());
        uiCircle.add(backButton);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(75, 310, 250, 40);
        mainMenu.setFont(new Font("Inter", Font.PLAIN, 14));
        mainMenu.setBackground(Color.white);
        mainMenu.setFocusPainted(false);
        mainMenu.addActionListener(e -> gm.showMainMenu());

        parentalControls = new JButton("Parental Controls");
        parentalControls.setBounds(75, 430, 250, 40);
        parentalControls.setFont(new Font("Inter", Font.PLAIN, 14));
        parentalControls.setBackground(Color.white);
        parentalControls.setFocusPainted(false);
        parentalControls.addActionListener(e -> gm.showParentalControlsLogin());
        // create password protected pop up. if no password is set, take directly to parental controls. if password has been set, open a pop up and prompt for password

        instructions = new JButton("Instructions");
        instructions.setBounds(75, 370, 250, 40);
        instructions.setFont(new Font("Inter", Font.PLAIN, 14));
        instructions.setBackground(Color.white);
        instructions.setFocusPainted(false);
        instructions.addActionListener(e -> gm.showInstructions());

        saveGame = new JButton("Save Game");
        saveGame.setBounds(75, 490, 250, 40);
        saveGame.setFont(new Font("Inter", Font.PLAIN, 14));
        saveGame.setBackground(Color.white);
        saveGame.setFocusPainted(false);


        uiCircle.add(parentalControls);
        uiCircle.add(instructions);
        uiCircle.add(saveGame);
        uiCircle.add(mainMenu);

        saveGame.addActionListener(e -> {
            // Get the current save slot from GameManager.

            String baseDir = "saves";
            File saveDir = new File(baseDir);
            if (!saveDir.exists()) {
                // Create the directory if it doesn't exist.
                saveDir.mkdirs();
            }

            int slot = gm.getCurrentSaveSlot(); // Should be nonzero if a game was loaded.
    
            if (slot == 0) { // New game; find the next available slot.
                boolean found = false;
                for (int i = 1; i <= 3; i++) {
                    // Use the baseDir when checking for the file.
                    File petFile = new File(baseDir + File.separator + "pet" + i + ".txt");
                    if (!petFile.exists()) {
                        slot = i;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // No available save slot.
                    System.out.println("No available save slots. Try again.");
                    JOptionPane.showMessageDialog(null, "No available save slots.", "Save Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Abort saving.
                }
                gm.setCurrentSaveSlot(slot);
            }            
            gm.endSession();
            
            // Construct filenames for pet and inventory data.
            String petFileName = baseDir + File.separator + "pet" + slot + ".txt";
            String inventoryFileName = baseDir + File.separator + "inventory" + slot + ".txt";
            String parentalControlFileName = baseDir + File.separator + "parentalControl" + ".txt";
            // Save pet and inventory data.
            PetSaver.savePet(petFileName, gm.getCurrentPet());
            InventorySaver.saveInventory(inventoryFileName, gm.getCurrentInventory());
            ParentalControlSaver.saveParentalControl(parentalControlFileName, gm.getParentalControl());
            gm.setCurrentSaveSlot(0);
            gm.startSession();
            
            System.out.println("Game saved to slot " + slot + " (" + petFileName + " and " + inventoryFileName + ")");
            JOptionPane.showMessageDialog(null, 
                "Save Successful. Game saved to slot " + slot);
        });


        JPanel closePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
                
                g2d.dispose();
            }
        };
        
        closePanel.setBounds(910, 20, 60, 60);
        closePanel.setOpaque(false);
        closePanel.setLayout(new BorderLayout());
        uiCircle.add(closePanel);

        JLabel closeBgLabel = new JLabel();
        ImageIcon closeUnscaled = new ImageIcon(getClass().getClassLoader().getResource("com/petgame/assets/closeIcon.png"));
        Image closeScaledImage = closeUnscaled.getImage().getScaledInstance(60, 55, Image.SCALE_SMOOTH);
        ImageIcon closeBackground = new ImageIcon(closeScaledImage);
        closeBgLabel.setIcon(closeBackground);
        closePanel.add(closeBgLabel);

        JButton closeButton = new JButton();
        closeButton.setBounds(910, 20, 60, 60);
        closeButton.setFocusPainted(false); 
        closeButton.setContentAreaFilled(false);
        closeButton.setBorder(null);
        closeButton.setOpaque(false); 
        uiCircle.add(closeButton);
        closeButton.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(
            null,                          
            "Are you sure you want to close? If you close, unsaved progress will be lost.", 
            "Confirm Close",                 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            new Object[] {"Yes", "No"},
            "Yes" 
        );
    
        if (result == 0) { 
            gm.closeWindow();
        }
            
        });
    }
}