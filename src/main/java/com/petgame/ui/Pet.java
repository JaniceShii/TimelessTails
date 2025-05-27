package com.petgame.ui;
import java.io.File;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.petgame.game.GameManager;

/**
 * Abstract base class representing a virtual pet with various attributes and behaviors.
 * Handles the pet's name, points, health, fullness, sleep, happiness, sprites, and states.
 * Provides functionality for managing pet stats over time.
 * 
 * @version 1.0
 * @author Advaith Thakur
 */
public abstract class Pet {
    protected String name;
    protected int points, healthCur, healthMax, fullnessCur, fullnessMax, sleepMax, sleepCur, happinessMax, happinessCur, happinessDecrease, fullnessDecrease, sleepDecrease, healthDecrease;
    protected String state, itemMsg, petType;
    protected long startTime, lastVet, lastPlay, lastExercise;
    protected GameManager gm;

    /**
     * Sets the PetStateListener that will handle state change events.
     *
     * @param listener The PetStateListener to be notified of state changes.
     */ private PetStateListener listener;
    
    
    public Pet(String name, GameManager gm) {
        this.name = name;
        this.points = 0;
        this.healthCur = 50;
        this.healthMax = 100;
        this.fullnessCur = 50;
        this.fullnessMax = 100;
        this.sleepCur = 50;
        this.sleepMax = 100;
        this.happinessCur = 50;
        this.happinessMax = 100;
        this.startTime = System.currentTimeMillis();
        this.state = "normal";
        this.lastVet = 0;
        this.lastPlay = 0;
        this.lastExercise = 0;
        this.gm = gm;
        this.happinessDecrease = 2;
        this.fullnessDecrease = 2;
        this.sleepDecrease = 2;
        this.healthDecrease = 1;

    }

    /**
     * Constructs a new Pet object with specified stats and attributes.
     *
     * @param name          The name of the pet.
     * @param points        The current point score of the pet.
     * @param healthCur     The current health value of the pet.
     * @param healthMax     The maximum health value of the pet.
     * @param fullnessCur   The current fullness value of the pet.
     * @param fullnessMax   The maximum fullness value of the pet.
     * @param sleepCur      The current sleep value of the pet.
     * @param state         The current state of the pet (e.g., "normal", "angry", "hungry").
     * @param sleepMax      The maximum sleep value of the pet.
     * @param happinessCur  The current happiness value of the pet.
     * @param happinessMax  The maximum happiness value of the pet.
     * @param lastVet       The last timestamp the pet visited the vet.
     * @param lastExercise  The last timestamp the pet exercised.
     * @param lastPlay      The last timestamp the pet played.
     * @param gm            The GameManager instance managing the pet's lifecycle and state.
     */
    public Pet(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, Long lastVet, Long lastExercise, Long lastPlay, GameManager gm) {
        this.name = name;
        this.points = points;
        this.healthCur = healthCur;
        this.healthMax = healthMax;
        this.fullnessCur = fullnessCur;
        this.fullnessMax = fullnessMax;
        this.sleepCur = sleepCur;
        this.sleepMax = sleepMax;
        this.happinessCur = happinessCur;
        this.happinessMax = happinessMax;
        this.startTime = System.currentTimeMillis();
        this.state = state;
        this.lastVet = lastVet;
        this.lastPlay = lastPlay;
        this.lastExercise = lastExercise;
        this.gm = gm;
        this.happinessDecrease = 2;
        this.fullnessDecrease = 2;
        this.sleepDecrease = 2;
        this.healthDecrease = 1;
    }

    /**
     * Retrieves the name of the pet.
     *
     * @return The name of the pet.
     */
    public String getName() { return name; }
    /**
     * Updates the name of the pet.
     *
     * @param name The new name to be assigned to the pet.
     */
    public void updateName(String name) { this.name = name; }
    /**
     * Decreases the current health of the pet by a specified amount.
     *
     * @param decrease The amount of health to decrease. Ensures health does not drop below 0.
     */
    public void decreaseHealth(int decrease) { healthCur = Math.max(0, healthCur - decrease); }
    /**
     * Retrieves the current health of the pet.
     *
     * @return The current health value.
     */
    public int getHealth() { return healthCur; }
    /**
     * Retrieves the maximum health of the pet.
     *
     * @return The maximum health value.
     */
    public int getMaxHealth() { return healthMax; }
    /**
     * Retrieves the current health percentage of the pet.
     *
     * @return The current health as a percentage of the maximum health.
     */
    public float getHealthPercentage() { return (float) healthCur / healthMax; }
    /**
     * Decreases the current fullness of the pet by a specified amount.
     *
     * @param decrease The amount of fullness to decrease. Ensures fullness does not drop below 0.
     */
    public void decreaseFullness(int decrease) { fullnessCur = Math.max(0, fullnessCur - decrease); }
    /**
     * Retrieves the current fullness of the pet.
     *
     * @return The current fullness value.
     */
    public int getFullness() { return fullnessCur; }
    /**
     * Retrieves the maximum fullness of the pet.
     *
     * @return The maximum fullness value.
     */
    public int getMaxFullness() { return fullnessMax; }
    /**
     * Retrieves the current fullness percentage of the pet.
     *
     * @return The current fullness as a percentage of the maximum fullness.
     */
    public float getFullnessPercentage() { return (float) fullnessCur / fullnessMax; }
    /**
     * Decreases the current sleep of the pet by a specified amount.
     *
     * @param decrease The amount of sleep to decrease. Ensures sleep does not drop below 0.
     */
    public void decreaseSleep(int decrease) { sleepCur = Math.max(0, sleepCur - decrease); }
    /**
     * Retrieves the current sleep of the pet.
     *
     * @return The current sleep value.
     */
    public int getSleep() { return sleepCur; }
    /**
     * Retrieves the maximum sleep of the pet.
     *
     * @return The maximum sleep value.
     */
    public int getMaxSleep() { return sleepMax; }
    /**
     * Retrieves the current sleep percentage of the pet.
     *
     * @return The current sleep as a percentage of the maximum sleep.
     */
    public float getSleepPercentage() { return (float) sleepCur / sleepMax; }
    /**
     * Gets the amount of happiness the pet currently has
     *
     * @return The amount of happiness the pet currently has
     */
    public int getHappiness() { return happinessCur; }
    /**
     * Retrieves the maximum happiness of the pet.
     *
     * @return The maximum happiness value.
     */
    public int getMaxHappiness() { return happinessMax; }
    /**
     * Retrieves the current happiness percentage of the pet.
     *
     * @return The current happiness as a percentage of the maximum happiness.
     */
    public float getHappinessPercentage() { return (float) happinessCur / happinessMax; }
    /**
     * Decreases the current happiness of the pet by a specified amount.
     *
     * @param decrease The amount of happiness to decrease. Ensures happiness does not drop below 0.
     */
    public void decreaseHapiness(int decrease) { happinessCur = Math.max(0, happinessCur - decrease); }
    /**
     * Retrieves the current points of the pet.
     *
     * @return The current point score of the pet.
     */
    public int getPoints() { return points; }
    /**
     * Sets the name of the pet.
     *
     * @param name The new name to be assigned to the pet.
     */
    public void setName(String name) { this.name = name; }
    /**
     * Retrieves the type of the pet.
     *
     * @return The type of the pet (e.g., "dog", "cat").
     */
    public String getPetType() { return petType; }
     /**
     * Sets the type of the pet.
     *
     * @param petType The new type to be assigned to the pet.
     */
    public void setPetType(String petType) { this.petType = petType; }
    /**
     * Retrieves the current state of the pet.
     *
     * @return The current state of the pet (e.g., "normal", "angry", "hungry").
     */
    public String getState() { return this.state; }
    /**
     * Retrieves the last time the pet visited the vet.
     *
     * @return The timestamp of the last vet visit.
     */
    public long getLastVet() { return lastVet; }
    /**
     * Retrieves the last time the pet played.
     *
     * @return The timestamp of the last play session.
     */
    public long getLastPlay() { return lastPlay; }
    /**
     * Retrieves the last time the pet exercised.
     *
     * @return The timestamp of the last exercise session.
     */
    public long getLastExercise() { return lastExercise; }
    /**
     * Retrieves the message indicating the item given to the pet.
     *
     * @return The message indicating the item given to the pet.
     */
    public String getItemMessage() { return itemMsg; }
    /**
     * Sets the current points of the pet.
     *
     * @param points The new points value to be assigned.
     */
    public void setPoints(int points) { this.points = points; }
    /**
     * Sets the current health of the pet.
     *
     * @param healthCur The new current health value to be assigned.
     */
    public void setHealthCur(int healthCur) { this.healthCur = healthCur; }
    /**
     * Sets the maximum health of the pet.
     *
     * @param healthMax The new maximum health value to be assigned.
     */
    public void setHealthMax(int healthMax) { this.healthMax = healthMax; }
    /**
     * Sets the current fullness of the pet.
     *
     * @param fullnessCur The new current fullness value to be assigned.
     */
    public void setFullnessCur(int fullnessCur) { this.fullnessCur = fullnessCur; }
    /**
     * Sets the maximum fullness of the pet.
     *
     * @param fullnessMax The new maximum fullness value to be assigned.
     */
    public void setFullnessMax(int fullnessMax) { this.fullnessMax = fullnessMax; }
     /**
     * Sets the current sleep of the pet.
     *
     * @param sleepCur The new current sleep value to be assigned.
     */
    public void setSleepCur(int sleepCur) { this.sleepCur = sleepCur; }
     /**
     * Sets the maximum sleep of the pet.
     *
     * @param sleepMax The new maximum sleep value to be assigned.
     */
    public void setSleepMax(int sleepMax) { this.sleepMax = sleepMax; }
    /**
     * Sets the current happiness of the pet.
     *
     * @param happinessCur The new current happiness value to be assigned.
     */
    public void setHappinessCur(int happinessCur) { this.happinessCur = happinessCur; }
    /**
     * Sets the maximum happiness of the pet.
     *
     * @param happinessMax The new maximum happiness value to be assigned.
     */
    public void setHappinessMax(int happinessMax) { this.happinessMax = happinessMax; }
     /**
     * Sets the state of the pet.
     *
     * @param state The new state to be assigned to the pet.
     */
    public void setState(String state) { this.state = state; }
    /**
     * Sets the last time the pet visited the vet.
     *
     * @param lastVet The new timestamp of the last vet visit to be assigned.
     */
    public void setLastVet(long lastVet) { this.lastVet = lastVet; }
     /**
     * Sets the last time the pet played.
     *
     * @param lastPlay The new timestamp of the last play session to be assigned.
     */
    public void setLastPlay(long lastPlay) { this.lastPlay = lastPlay; }
    /**
     * Sets the last time the pet exercised.
     *
     * @param lastExercise The new timestamp of the last exercise session to be assigned.
     */
    public void setLastExercise(long lastExercise) { this.lastExercise = lastExercise; }
    /**
     * Sets the PetStateListener that will handle state change events.
     *
     * @param listener The PetStateListener to be notified of state changes.
     */
    public void setListener(PetStateListener listener) { this.listener = listener; }

    /**
     * Decrease pet stats based on time from game open to game close 
     * @param statType The stat to be decreased, can be "health", "fullness", "happiness", or "sleep"
     */
    public void decreaseStats(String statType) {
        long startTime = System.currentTimeMillis();
        if (!this.getState().equals("dead")) {
            if (statType.equals("health")) {
                while (healthCur > 0 && this.getState().equals("hungry")) {
                    healthCur = Math.max(healthCur - healthDecrease, 0);
                    try {
                        Thread.sleep(10000);  // Sleep for 100 milliseconds before checking again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Run the task to decrease the stat every 5 seconds
                while (true) {  
                    // Decrease the stat every 5 seconds (5000 milliseconds)
                        switch (statType) {
                            case "sleep":
                                if (sleepCur > 0) {
                                    if (!this.getState().equals("sleep")) {
                                        sleepCur = Math.max(sleepCur - sleepDecrease, 0);
                                    }    
                                }
                                break;
    
                            case "fullness":
                                if (fullnessCur > 0) {
                                    fullnessCur = Math.max(fullnessCur - fullnessDecrease, 0);
                                }
                                break;
    
                            case "happiness":
                                if (happinessCur > 0) {
                                    happinessCur = Math.max(happinessCur - happinessDecrease, 0);
                                }
                                break;
    
                            default:
                                return;  // Exit if an unknown stat type is provided
                        }
    
                        // Reset the start time to current time for the next cycle
                        startTime = System.currentTimeMillis();
    
                    // Sleep for a short period to avoid busy-waiting and reduce CPU usage
                    try {
                        Thread.sleep(10000);  // Sleep for 5000 milliseconds before checking again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Checks the state and decreases the specified stat type over time based on the pet's current state.
     *
     */
    public void checkState() {
        // variable to keep track of the GIF file names depending on state of pet
        String GIFFileName = "Idle.gif"; 
        String portraitName = "normal.png";

        long startTime = System.currentTimeMillis();
        while (true) { 
            long elapsedTime = System.currentTimeMillis() - startTime; // Correct calculation of elapsed time
    
            // Check the elapsed time every 1 second (1000 milliseconds)
            if (elapsedTime >= 1000) {
                if (healthCur <= 0) {
                    // Pet dies, change state to "dead"
                    state = "dead";
                    portraitName = "dead.png";
                    GIFFileName = "Dead.png";
                    points -= 100;
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
                            JOptionPane.showMessageDialog(null, "No available save slots.", "Save Error", JOptionPane.ERROR_MESSAGE);
                            gm.showMainMenu();
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
                    gm.startSession();
                    
                    JOptionPane.showMessageDialog(null, 
                        "Save Successful. Game saved to slot " + slot);
                    gm.showMainMenu();
                    gm.setCurrentSaveSlot(0);
                    // make it so that the file saves upon exit, and make it so that you cannot load a dead pet game file

                    break;

                } else if (state.equals("sleep")) {
                    portraitName = "sleep.png";
                    GIFFileName = "Sleep.gif";

                } else if (sleepCur <= 0) {
                    healthCur -= 25;
                    happinessDecrease = 2;
                    portraitName = "sleep.png";
                    GIFFileName = "Sleep.gif";
                    this.sleep();
                    points -= 25;
                // doesnt work properly -- switches back immediately after value goes above zero
                } else if (happinessCur <= 0) {
                    // Pet is angry, change state to "angry"
                    // make it so that they only exit this state if happiness is above 1/2 of happinessMax
                    state = "angry";
                    happinessDecrease = 2;
                    portraitName = "angry.png";
                    GIFFileName = "Angry.gif";
                    points -= 25;
                } else if (happinessCur > 0 && happinessCur < (happinessMax / 2) && state.equals("angry")) {
                    // If happiness goes above 0 but is still below half, stay in "angry" state
                    GIFFileName = "Angry.gif";

                } else if (happinessCur >= (happinessMax / 2) && state.equals("angry")) {
                    // Transition back to "normal" if happiness is above half the max
                    state = "normal";
                    GIFFileName = "Idle.gif";
                    happinessDecrease = 2;
                } else if (fullnessCur <= 0) {
                    if (!state.equals("hungry")) { // Avoid starting multiple threads if already hungry
                        state = "hungry";
                        portraitName = "hungry.png";
                        GIFFileName = "Hurt.gif";
                        new Thread(() -> {
                            if (this.getFullness() <= 0) {
                                happinessDecrease = 5;
                                this.decreaseStats("health");
                            }
                        }).start();
                        points -= 25;
                    }
                } else {
                    // Normal state, decrease happiness normally
                    state = "normal";
                    portraitName = "normal.png";
                    GIFFileName = "Idle.gif";
                    happinessDecrease = 2;
                }

                if (listener != null) {
                    listener.onStateChanged(GIFFileName, portraitName);
                }
                
                startTime = System.currentTimeMillis();
            }
            // Start the timer to check the state every 1000 milliseconds (1 second)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
    }

    /**
     * Makes the pet sleep until its sleep is fully restored, locking it into a "sleep" state during the process.
     * 
     * <p>This method changes the pet's state to "sleep" and gradually increases its sleep meter until it reaches the maximum value.
     * While the pet is sleeping, it cannot perform any other actions. Points are awarded based on the duration of sleep completed.</p>
     * 
     * <p>If the pet is dead, the method returns -1. If the pet is in a restricted state (e.g., angry or already sleeping), it returns -2.</p>
     * 
     * @return The amount of sleep gained if successful. Returns:
     *         <ul>
     *           <li>A positive integer representing the amount of sleep gained.</li>
     *           <li>-1 if the pet is dead.</li>
     *           <li>-2 if the pet is in a restricted state (e.g., "angry" or "sleep").</li>
     *         </ul>
     */
    public int sleep() {
        if (!state.equals("dead") && !state.equals("angry") && !state.equals("sleep")) {
            String prevState = state;
            state = "sleep";
            int initSleep = sleepCur;

            new Thread(() -> {
                SwingUtilities.invokeLater(() -> {
                    if (listener != null) {
                        listener.onStateChanged("Sleep.gif", "sleep.png");
                    }
                });

                while (sleepCur < sleepMax) {
                    sleepCur += 1;

                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                points += (sleepCur - initSleep);
                state = prevState;

                SwingUtilities.invokeLater(() -> {
                    if (listener != null) {
                        listener.onStateChanged("Idle.gif", "normal.png");
                    }
                });
            }).start();

            return sleepCur - initSleep;
        } else if (state.equals("dead")) {
            // make it so that if it doen't execute, print state instead of returning int to indicate state
            return -1;
        } else {
            return -2;
        }
    }

    /**
     * Initiates a play session with the pet, increasing its happiness if successful.
     * 
     * <p>This method checks if a cooldown period has passed since the last play session. If so, the pet's happiness increases
     * by a fixed amount, and points are awarded. If the cooldown is not yet over, the method returns -3.</p>
     * 
     * <p>If the pet is dead, the method returns -1.</p>
     * 
     * @return The amount of happiness gained if successful. Returns:
     *         <ul>
     *           <li>A positive integer representing the amount of happiness gained.</li>
     *           <li>-1 if the pet is dead.</li>
     *           <li>-3 if the command is on cooldown.</li>
     *         </ul>
     */
    public int play() {
        // make command go onto cooldown
        if ((System.currentTimeMillis() - lastPlay) > 1200000) {
            if (!state.equals("dead") && !state.equals("sleep")) {
                int initHappiness = happinessCur;
                MainMenu menu = new MainMenu(gm.getWindow(), this.getPetType());
                menu.setVisible(true);
                happinessCur = Math.min(happinessMax, happinessCur + 45);
                points += 45;
                lastPlay = System.currentTimeMillis();
                return (happinessCur - initHappiness); // return the amount of happiness gained
            } else {
                // pet is dead
                return -1;
            }
        } else {
            return -3; // on cooldown
        }
    }

    /**
     * Takes the pet to the vet to restore health, with a cooldown period of 20 minutes between visits.
     * 
     * <p>This method increases the pet's health by a fixed amount. Points are deducted as a cost for the vet visit.
     * If the cooldown period has not passed, the method returns -3. If the pet is dead or angry, appropriate status codes are returned.</p>
     * 
     * @return The amount of health gained if successful. Returns:
     *         <ul>
     *           <li>A positive integer representing the amount of health gained.</li>
     *           <li>-1 if the pet is dead.</li>
     *           <li>-2 if the pet is angry.</li>
     *           <li>-3 if the command is on cooldown.</li>
     *         </ul>
     */
    public int takeVet() {
        // make command go onto cooldown !!!
        // 20 minutes cooldown
        if (System.currentTimeMillis() - lastVet > 1200000) {
            if (!state.equals("dead") && !state.equals("sleep") && !state.equals("angry")) {
                int initHealth = healthCur;
                healthCur = Math.min(healthMax, healthCur + 20);
                points -= 10;
                lastVet = System.currentTimeMillis();
                return healthCur - initHealth; // return the amount of health gained
            } else if (healthCur == 0) {
                return -1; // -1 = dead
            } else {
                return -2; // -2 = angry
            }
        } else {
            return -3; // on cooldown 
        }
    }

    /**
     * Gives an item to the pet to increase its fullness and happiness.
     * 
     * <p>This method applies the effects of an item to the pet. If the item is food, the pet must not be in an angry state.
     * Otherwise, the pet's fullness and happiness are updated based on the item's properties. Points are also awarded.</p>
     * 
     * <p>If the pet is dead, the method returns -1. If the pet is angry and the item is food, it returns -2.</p>
     * 
     * @param item The item to be given to the pet.
     * @return Returns:
     *         <ul>
     *           <li>0 if the item is successfully applied.</li>
     *           <li>-1 if the pet is dead.</li>
     *           <li>-2 if the pet is angry and the item is food.</li>
     *         </ul>
     */
    public int giveItem(Item item) {
        // called within the actual inventory part
        if (!state.equals("dead") && !state.equals("sleep")) {
            if (item.getType().equals("food")) {
                if (!state.equals("angry")) {
                    fullnessCur = Math.min(fullnessMax, fullnessCur + item.getFullness());
                    happinessCur = Math.min(happinessMax, happinessCur + item.getHappy());
                    points += item.getFullness() + item.getHappy();
                    return 0;
                } else {
                    return -2; // pet is angry and can't be fed
                }
            } else {
                fullnessCur = Math.min(fullnessMax, fullnessCur + item.getFullness());
                happinessCur = Math.min(happinessMax, happinessCur + item.getHappy());
                points += item.getFullness() + item.getHappy();
                return 0;
            }
        } else {
            return -1; // pet is dead
        }
    }

    /**
     * Exercises the pet, improving its health but reducing fullness and sleepiness.
     * 
     * <p>This method improves the pet's health and awards points, but decreases fullness and sleepiness by fixed amounts.
     * It also randomly awards an item from a predefined list of items, which is added to the inventory.</p>
     * 
     * <p>If the cooldown period of 20 minutes has not passed, the method returns -3. If the pet is dead or angry, appropriate status codes are returned.</p>
     * 
     * @param inv The inventory to which the randomly awarded item will be added.
     * @return The amount of health gained if successful. Returns:
     *         <ul>
     *           <li>A positive integer representing the amount of health gained.</li>
     *           <li>-1 if the pet is dead.</li>
     *           <li>-2 if the pet is angry.</li>
     *           <li>-3 if the command is on cooldown.</li>
     *         </ul>
     */
    public int exercise(Inventory inv) {
        Inventory inventory = inv;
        if (System.currentTimeMillis() - lastExercise > 1200000) {
            if (!state.equals("dead") && !state.equals("angry") && !state.equals("sleep")) {
                int initHealth = healthCur;
                int initFullness = fullnessCur;
                int initSleep = sleepCur;
                healthCur = Math.min(healthMax, healthCur + 25);
                sleepCur = Math.max(0, sleepCur - 10);
                fullnessCur = Math.max(0, fullnessCur - 10);
                points += 25;

                // add random item to the inventory
                // uses randomized int passed to the inventory to determine which item gets added
                Random random = new Random();
                int randInt = random.nextInt(9);
                String itemNameString = "";
                inventory.addRandItem(randInt);

                switch (randInt) {
                    case 0:
                        itemNameString = "Berry";
                        break;    
                    case 1:
                        itemNameString = "Ice Cream";
                        break;   
                    case 2:
                        itemNameString = "Curry";
                        break;
                    case 3:
                        itemNameString = "Poffin";
                        break;
                    case 4:
                        itemNameString = "Flower";
                        break;    
                    case 5:
                        itemNameString = "Ball";
                        break;
                    case 6:
                        itemNameString = "Comb";
                        break;    
                    case 7:
                        itemNameString = "Gear";
                        break;
                    case 8:
                        itemNameString = "Bone";
                        break;
                    case 9:
                        itemNameString = "Poke Puff";
                        break;
                    default:
                        break;
                }

                // add msg that an item was picked up
                itemMsg = inventory.getMessage(name);
                
                lastExercise = System.currentTimeMillis();
                return (healthCur - initHealth);
            } else if (healthCur == 0) {
                return -1; //pet is dead
            } else {
                // pet is angry
                return -2;
            }
        } else {
            return -3; // on cooldown
        }
    }
}