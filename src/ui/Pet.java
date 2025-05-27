package ui;
import game.GameManager;
import java.io.File;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public abstract class Pet {
    protected String name;
    protected int points, healthCur, healthMax, fullnessCur, fullnessMax, sleepMax, sleepCur, happinessMax, happinessCur, happinessDecrease, fullnessDecrease, sleepDecrease, healthDecrease;
    protected String state, itemMsg, petType;
    protected long startTime, lastVet, lastPlay, lastExercise;
    protected GameManager gm;

    // checks the state to redraw sprite depending on state of pet
    private PetStateListener listener;
    

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

    public String getName() { return name; }
    public void updateName(String name) { this.name = name; }
    public void decreaseHealth(int decrease) { healthCur = Math.max(0, healthCur - decrease); }
    public int getHealth() { return healthCur; }
    public int getMaxHealth() { return healthMax; }
    public float getHealthPercentage() { return (float) healthCur / healthMax; }
    public void decreaseFullness(int decrease) { fullnessCur = Math.max(0, fullnessCur - decrease); }
    public int getFullness() { return fullnessCur; }
    public int getMaxFullness() { return fullnessMax; }
    public float getFullnessPercentage() { return (float) fullnessCur / fullnessMax; }
    public void decreaseSleep(int decrease) { sleepCur = Math.max(0, sleepCur - decrease); }
    public int getSleep() { return sleepCur; }
    public int getMaxSleep() { return sleepMax; }
    public float getSleepPercentage() { return (float) sleepCur / sleepMax; }
    public int getHappiness() { return happinessCur; }
    public int getMaxHappiness() { return happinessMax; }
    public float getHappinessPercentage() { return (float) happinessCur / happinessMax; }
    public void decreaseHapiness(int decrease) { happinessCur = Math.max(0, happinessCur - decrease); }
    public int getPoints() { return points; }
    public void setName(String name) { this.name = name; }
    public String getPetType() { return petType; }
    public void setPetType(String petType) { this.petType = petType; }
    public String getState() { return this.state; }
    public long getLastVet() { return lastVet; }
    public long getLastPlay() { return lastPlay; }
    public long getLastExercise() { return lastExercise; }
    public String getItemMessage() { return itemMsg; }
    public void setPoints(int points) { this.points = points; }
    public void setHealthCur(int healthCur) { this.healthCur = healthCur; }
    public void setHealthMax(int healthMax) { this.healthMax = healthMax; }
    public void setFullnessCur(int fullnessCur) { this.fullnessCur = fullnessCur; }
    public void setFullnessMax(int fullnessMax) { this.fullnessMax = fullnessMax; }
    public void setSleepCur(int sleepCur) { this.sleepCur = sleepCur; }
    public void setSleepMax(int sleepMax) { this.sleepMax = sleepMax; }
    public void setHappinessCur(int happinessCur) { this.happinessCur = happinessCur; }
    public void setHappinessMax(int happinessMax) { this.happinessMax = happinessMax; }
    public void setState(String state) { this.state = state; }
    public void setLastVet(long lastVet) { this.lastVet = lastVet; }
    public void setLastPlay(long lastPlay) { this.lastPlay = lastPlay; }
    public void setLastExercise(long lastExercise) { this.lastExercise = lastExercise; }
    public void setListener(PetStateListener listener) { this.listener = listener; }

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
                    //sdfsadfjsahlfsadfasdfasdfasdfasdfa
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

    // change sleep to make pet sleep for xx time until sleep is full (locks pet into sleep)
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

    public int play() {
        // make command go onto cooldown!!!!!!
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