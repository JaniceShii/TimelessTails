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
 * The class represents a screen where players can revive their pet by selecting a saved game slot.
 * 
 * It allows the player to load previously saved inventory and pet data, restore pet stats to maximum values, and
 * continue gameplay from where they left off.
 * 
 * @version 1.0
 * @author Jugraj
 */

public class Revive extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JLabel infoLabel;
    private JButton backButton;
    private JButton game1;
    private JButton game2;
    private JButton game3;

    /**
     * Constructs a screen with the given {@code GameManager} instance.
     *
     * @param gm The {@code GameManager} that controls the game's state and logic.
     */
    public Revive(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    /**
     * Initializes and lays out the user interface elements of the Revive screen.
     * This includes title label, information label, game slot buttons, and the close button.
     */
    @Override
    public void createUIElements() {
        titleLabel = new JLabel("Revive");
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

        infoLabel = new JLabel("Choose a file to revive");
        infoLabel.setBounds((400 - 150) / 2 - 75, 300, 300, 30);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Inter", Font.BOLD, 15));
        uiCircle.add(infoLabel);

        game1 = new JButton("Game 1");
        game2 = new JButton("Game 2");
        game3 = new JButton("Game 3");

        JButton[] gameButtons = {game1, game2, game3};
        int y = 340;

        for (JButton btn : gameButtons) {
            btn.setBounds(75, y, 250, 40);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Inter", Font.PLAIN, 14));
            uiCircle.add(btn);
            y += 65;
        }


        // Add action listeners for each game slot button.
        game1.addActionListener(e -> {
            // Load inventory and pet for game slot 1.

            gm.setCurrentSaveSlot(1);

            String baseDir = "saves"; // Folder in your working directory
            File saveDir = new File(baseDir);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
                }
            String petFilePath = baseDir + File.separator + "pet" + gm.getCurrentSaveSlot() + ".txt";
            String invFilePath = baseDir + File.separator + "inventory" + gm.getCurrentSaveSlot() + ".txt";
            // Revive the pet by setting all stats to their maximum values
            if (new File(petFilePath).exists() && new File(invFilePath).exists()) {
                InventoryLoader.loadInventory(invFilePath, gm.getCurrentInventory());
                PetLoader.loadPet(petFilePath, gm);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Save file does not exist for slot 1.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
                
                // Set all pet stats to maximum values
                if (gm.getCurrentPet().getHealth() <= 0) {
                    // Set all pet stats to maximum values
                    gm.getCurrentPet().setHealthCur(gm.getCurrentPet().getMaxHealth());
                    gm.getCurrentPet().setFullnessCur(gm.getCurrentPet().getMaxFullness());
                    gm.getCurrentPet().setSleepCur(gm.getCurrentPet().getMaxSleep());
                    gm.getCurrentPet().setHappinessCur(gm.getCurrentPet().getMaxHappiness());
                    gm.getCurrentPet().setState("normal");
                    
                    // Save the revived pet stats
                    PetSaver.savePet(petFilePath, gm.getCurrentPet());

                    switch (gm.getCurrentPet().getPetType()) {
                        case "Furfrou":
                            gm.showMainGamePlayFurfrou();
                            break;
                        case "Tyrunt":
                            gm.showMainGamePlayTyrunt();
                            break;
                        case "Magearna":
                            gm.showMainGamePlayMagearna();
                            break;
                    }
                } else {
                    // Show message that pet is not dead
                    JOptionPane.showMessageDialog(null, 
                        "This pet is not dead!", 
                        "Revive Error", 
                        JOptionPane.INFORMATION_MESSAGE);
                    gm.previous(); // Return to previous screen

                }
            });
            
        

        game2.addActionListener(e -> {
            // Load inventory and pet for game slot 2.
            gm.setCurrentSaveSlot(2);

            String baseDir = "saves"; // Folder in your working directory
            File saveDir = new File(baseDir);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
                }
            String petFilePath = baseDir + File.separator + "pet" + gm.getCurrentSaveSlot() + ".txt";
            String invFilePath = baseDir + File.separator + "inventory" + gm.getCurrentSaveSlot() + ".txt";


            // Revive the pet by setting all stats to their maximum values
            if (new File(petFilePath).exists() && new File(invFilePath).exists()) {
                InventoryLoader.loadInventory(invFilePath, gm.getCurrentInventory());
                PetLoader.loadPet(petFilePath, gm);
                
                // Set all pet stats to maximum values
                if (gm.getCurrentPet().getHealth() <= 0) {
                    // Set all pet stats to maximum values
                    gm.getCurrentPet().setHealthCur(gm.getCurrentPet().getMaxHealth());
                    gm.getCurrentPet().setFullnessCur(gm.getCurrentPet().getMaxFullness());
                    gm.getCurrentPet().setSleepCur(gm.getCurrentPet().getMaxSleep());
                    gm.getCurrentPet().setHappinessCur(gm.getCurrentPet().getMaxHappiness());
                    gm.getCurrentPet().setState("normal");

                    
                    // Save the revived pet stats
                    PetSaver.savePet(petFilePath, gm.getCurrentPet());

                    switch (gm.getCurrentPet().getPetType()) {
                        case "Furfrou":
                            gm.showMainGamePlayFurfrou();
                            break;
                        case "Tyrunt":
                            gm.showMainGamePlayTyrunt();
                            break;
                        case "Magearna":
                            gm.showMainGamePlayMagearna();
                            break;
                    }
                } else {
                    // Show message that pet is not dead
                    JOptionPane.showMessageDialog(null, 
                        "This pet is not dead!", 
                        "Revive Error", 
                        JOptionPane.INFORMATION_MESSAGE);
                    gm.previous(); // Return to previous screen

                }
            }
            
             // Start or update the game with the loaded data.
        });

        game3.addActionListener(e -> {
            // Load inventory and pet for game slot 3.
            gm.setCurrentSaveSlot(3);

            String baseDir = "saves"; // Folder in your working directory
            File saveDir = new File(baseDir);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
                }
            String petFilePath = baseDir + File.separator + "pet" + gm.getCurrentSaveSlot() + ".txt";
            String invFilePath = baseDir + File.separator + "inventory" + gm.getCurrentSaveSlot() + ".txt";


            // Revive the pet by setting all stats to their maximum values
            if (new File(petFilePath).exists() && new File(invFilePath).exists()) {
                InventoryLoader.loadInventory(invFilePath, gm.getCurrentInventory());
                PetLoader.loadPet(petFilePath, gm);
                
                // Set all pet stats to maximum values
                if (gm.getCurrentPet().getHealth() <= 0) {
                    // Set all pet stats to maximum values
                    gm.getCurrentPet().setHealthCur(gm.getCurrentPet().getMaxHealth());
                    gm.getCurrentPet().setFullnessCur(gm.getCurrentPet().getMaxFullness());
                    gm.getCurrentPet().setSleepCur(gm.getCurrentPet().getMaxSleep());
                    gm.getCurrentPet().setHappinessCur(gm.getCurrentPet().getMaxHappiness());
                    gm.getCurrentPet().setState("normal");

                    
                    // Save the revived pet stats
                    PetSaver.savePet(petFilePath, gm.getCurrentPet());

                    switch (gm.getCurrentPet().getPetType()) {
                        case "Furfrou":
                            gm.showMainGamePlayFurfrou();
                            break;
                        case "Tyrunt":
                            gm.showMainGamePlayTyrunt();
                            break;
                        case "Magearna":
                            gm.showMainGamePlayMagearna();
                            break;
                    }
                } else {
                    // Show message that pet is not dead
                    JOptionPane.showMessageDialog(null, 
                        "This pet is not dead!", 
                        "Revive Error", 
                        JOptionPane.INFORMATION_MESSAGE);
                    gm.previous(); // Return to previous screen

                }
            }
            
             // Start or update the game with the loaded data.
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
