package game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.Instant;
import javax.swing.*;
import ui.*;


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

    // testing
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

        // // !!!!!!!!!!!!! create default pet before initializing UI elements
        currentPet = new DefaultPet("default", this);


        MainMenuScreen mainMenu = new MainMenuScreen(this, inventory);
        SaveFileScreen saveFileScreen = new SaveFileScreen(this);
        SettingsScreen settingsScreen = new SettingsScreen(this);
        ParentalControlsScreen parentalControls = new ParentalControlsScreen(this);
        // testing 
        PetNameFurfrou petName = new PetNameFurfrou(this);
        PetNameTyrunt petNameTyrunt = new PetNameTyrunt(this);
        PetNameMagearna petNameMagearna = new PetNameMagearna(this);
        ChoosePet choosePet = new ChoosePet(this);
        GamePlayUI mainGamePlayFurfrou = new GamePlayUI(this, inventory);
        GamePlayUITyrunt mainGamePlayTyrunt = new GamePlayUITyrunt(this, inventory);
        GamePlayUIMagearna mainGamePlayMagearna = new GamePlayUIMagearna(this, inventory);

        InventoryUI inventoryScreen = new InventoryUI(this, inventory, mainGamePlayFurfrou);
        inventory.setInventoryUI(inventoryScreen);

        // !! potential issue is that when we have multiple pet UI then have to figure out 

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

        // !!!!!!!!!!!! adds the GIF sprite image change when the state changes on the pet
        // need to set it dynamically based on which pet was chosen
        currentPet.setListener(mainGamePlayFurfrou);

        container.add(mainMenu, MAIN_MENU);
        container.add(saveFileScreen, SAVE_FILE);
        container.add(settingsScreen, SETTINGS);
        container.add(parentalControls, PARENTAL_CONTROLS);
        // testing
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

    public void startSession() { 
        sessionStart = Instant.now();
    }

    public void endSession() {
     
    }

    public ParentalControl getParentalControl() {
        return parentalControl;
    }

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

    public void showMainMenu() {
        previousScreen = currentScreen;
        this.setCurrentSaveSlot(0);
        currentScreen = MAIN_MENU;
        cardLayout.show(container, MAIN_MENU);
    }

    public void showSaveFileScreen() {
        previousScreen = currentScreen;
        currentScreen = SAVE_FILE;
        cardLayout.show(container, SAVE_FILE);
    }

    public void showSettingsScreen() {
        previousScreen = currentScreen;
        currentScreen = SETTINGS;
        cardLayout.show(container, SETTINGS);
    }
    
    public void showParentalControlsScreen(){
        previousScreen = currentScreen;
        currentScreen = PARENTAL_CONTROLS;
        cardLayout.show(container, PARENTAL_CONTROLS);
    }
    // TESTING
    public void showPetName(){
        previousScreen = currentScreen;
        currentScreen = PET_NAME;
        cardLayout.show(container, PET_NAME);
    }
    public void showPetNameTyrunt(){
        previousScreen = currentScreen;
        currentScreen = PET_NAME_TYRUNT;
        cardLayout.show(container, PET_NAME_TYRUNT);
    }

    public void showPetNameMagearna(){
        previousScreen = currentScreen;
        currentScreen = PET_NAME_MAGEARNA;
        cardLayout.show(container, PET_NAME_MAGEARNA);
    }

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
    public void showChoosePet(){
        previousScreen = currentScreen;
        currentScreen = CHOOSE_PET;
        cardLayout.show(container, CHOOSE_PET);
    }

    public void showInventoryScreen() {
        previousScreen = currentScreen;
        currentScreen = INVENTORY_UI;
        cardLayout.show(container, INVENTORY_UI);
    }

    public void showParentalControlsLogin() {
        previousScreen = currentScreen;
        currentScreen = PARENT_LOGIN;
        cardLayout.show(container, PARENT_LOGIN);
    }

    public void showEnableTimeControl() {
        previousScreen = currentScreen;
        currentScreen = ENABLE_TIME;
        cardLayout.show(container, ENABLE_TIME);
    }

    public void showInstructions() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT;
        cardLayout.show(container, INSTRUCT);
    }

    public void showInstructions2() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT2;
        cardLayout.show(container, INSTRUCT2);
    }

    public void showInstructions3() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT3;
        cardLayout.show(container, INSTRUCT3);
    }

    public void showInstructions4() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT4;
        cardLayout.show(container, INSTRUCT4);
    }

    public void showInstructions5() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT5;
        cardLayout.show(container, INSTRUCT5);
    }

    public void showInstructions6() {
        previousScreen = currentScreen;
        currentScreen = INSTRUCT6;
        cardLayout.show(container, INSTRUCT6);
    }

    public void showStatistics() {
        previousScreen = currentScreen;
        currentScreen = STATS;
        cardLayout.show(container, STATS);
    }

    public void showRevive() {
        previousScreen = currentScreen;
        currentScreen = REVIVE;
        cardLayout.show(container, REVIVE);
    }

    public void previous() {
        if (previousScreen != null) {
            cardLayout.show(container, previousScreen);
            currentScreen = previousScreen;
        }
    }

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

    public void setCurrentPet(Pet pet) {
        this.currentPet = pet;
    }

    // Getter method to get the pet object
    public Pet getCurrentPet() {
        return this.currentPet;
    }

    public Inventory getCurrentInventory() {
        return this.inventory;
    }

    // Getter for the current save slot.
    public int getCurrentSaveSlot() {
        return currentSaveSlot;
    }

    // Setter for the current save slot.
    public void setCurrentSaveSlot(int slot) {
        this.currentSaveSlot = slot;
    }

    public JFrame getWindow() {
        return window;
    }

    public boolean getVariable() {
        return GMVariable;
    }

    public void setVariable(boolean state) {
        GMVariable = state;
    }

    public String getCurrentScreen() {
        return currentScreen;
    }
    

}


// gm.getCurrentScreen.isequal("")