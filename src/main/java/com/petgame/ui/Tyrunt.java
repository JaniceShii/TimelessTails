package com.petgame.ui;
import com.petgame.game.GameManager;

/**
 * This class represents a pet of type Tyrunt in the game. 
 * It extends the {@code Pet} class and manages attributes like health, fullness, sleep, and happiness.
 * It also runs background threads to gradually decrease these stats over time.
 * 
 * @version 1.0
 * @author Meridith Shang
 */
public class Tyrunt extends Pet {

    /**
     * Constructs a new pet with default attributes.
     * 
     * @param name The name of the pet.
     * @param gm The {@code GameManager} instance used to manage the game state and interactions.
     */
    public Tyrunt(String name, GameManager gm) {
        super(name, gm);
        this.fullnessDecrease = 3;
        this.sleepDecrease = 1;
        this.healthCur = 60;
        this.healthMax = 120;
        this.sleepCur = 75;
        this.sleepMax = 150;
        this.fullnessCur = 40;
        this.fullnessMax = 80;
        this.petType = "tyrunt";

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
     * Constructs a new pet with specified attributes.
     * 
     * @param name The name of the pet.
     * @param points The points or score associated with the pet.
     * @param healthCur The current health value of the pet.
     * @param healthMax The maximum health value of the pet.
     * @param fullnessCur The current fullness value of the pet.
     * @param fullnessMax The maximum fullness value of the pet.
     * @param sleepCur The current sleep value of the pet.
     * @param state The current state of the pet (e.g., idle, sleeping, etc.).
     * @param sleepMax The maximum sleep value of the pet.
     * @param happinessCur The current happiness value of the pet.
     * @param happinessMax The maximum happiness value of the pet.
     * @param lastVet The timestamp of the last vet visit.
     * @param lastExercise The timestamp of the last exercise session.
     * @param lastPlay The timestamp of the last play session.
     * @param gm The {@code GameManager} instance used to manage the game state and interactions.
     */
    public Tyrunt(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, long lastVet, long lastExercise, long lastPlay, GameManager gm) {
        super(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        this.fullnessDecrease = 3;
        this.sleepDecrease = 1;
        this.petType = "tyrunt";
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
     * Retrieves the pet type.
     * 
     * @return the type of the pet, which is "Tyrunt".
     */
    @Override
    public String getPetType() {
        return "Tyrunt";
    }

    /**
     * Sets the pet type of this.
     * 
     * @param petType A string representing the new pet type.
     */
    @Override
    public void setPetType(String petType) {
        this.petType = petType;
    }
}
