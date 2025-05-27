package ui;
import game.GameManager;

public class Magearna extends Pet {

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

    public Magearna(String name, int points, int healthCur, int healthMax, int fullnessCur, int fullnessMax, int sleepCur, String state, int sleepMax, int happinessCur, int happinessMax, Long lastVet, Long lastExercise, Long lastPlay, GameManager gm) {
        super(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        this.sleepDecrease = 3;
        this.happinessDecrease = 1;
        this.petType = "Magearna";

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
        return "Magearna";
    }
    
}
