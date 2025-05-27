package ui;
import game.GameManager;

public class Tyrunt extends Pet {

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

    public Tyrunt(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, long lastVet, long lastExercise, long lastPlay, GameManager gm) {
        super(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        this.fullnessDecrease = 3;
        this.sleepDecrease = 1;
        this.petType = "tyrunt";

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
        return "Tyrunt";
    }
}
