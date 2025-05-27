package com.petgame.ui;
import com.petgame.game.GameManager;

/**
 * Represents the Magearna pet, a type of Pet with specific stat parameters and behaviors.
 * The Magearna pet decreases its happiness, sleep, and fullness over time through background threads.
 * 
 * Extends the Pet class and overrides specific methods to provide Magearna-specific functionality.
 * 
 * @version 1.0
 * @author Meridith Shang
 */
public class Magearna extends Pet {

    /**
     * Constructs a new Magearna instance with the given name and GameManager instance.
     * Initializes the pet's attributes and starts background threads for stats.
     *
     * @param name The name of the pet.
     * @param gm The GameManager instance managing the game flow.
     */
    public Magearna(String name, GameManager gm) {
        super(name, gm);
        this.sleepDecrease = 3;
        this.happinessDecrease = 1;
        this.sleepMax = 80;
        this.sleepCur = 40;
        this.healthMax = 90;
        this.healthCur = 45;
        this.happinessCur = 75;
        this.happinessMax = 150;
        this.petType = "Magearna";

        // These threads run in the background
        new Thread(() -> {
            this.decreaseStats("happiness");
        }).start();
        new Thread(() -> {
            this.decreaseStats("sleep");  
        }).start();
        new Thread(() -> {
            this.decreaseStats("fullness");
        }).start();
        new Thread(() -> {
            this.checkState();
        }).start();
    }

    /**
     * Constructs a new Magearna instance with detailed attributes.
     *
     * @param name The name of the pet.
     * @param points The points scored by the pet.
     * @param healthCur The current health of the pet.
     * @param healthMax The maximum health of the pet.
     * @param fullnessCur The current fullness level of the pet.
     * @param fullnessMax The maximum fullness level of the pet.
     * @param sleepCur The current sleep level of the pet.
     * @param state The current state of the pet.
     * @param sleepMax The maximum sleep level of the pet.
     * @param happinessCur The current happiness level of the pet.
     * @param happinessMax The maximum happiness level of the pet.
     * @param lastVet The timestamp of the last vet visit.
     * @param lastExercise The timestamp of the last exercise session.
     * @param lastPlay The timestamp of the last play session.
     * @param gm The GameManager instance managing the game flow.
     */
    public Magearna(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, Long lastVet, Long lastExercise, Long lastPlay, GameManager gm) {
        super(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        this.sleepDecrease = 3;
        this.happinessDecrease = 1;
        this.petType = "Magearna";

        // These threads run in the background
        new Thread(() -> {
            this.decreaseStats("happiness");
        }).start();
        new Thread(() -> {
            this.decreaseStats("sleep");  
        }).start();
        new Thread(() -> {
            this.decreaseStats("fullness");
        }).start();
        new Thread(() -> {
            this.checkState();
        }).start();
    }

    /**
     * Retrieves the type of the pet.
     * 
     * @return A string representing the type of the pet, which is "Magearna".
     */
    @Override
    public String getPetType() {
        return "Magearna";
    }

    /**
     * Sets the type of the pet.
     * 
     * @param petType The type to set for the pet.
     */
    @Override
    public void setPetType(String petType) {
        this.petType = petType;
    }

}
