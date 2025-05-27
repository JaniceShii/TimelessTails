package com.petgame.ui;

import com.petgame.game.GameManager;

/**
 * The Furfrou class represents the Furfrou pet in the game.
 * This class extends the Pet class and initializes the Furfrou pet with specific attributes.
 * It also handles  stat decreases through background threads.
 * 
 * @version 1.0
 * @author Meridith Shang
 */
public class Furfrou extends Pet {

     /**
     * Constructs a new Furfrou instance with the given name and GameManager instance.
     * Initializes the pet's attributes and starts background threads for stats.
     *
     * @param name The name of the pet.
     * @param gm The GameManager instance managing the game flow.
     */
    public Furfrou(String name, GameManager gm) {
        super(name, gm);
        this.happinessDecrease = 3;
        this.happinessCur = 40;
        this.happinessMax = 80;
        this.fullnessCur = 60;
        this.fullnessMax = 120;
        this.fullnessDecrease = 1;
        this.petType = "Furfrou";

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
     * Constructs a new Furfrou instance with detailed attributes.
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
     * @param lastExercise The timestamp of the last exercise.
     * @param lastPlay The timestamp of the last play session.
     * @param gm The GameManager instance managing the game flow.
     */
    public Furfrou(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, Long lastVet, Long lastExercise, Long lastPlay, GameManager gm) {
        super(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        this.happinessDecrease = 3;
        this.fullnessDecrease = 1;
        this.petType = "Furfrou";

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
     * @return The pet type, which is "Furfrou".
     */
    @Override
    public String getPetType() {
        return "Furfrou";
    }

    /**
     * Sets the type of the pet.
     * 
     * @param petType The pet type to set.
     */
    @Override
    public void setPetType(String petType) {
        this.petType = petType;
    }


}
