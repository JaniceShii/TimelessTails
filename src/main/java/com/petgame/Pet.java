/*package com.petgame;

public final class Pet {
    private int hunger;
    private int happiness;
    private int sleepiness;
    private int health;

    public Pet() {
        this(50, 50, 50, 100);
    }

    public Pet(int hunger, int happiness, int sleepiness, int health) {
        this.hunger = clampValue(hunger);
        this.happiness = clampValue(happiness);
        this.sleepiness = clampValue(sleepiness);
        this.health = clampValue(health);
    }

    // Getters and Setters
    public int getHunger() { return hunger; }
    public void setHunger(int hunger) { this.hunger = clampValue(hunger); }
    
    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = clampValue(happiness); }
    
    public int getSleepiness() { return sleepiness; }
    public void setSleepiness(int sleepiness) { this.sleepiness = clampValue(sleepiness); }
    
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = clampValue(health); }

    private int clampValue(int value) {
        return Math.max(0, Math.min(100, value));
    }
} */

package com.petgame;

public class Pet {
    private String petType;    // "A", "B", or "C"
    private String name;
    private int hunger;
    private int sleepiness;
    private int happiness;
    private int health;
    private long totalPoints;

    // When can the pet next sleep or exercise? (in epoch milliseconds)
    private long nextSleepAllowed;
    private long nextExerciseAllowed;

    // Constructor
    public Pet(String petType, String name, int hunger, int sleepiness, int happiness, int health, long totalPoints) {
        this.petType = petType;
        this.name = name;
        this.hunger = hunger;
        this.sleepiness = sleepiness;
        this.happiness = happiness;
        this.health = health;
        this.totalPoints = totalPoints;

        this.nextSleepAllowed = 0L;     // 0 means "no cooldown yet," or you can set it to current time + some offset
        this.nextExerciseAllowed = 0L;
    }

    // Getters and Setters
    public String getPetType() { return petType; }
    public void setPetType(String petType) { this.petType = petType; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHunger() { return hunger; }
    public void setHunger(int hunger) { this.hunger = hunger; }

    public int getSleepiness() { return sleepiness; }
    public void setSleepiness(int sleepiness) { this.sleepiness = sleepiness; }

    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = happiness; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public long getTotalPoints() { return totalPoints; }
    public void setTotalPoints(long totalPoints) { this.totalPoints = totalPoints; }

    public long getNextSleepAllowed() { return nextSleepAllowed; }
    public void setNextSleepAllowed(long nextSleepAllowed) { this.nextSleepAllowed = nextSleepAllowed; }

    public long getNextExerciseAllowed() { return nextExerciseAllowed; }
    public void setNextExerciseAllowed(long nextExerciseAllowed) { this.nextExerciseAllowed = nextExerciseAllowed; }

    // Example logic: set a 12-hour cooldown for sleep
    public void sleep() {
        long now = System.currentTimeMillis();
        if (now >= nextSleepAllowed) {
            // do your sleep logic...
            // then set the next allowed time:
            this.nextSleepAllowed = now + 12L * 60L * 60L * 1000L; // 12 hours in ms
        } else {
            System.out.println("Pet cannot sleep yet!");
        }
    }
    
    // Similarly for exercise
    public void exercise() {
        long now = System.currentTimeMillis();
        if (now >= nextExerciseAllowed) {
            // do your exercise logic...
            this.nextExerciseAllowed = now + 6L * 60L * 60L * 1000L; // for example, 6-hour cooldown
        } else {
            System.out.println("Pet cannot exercise yet!");
        }
    }
}