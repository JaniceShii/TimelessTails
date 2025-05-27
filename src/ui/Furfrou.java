package ui;

import game.GameManager;

public class Furfrou extends Pet {

    public Furfrou(String name, GameManager gm) {
        super(name, gm);
        this.happinessDecrease = 3;
        this.happinessCur = 40;
        this.happinessMax = 80;
        this.fullnessCur = 60;
        this.fullnessMax = 120;
        this.fullnessDecrease = 1;
        this.petType = "Furfrou";

        new Thread(() -> {
            this.decreaseStats("happiness");
        }).start();
        // This runs in the background
        new Thread(() -> {
            this.decreaseStats("sleep");  
        }).start();
        // This runs in the background
        new Thread(() -> {
            this.decreaseStats("fullness");
        }).start();
        // This runs in the background
        new Thread(() -> {
            this.checkState();
        }).start();
    }

    public Furfrou(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, Long lastVet, Long lastExercise, Long lastPlay, GameManager gm) {
        super(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        this.happinessDecrease = 3;
        this.fullnessDecrease = 1;
        this.petType = "Furfrou";

        
        new Thread(() -> {
            this.decreaseStats("happiness");
        }).start();
        // This runs in the background
        new Thread(() -> {
            this.decreaseStats("sleep");  
        }).start();
        // This runs in the background
        new Thread(() -> {
            this.decreaseStats("fullness");
        }).start();
        // This runs in the background
        new Thread(() -> {
            this.checkState();
        }).start();
    }

    @Override
    public String getPetType() {
        return "Furfrou";
    }

}
