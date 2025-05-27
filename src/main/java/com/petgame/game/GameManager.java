package com.petgame.game;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.Instant;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import com.petgame.ui.ChoosePet;
import com.petgame.ui.DefaultPet;
import com.petgame.ui.EnableTimeLimit;
import com.petgame.ui.GamePlayUI;
import com.petgame.ui.GamePlayUIMagearna;
import com.petgame.ui.GamePlayUITyrunt;
import com.petgame.ui.Instructions;
import com.petgame.ui.Instructions2;
import com.petgame.ui.Instructions3;
import com.petgame.ui.Instructions4;
import com.petgame.ui.Instructions5;
import com.petgame.ui.Instructions6;
import com.petgame.ui.Inventory;
import com.petgame.ui.InventoryUI;
import com.petgame.ui.MainMenuScreen;
import com.petgame.ui.ParentalControl;
import com.petgame.ui.ParentalControlLoader;
import com.petgame.ui.ParentalControlSaver;
import com.petgame.ui.ParentalControlsLogin;
import com.petgame.ui.ParentalControlsScreen;
import com.petgame.ui.Pet;
import com.petgame.ui.PetNameFurfrou;
import com.petgame.ui.PetNameMagearna;
import com.petgame.ui.PetNameTyrunt;
import com.petgame.ui.Revive;
import com.petgame.ui.SaveFileScreen;
import com.petgame.ui.SettingsScreen;
import com.petgame.ui.Statistics;

/**
 * GameManager is responsible for managing the overall game flow, including
 * transitioning between different screens, handling user input, and tracking
 * game session data. It initializes the game window and UI components, and
 * manages the current state of the game, including the pet and inventory.
 * 
 * @version 1.0
 * @author Janice Shi, Meridith Shang, Jessica Yang, Jugraj Khangura, Advaith Thakur
 */
public class GameManager {

    public String currentScreen;
    public String previousScreen;
    private Pet currentPet;
    private Inventory inventory;
    private int currentSaveSlot = 0;
    private ParentalControl parentalControl;
    private Instant sessionStart;
    private boolean GMVariable = true;

    public static void main(String[] args) {
        new GameManager();
    }
    
    private JFrame window;
    private CardLayout cardLayout;
    private JPanel container;

    public static final String MAIN_MENU = "MainMenu";
    public static final String SAVE_FILE = "SaveFile";
    public static final String SETTINGS = "Settings";
    public static final String PARENTAL_CONTROLS = "ParentalControls";
    public static final String PET_NAME = "PetName";
    public static final String CHOOSE_PET = "ChoosePet";
    public static final String MAIN_GAMEPLAY_FURFROU = "MainGamePlay";
    public static final String MAIN_GAMEPLAY_TYRUNT = "MainGamePlayTyrunt";
    public static final String MAIN_GAMEPLAY_MAGEARNA = "MainGamePlayMagearna";
    public static final String PET_NAME_TYRUNT = "PetNameTyrunt";
    public static final String PET_NAME_MAGEARNA = "PetNameMagerna";
    public static final String INVENTORY_UI = "InventoryUI";
    public static final String PARENT_LOGIN = "ParentalControlsLogin";
    public static final String ENABLE_TIME = "EnableTimeLimit";
    public static final String INSTRUCT = "Instructions";
    public static final String INSTRUCT2 = "Instructions2";
    public static final String INSTRUCT3 = "Instructions3";
    public static final String INSTRUCT4 = "Instructions4";
    public static final String INSTRUCT5 = "Instructions5";
    public static final String INSTRUCT6 = "Instructions6";
    public static final String STATS = "Statistics";
    public static final String REVIVE = "Revive";

    /**
     * Initializes the game manager, sets up the window, and displays the main menu.
     * Initializes the inventory, parental controls, and screens for different game features.
     */
    public GameManager() {
        inventory = new Inventory();

        parentalControl = new ParentalControl();
        ParentalControlLoader.loadParentalControl("saves/parentalControl.txt", parentalControl);
        startSession();


        window = new JFrame("Timeless Tails");
        window.setSize(1000, 600);
        window.setResizable(false);
        window.setBackground(Color.black);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setUndecorated(true);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        window.add(container);

        // create default pet before initializing UI elements
        currentPet = new DefaultPet("default", this);


        MainMenuScreen mainMenu = new MainMenuScreen(this, inventory);
        SaveFileScreen saveFileScreen = new SaveFileScreen(this);
        SettingsScreen settingsScreen = new SettingsScreen(this);
        ParentalControlsScreen parentalControls = new ParentalControlsScreen(this);
        PetNameFurfrou petName = new PetNameFurfrou(this);
        PetNameTyrunt petNameTyrunt = new PetNameTyrunt(this);
        PetNameMagearna petNameMagearna = new PetNameMagearna(this);
        ChoosePet choosePet = new ChoosePet(this);
        GamePlayUI mainGamePlayFurfrou = new GamePlayUI(this, inventory);
        GamePlayUITyrunt mainGamePlayTyrunt = new GamePlayUITyrunt(this, inventory);
        GamePlayUIMagearna mainGamePlayMagearna = new GamePlayUIMagearna(this, inventory);

        InventoryUI inventoryScreen = new InventoryUI(this, inventory, mainGamePlayFurfrou);
        inventory.setInventoryUI(inventoryScreen);
        ParentalControlsLogin parentalLogin = new ParentalControlsLogin(this);
        EnableTimeLimit enableTimeLimit = new EnableTimeLimit(this);
        Instructions instructions = new Instructions(this);
        Instructions2 instructions2 = new Instructions2(this);
        Instructions3 instructions3 = new Instructions3(this);
        Instructions4 instructions4 = new Instructions4(this);
        Instructions5 instructions5 = new Instructions5(this);
        Instructions6 instructions6 = new Instructions6(this);
        Statistics statistics = new Statistics(this);
        Revive revive = new Revive(this);

        // adds the GIF sprite image change when the state changes on the pet
        // need to set it dynamically based on which pet was chosen
        currentPet.setListener(mainGamePlayFurfrou);

        container.add(mainMenu, MAIN_MENU);
        container.add(saveFileScreen, SAVE_FILE);
        container.add(settingsScreen, SETTINGS);
        container.add(parentalControls, PARENTAL_CONTROLS);
        container.add(petName, PET_NAME);
        container.add(petNameTyrunt, PET_NAME_TYRUNT);
        container.add(petNameMagearna, PET_NAME_MAGEARNA);
        container.add(choosePet, CHOOSE_PET);
        container.add(mainGamePlayFurfrou, MAIN_GAMEPLAY_FURFROU);
        container.add(mainGamePlayTyrunt, MAIN_GAMEPLAY_TYRUNT);
        container.add(mainGamePlayMagearna, MAIN_GAMEPLAY_MAGEARNA);
        container.add(inventoryScreen, INVENTORY_UI);
        container.add(parentalLogin, PARENT_LOGIN);
        container.add(enableTimeLimit, ENABLE_TIME);
        container.add(instructions, INSTRUCT);
        container.add(instructions2, INSTRUCT2);
        container.add(instructions3, INSTRUCT3);
        container.add(instructions4, INSTRUCT4);
        container.add(instructions5, INSTRUCT5);
        container.add(instructions6, INSTRUCT6);
        container.add(statistics, STATS);
        container.add(revive, REVIVE);

        window.setVisible(true);
        setUpKeyboardShortcuts();
        showMainMenu();
    }

    /**
     * Starts a new game session by recording the start time.
     */
    public void startSession() { 
        sessionStart = Instant.now();
    }

    /**
     * Ends the current game session.
     */
    public void endSession() {
     
    }

    /**
     * gets the parental control details
     * @return the parent control
     */
    public ParentalControl getParentalControl() {
        return parentalControl;
    }

    /**
     * Sets up keyboard shortcuts for the game.
     */
    private void setUpKeyboardShortcuts() {
        JRootPane rootPane = window.getRootPane();
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();
    
        inputMap.put(KeyStroke.getKeyStroke("control Q"), "openSettings");
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "quitGame");

        // universal actions, can be accessed from any screen (not just main gameplay screen)
    
        actionMap.put("openSettings", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                showSettingsScreen();
            }
        });
    
        actionMap.put("quitGame", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });
    }

    /**
     * Displays the main menu screen.
     */
    public void showMainMenu() {
        previousScreen = currentScreen;
        this.setCurrentSaveSlot(0);
        currentScreen = MAIN_MENU;
        cardLayout.show(container, MAIN_MENU);
    }

    /**
     * Displays the save file screen.
     */
    public void showSaveFileScreen() {
        previousScreen = currentScreen;
        currentScreen = SAVE_FILE;
        cardLayout.show(container, SAVE_FILE);
    }

    /**
     * Displays the setting screen.
     */
    public void showSettingsScreen() {
        previousScreen = currentScreen;
        currentScreen = SETTINGS;
        cardLayout.show(container, SETTINGS);
    }

    /**
     * Displays the parental controls screen.
     */
    public void showParentalControlsScreen(){
        previousScreen = currentScreen;
        currentScreen = PARENTAL_CONTROLS;
        cardLayout.show(container, PARENTAL_CONTROLS);
    }

    /**
     * Displays the pet naming screen for furfrou
     */
    public void showPetName(){
        previousScreen = currentScreen;
        currentScreen = PET_NAME;
        cardLayout.show(container, PET_NAME);
    }

    /**
     * Displays the pet naming screen for tyrunt
     */
    public void showPetNameTyrunt(){
        previousScreen = currentScreen;
        currentScreen = PET_NAME_TYRUNT;
        cardLayout.show(container, PET_NAME_TYRUNT);
    }

    /**
     * Displays the pet naming screen for magearna
     */
    public void showPetNameMagearna(){
        previousScreen = currentScreen;
        currentScreen = PET_NAME_MAGEARNA;
        cardLayout.show(container, PET_NAME_MAGEARNA);
    }

    /**
     * Displays the main play screen for furfrou
     */
    public void showMainGamePlayFurfrou() {
        if (currentPet == null) {
            return;
        }
    
        for (Component c : container.getComponents()) {
            if (c instanceof GamePlayUI) {
                container.remove(c);
                break;
            }
        }
        GamePlayUI gameplay = new GamePlayUI(this, inventory); 
        container.add(gameplay, MAIN_GAMEPLAY_FURFROU);
        
        previousScreen = currentScreen;
        currentScreen = MAIN_GAMEPLAY_FURFROU;
        cardLayout.show(container, MAIN_GAMEPLAY_FURFROU);
    }

    
    /**
     * Displays the main play screen for tyrunt
     */
    public void showMainGamePlayTyrunt(){
        if (currentPet == null) {
            return;
        }
    
        for (Component c : container.getComponents()) {
            if (c instanceof GamePlayUITyrunt) {
                container.remove(c);
                break;
            }
        }
        GamePlayUITyrunt gameplay = new GamePlayUITyrunt(this, inventory); 
        container.add(gameplay, MAIN_GAMEPLAY_TYRUNT);

        previousScreen = currentScreen;
        currentScreen = MAIN_GAMEPLAY_TYRUNT;
        cardLayout.show(container, MAIN_GAMEPLAY_TYRUNT);
    }

    /**
     * Displays the main play screen for magearna
     */
    public void showMainGamePlayMagearna(){
        if (currentPet == null) {
            return;
        }
    
        for (Component c : container.getComponents()) {
            if (c instanceof GamePlayUIMagearna) {
                container.remove(c);
                break;
            }
        }
        GamePlayUIMagearna gameplay = new GamePlayUIMagearna(this, inventory); 
        container.add(gameplay, MAIN_GAMEPLAY_MAGEARNA);

        previousScreen = currentScreen;
        currentScreen = MAIN_GAMEPLAY_MAGEARNA;
        cardLayout.show(container, MAIN_GAMEPLAY_MAGEARNA);
    }

    /**
     * Displays the pet selection screen
     */
    public void showChoosePet(){
        previousScreen = currentScreen;
        currentScreen = CHOOSE_PET;
        cardLayout.show(container, CHOOSE_PET);
    }

    /**
     * Displays the inventory screen
     */
    public void showInventoryScreen() {
        previousScreen = currentScreen;
        currentScreen = INVENTORY_UI;
        cardLayout.show(container, INVENTORY_UI);
    }

    /**
     * Displays the parental controls login screen
     */
    public void showParentalControlsLogin() {
        previousScreen = currentScreen;
        currentScreen = PARENT_LOGIN;
        cardLayout.show(container, PARENT_LOGIN);
    }

    /**
     * Displays the set time limits screen in parental controls
     */
    public void showEnableTimeControl() {
        previousScreen = currentScreen;
        currentScreen = ENABLE_TIME;
        cardLayout.show(container, ENABLE_TIME);
    }

    /**
     * Displays the first page of the instructions screen
     */
    public void showInstructions() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT;
        cardLayout.show(container, INSTRUCT);
    }

    /**
     * Displays the second page of the instructions screen
     */
    public void showInstructions2() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT2;
        cardLayout.show(container, INSTRUCT2);
    }

    /**
     * Displays the third page of the instructions screen
     */
    public void showInstructions3() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT3;
        cardLayout.show(container, INSTRUCT3);
    }

    /**
     * Displays the fourth page of the instructions screen
     */
    public void showInstructions4() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT4;
        cardLayout.show(container, INSTRUCT4);
    }

    /**
     * Displays the fifth page of the instructions screen
     */
    public void showInstructions5() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT5;
        cardLayout.show(container, INSTRUCT5);
    }

    /**
     * Displays the sixth page of the instructions screen
     */
    public void showInstructions6() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT6;
        cardLayout.show(container, INSTRUCT6);
    }

    /**
     * Displays the statistics screen in parental controls
     */
    public void showStatistics() {
        previousScreen = currentScreen;
        currentScreen = STATS;
        cardLayout.show(container, STATS);
    }

    /**
     * Displays the revive screen in parental controls
     */
    public void showRevive() {
        previousScreen = currentScreen;
        currentScreen = REVIVE;
        cardLayout.show(container, REVIVE);
    }

    /**
     * Displays the previous screen the user was on
     */
    public void previous() {
        if (previousScreen != null) {
            cardLayout.show(container, previousScreen);
            currentScreen = previousScreen;
        }
    }

    /**
     * Closes the game window
     */
    public void closeWindow() {
        endSession();
        String baseDir = "saves";
            File saveDir = new File(baseDir);
            if (!saveDir.exists()) {
                // Create the directory if it doesn't exist.
                saveDir.mkdirs();
            }

                String parentalControlFileName = baseDir + File.separator + "parentalControl" + ".txt";
                Instant sessionEnd = Instant.now();
                long sessionDuration = sessionEnd.toEpochMilli() - sessionStart.toEpochMilli();
                
                parentalControl.updateSessionTime(sessionDuration);
                System.out.println(parentalControl.getSessionCount());

                System.out.println(parentalControl.getLastSessionTime());

                ParentalControlSaver.saveParentalControl(parentalControlFileName, parentalControl);

        window.dispose();
        System.exit(0);
    }

    /**
     * Sets the current pet for the player.
     * @param pet the new pet object.
     */
    public void setCurrentPet(Pet pet) {
        this.currentPet = pet;
    }

    /**
     * Getter method for the current pet object.
     * @return the current pet object.
     */
    public Pet getCurrentPet() {
        return this.currentPet;
    }

    /**
     * Returns the player's inventory.
     * @return the player's inventory object.
     */
    public Inventory getCurrentInventory() {
        return this.inventory;
    }

    /**
     * Returns the current save slot index.
     * 
     * @return the current save slot index.
     */    
    public int getCurrentSaveSlot() {
        return currentSaveSlot;
    }

    /**
     * Sets the current save slot index.
     * 
     * @param slot the new save slot index.
     */
    public void setCurrentSaveSlot(int slot) {
        this.currentSaveSlot = slot;
    }

    /**
     * Returns the main game window.
     * 
     * @return the JFrame object representing the main game window.
     */
    public JFrame getWindow() {
        return window;
    }

     /**
     * Returns the current game variable flag.
     * 
     * @return the current state of the GMVariable flag.
     */
    public boolean getVariable() {
        return GMVariable;
    }

     /**
     * Sets the current game variable flag.
     * 
     * @param state the new state for the GMVariable flag.
     */
    public void setVariable(boolean state) {
        GMVariable = state;
    }

    /**
     * Returns the current screen name.
     * 
     * @return the name of the current screen.
     */
    public String getCurrentScreen() {
        return currentScreen;
    }
    

}


// gm.getCurrentScreen.isequal("")
