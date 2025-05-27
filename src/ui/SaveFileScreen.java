package ui;

import game.GameManager;
import java.awt.*;
import java.io.File;
import javax.swing.*;

public class SaveFileScreen extends AbstractScreen {
    GameManager gm;

    private JLabel titleLabel;
    private JButton backButton;
    private JButton game1;
    private JButton game2;
    private JButton game3;
    private boolean isDead;
    private JButton delete1;
    private JButton delete2;
    private JButton delete3;

    public SaveFileScreen(GameManager gm) {
        this.gm = gm;
        createUIElements();
    }

    @Override
    public void createUIElements() {
        titleLabel = new JLabel("Save Files");
        titleLabel.setBounds((400 - 150) / 2, 250, 150, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24));
        uiCircle.add(titleLabel);

        ImageIcon backIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/back_arrow.png"));
        backButton = new JButton(backIcon);
        backButton.setBounds(100, 254, 24, 24);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> gm.previous());
        uiCircle.add(backButton);

        game1 = new JButton("Game 1");
        game2 = new JButton("Game 2");
        game3 = new JButton("Game 3");

        JButton[] gameButtons = {game1, game2, game3};
        int y = 325;

        for (JButton btn : gameButtons) {
            btn.setBounds(65, y, 200, 40);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Inter", Font.PLAIN, 14));
            uiCircle.add(btn);
            y += 65;
        }

        delete1 = new JButton("Delete");
        delete2 = new JButton("Delete");
        delete3 = new JButton("Delete");

        JButton[] deletebuttons = {delete1, delete2, delete3};
        int x = 325;

        for (JButton btn2 : deletebuttons) {
            btn2.setBounds(280, x, 75, 40);
            btn2.setBackground(new Color(255, 30, 30, 255));
            btn2.setFocusPainted(false);
            btn2.setForeground(Color.WHITE);
            btn2.setFont(new Font("Inter", Font.PLAIN, 14));
            uiCircle.add(btn2);
            x += 65;
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


            if (new File(petFilePath).exists() && new File(invFilePath).exists()) {
                InventoryLoader.loadInventory(invFilePath, gm.getCurrentInventory());
                PetLoader petLoader = new PetLoader();
                isDead = petLoader.checkDeath(petFilePath, gm);
                if (!isDead) {
                    petLoader.loadPet(petFilePath, gm);
                } else {
                   JOptionPane.showMessageDialog(null, 
                   "Pet is dead, please select a different save slot or revive the pet (parent).", 
                   "Error", 
                   JOptionPane.ERROR_MESSAGE);
                   return;
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Save file does not exist for slot 1.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
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
            }  // Start or update the game with the loaded data.
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


            if (new File(petFilePath).exists() && new File(invFilePath).exists()) {
                InventoryLoader.loadInventory(invFilePath, gm.getCurrentInventory());
                PetLoader.loadPet(petFilePath, gm);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Save file does not exist for slot 2.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gm.getCurrentPet().getHealth() <= 0) {
                JOptionPane.showMessageDialog(null, 
                    "Pet is dead, please select a different save slot or revive the pet (parent).", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

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


            if (new File(petFilePath).exists() && new File(invFilePath).exists()) {
                InventoryLoader.loadInventory(invFilePath, gm.getCurrentInventory());
                PetLoader.loadPet(petFilePath, gm);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Save file does not exist for slot 3.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gm.getCurrentPet().getHealth() <= 0) {
                JOptionPane.showMessageDialog(null, 
                    "Pet is dead, please select a different save slot or revive the pet (parent).", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

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
        });

        // Add action listeners for delete buttons.
        delete1.addActionListener(e -> {
            int slot = 1;
            String baseDir = "saves";
            String petFileName = baseDir + File.separator + "pet" + slot + ".txt";
            String invFileName = baseDir + File.separator + "inventory" + slot + ".txt";
            File petFile = new File(petFileName);
            File invFile = new File(invFileName);
            boolean petDeleted = false;
            boolean invDeleted = false;
            if (petFile.exists()) {
                petDeleted = petFile.delete();
            }
            if (invFile.exists()) {
                invDeleted = invFile.delete();
            }
            if (petDeleted || invDeleted) {
                JOptionPane.showMessageDialog(null, 
                    "Save for slot " + slot + " has been deleted.", 
                    "Delete Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Optionally, reset current save slot if this slot was in use.
                if (gm.getCurrentSaveSlot() == slot) {
                    gm.setCurrentSaveSlot(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No save file exists for slot " + slot + ".", 
                    "Delete Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

                // Action listener for Delete button for slot 2.
                delete2.addActionListener(e -> {
                    int slot = 2;
                    String baseDir = "saves";
                    String petFileName = baseDir + File.separator + "pet" + slot + ".txt";
                    String invFileName = baseDir + File.separator + "inventory" + slot + ".txt";
                    File petFile = new File(petFileName);
                    File invFile = new File(invFileName);
                    boolean petDeleted = false;
                    boolean invDeleted = false;
                    if (petFile.exists()) {
                        petDeleted = petFile.delete();
                    }
                    if (invFile.exists()) {
                        invDeleted = invFile.delete();
                    }
                    if (petDeleted || invDeleted) {
                        JOptionPane.showMessageDialog(null, 
                            "Save for slot " + slot + " has been deleted.", 
                            "Delete Successful", 
                            JOptionPane.INFORMATION_MESSAGE);
                        if (gm.getCurrentSaveSlot() == slot) {
                            gm.setCurrentSaveSlot(0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "No save file exists for slot " + slot + ".", 
                            "Delete Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                });

        // Action listener for Delete button for slot 3.
        
        delete3.addActionListener(e -> {
            int slot = 3;
            String baseDir = "saves";
            String petFileName = baseDir + File.separator + "pet" + slot + ".txt";
            String invFileName = baseDir + File.separator + "inventory" + slot + ".txt";
            File petFile = new File(petFileName);
            File invFile = new File(invFileName);
            boolean petDeleted = false;
            boolean invDeleted = false;
            if (petFile.exists()) {
                petDeleted = petFile.delete();
            }
            if (invFile.exists()) {
                invDeleted = invFile.delete();
            }
            if (petDeleted || invDeleted) {
                JOptionPane.showMessageDialog(null, 
                    "Save for slot " + slot + " has been deleted.", 
                    "Delete Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
                if (gm.getCurrentSaveSlot() == slot) {
                    gm.setCurrentSaveSlot(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No save file exists for slot " + slot + ".", 
                    "Delete Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
