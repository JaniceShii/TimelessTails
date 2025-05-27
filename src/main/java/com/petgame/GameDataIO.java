package com.petgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class GameDataIO {

    // ------------------------------------------------
    // SAVE
    // ------------------------------------------------
    public static void saveGame(Pet pet,
                                Inventory inventory,
                                ParentalControl parental,
                                String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Section,Key,Value");

            // --------------------
            // SAVE PET
            // --------------------
            writer.printf("Stats,Type,%s%n", pet.getPetType());
            writer.printf("Stats,Name,%s%n", pet.getName());
            writer.printf("Stats,Hunger,%d%n", pet.getHunger());
            writer.printf("Stats,Sleepiness,%d%n", pet.getSleepiness());
            writer.printf("Stats,Happiness,%d%n", pet.getHappiness());
            writer.printf("Stats,Health,%d%n", pet.getHealth());
            writer.printf("Stats,TotalPoints,%d%n", pet.getTotalPoints());
            writer.printf("Stats,NextSleepAllowed,%d%n", pet.getNextSleepAllowed());
            writer.printf("Stats,NextExerciseAllowed,%d%n", pet.getNextExerciseAllowed());

            // --------------------
            // SAVE INVENTORY
            // --------------------
            for (Map.Entry<String, Integer> entry : inventory.getItems().entrySet()) {
                writer.printf("Inventory,%s,%d%n", entry.getKey(), entry.getValue());
            }

            // --------------------
            // SAVE PARENTAL
            // --------------------
            writer.printf("Settings,TotalTimePlayed,%d%n", parental.getTotalTimePlayed());
            writer.printf("Settings,SessionCount,%d%n", parental.getSessionCount());
            writer.printf("Settings,AveragePlaytime,%d%n", parental.getAveragePlaytime());
            writer.printf("Settings,LimitHours,%d%n", parental.getLimitHours());
            writer.printf("Settings,LimitDays,%d%n", parental.getLimitDays());
            writer.printf("Settings,PlaytimePassword,%s%n", parental.getPlaytimePassword());

            System.out.println("Game data saved to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------
    // LOAD
    // ------------------------------------------------
    public static void loadGame(Pet pet,
                                Inventory inventory,
                                ParentalControl parental,
                                String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No save file found. Starting fresh...");
            return;
        }

        // Clear inventory before loading
        inventory.getItems().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip header line (e.g. "Section,Key,Value")
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length != 3) {
                    continue;
                }

                String section = parts[0].trim();  // e.g. "Stats", "Inventory", "Settings"
                String key     = parts[1].trim();  // e.g. "Hunger", "TimePlayed", ...
                String value   = parts[2].trim();  // e.g. "50", "A", "secret"

                switch (section) {
                    case "Stats":
                        switch (key) {
                            case "Type":
                                pet.setPetType(value);
                                break;
                            case "Name":
                                pet.setName(value);
                                break;
                            case "Hunger":
                                pet.setHunger(Integer.parseInt(value));
                                break;
                            case "Sleepiness":
                                pet.setSleepiness(Integer.parseInt(value));
                                break;
                            case "Happiness":
                                pet.setHappiness(Integer.parseInt(value));
                                break;
                            case "Health":
                                pet.setHealth(Integer.parseInt(value));
                                break;
                            case "TotalPoints":
                                pet.setTotalPoints(Long.parseLong(value));
                                break;
                            case "NextSleepAllowed":
                                pet.setNextSleepAllowed(Long.parseLong(value));
                                break;
                            case "NextExerciseAllowed":
                                pet.setNextExerciseAllowed(Long.parseLong(value));
                                break;
                        }
                        break;

                    case "Inventory":
                        // key = item name, value = quantity
                        inventory.getItems().put(key, Integer.parseInt(value));
                        break;

                    case "Settings":
                        switch (key) {
                            case "TotalTimePlayed":
                                parental.setTotalTimePlayed(Long.parseLong(value));
                                break;
                            case "SessionCount":
                                parental.setSessionCount(Integer.parseInt(value));
                                break;
                            case "AveragePlaytime":
                                parental.setAveragePlaytime(Long.parseLong(value));
                                break;
                            case "LimitHours":
                                parental.setLimitHours(Integer.parseInt(value));
                                break;
                            case "LimitDays":
                                parental.setLimitDays(Integer.parseInt(value));
                                break;
                            case "PlaytimePassword":
                                parental.setPlaytimePassword(value);
                                break;
                        }
                        break;
                }
            }

            System.out.println("Game data loaded from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------
    // MAIN (DEMO)
    // ------------------------------------------------
    public static void main(String[] args) {
        // 1) Create an example Pet
        Pet pet = new Pet("A", "Fluffy", 
                          50,     // hunger
                          10,     // sleepiness
                          100,    // happiness
                          90,     // health
                          2000L); // totalPoints
        // Set sample cooldown times (in real usage, you'd do pet.sleep() or pet.exercise())
        long now = System.currentTimeMillis();
        pet.setNextSleepAllowed(now + 12L * 60L * 60L * 1000L);    // 12 hours from now
        pet.setNextExerciseAllowed(now + 6L * 60L * 60L * 1000L);  // 6 hours from now

        // 2) Create Inventory
        Inventory inventory = new Inventory();
        inventory.addItem("Apple", 5);
        inventory.addItem("Cookie", 2);

        // 3) Create ParentalControl
        ParentalControl parental = new ParentalControl();
        parental.setLimitHours(3);       // e.g. 3 hours daily limit
        parental.setLimitDays(7);        // e.g. up to 7 consecutive days?
        parental.setPlaytimePassword("secret123");

        // Start a session
        System.out.println("[Parental] Starting session...");
        parental.startSession();
        try {
            // Simulate 1-second play session
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parental.endSession();
        System.out.println("[Parental] Session ended.");

        // Check updated session stats
        System.out.println("TotalTimePlayed (ms): " + parental.getTotalTimePlayed());
        System.out.println("SessionCount:        " + parental.getSessionCount());
        System.out.println("AveragePlaytime (ms):" + parental.getAveragePlaytime());

        // 4) Save everything to CSV
        String filename = "my_pet_save.csv";
        saveGame(pet, inventory, parental, filename);

        // 5) Reset objects to prove loading works
        pet.setPetType("Z");
        pet.setName("Unknown");
        pet.setHunger(0);
        pet.setSleepiness(0);
        pet.setHappiness(0);
        pet.setHealth(0);
        pet.setTotalPoints(0);
        pet.setNextSleepAllowed(0);
        pet.setNextExerciseAllowed(0);

        inventory.getItems().clear();

        parental.setTotalTimePlayed(0);
        parental.setSessionCount(0);
        parental.setAveragePlaytime(0);
        parental.setLimitHours(0);
        parental.setLimitDays(0);
        parental.setPlaytimePassword("");

        // 6) Load from CSV
        loadGame(pet, inventory, parental, filename);

        // 7) Confirm data loaded
        System.out.println("\n--- AFTER LOADING ---");
        System.out.println("PetType:              " + pet.getPetType());
        System.out.println("PetName:              " + pet.getName());
        System.out.println("Hunger:               " + pet.getHunger());
        System.out.println("Sleepiness:           " + pet.getSleepiness());
        System.out.println("Happiness:            " + pet.getHappiness());
        System.out.println("Health:               " + pet.getHealth());
        System.out.println("TotalPoints:          " + pet.getTotalPoints());
        System.out.println("NextSleepAllowed (ms):" + pet.getNextSleepAllowed());
        System.out.println("NextExerciseAllowed:  " + pet.getNextExerciseAllowed());

        System.out.println("Inventory items:      " + inventory.getItems());

        System.out.println("Parental totalTimePlayed (ms): " + parental.getTotalTimePlayed());
        System.out.println("Parental sessionCount:         " + parental.getSessionCount());
        System.out.println("Parental averagePlaytime (ms): " + parental.getAveragePlaytime());
        System.out.println("Parental limitHours:           " + parental.getLimitHours());
        System.out.println("Parental limitDays:            " + parental.getLimitDays());
        System.out.println("Parental password:             " + parental.getPlaytimePassword());
    }
}
/* 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class GameDataIO {

    // ------------------------------------------------
    // SAVE
    // ------------------------------------------------
    public static void saveGame(Pet pet,
                                Inventory inventory,
                                ParentalControl parental,
                                String filename) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Section,Key,Value");

            // --------------------
            // SAVE PET
            // --------------------
            writer.printf("Stats,Type,%s%n", pet.getPetType());
            writer.printf("Stats,Name,%s%n", pet.getName());
            writer.printf("Stats,Hunger,%d%n", pet.getHunger());
            writer.printf("Stats,Sleepiness,%d%n", pet.getSleepiness());
            writer.printf("Stats,Happiness,%d%n", pet.getHappiness());
            writer.printf("Stats,Health,%d%n", pet.getHealth());
            writer.printf("Stats,TotalPoints,%d%n", pet.getTotalPoints());
            writer.printf("Stats,NextSleepAllowed,%d%n", pet.getNextSleepAllowed());
            writer.printf("Stats,NextExerciseAllowed,%d%n", pet.getNextExerciseAllowed());

            // --------------------
            // SAVE INVENTORY
            // --------------------
            for (Map.Entry<String, Integer> entry : inventory.getItems().entrySet()) {
                writer.printf("Inventory,%s,%d%n", entry.getKey(), entry.getValue());
            }

            // --------------------
            // SAVE PARENTAL
            // --------------------
            writer.printf("Settings,TotalTimePlayed,%d%n", parental.getTotalTimePlayed());
            writer.printf("Settings,SessionCount,%d%n", parental.getSessionCount());
            writer.printf("Settings,AveragePlaytime,%d%n", parental.getAveragePlaytime());
            writer.printf("Settings,LimitHours,%d%n", parental.getLimitHours());
            writer.printf("Settings,LimitDays,%d%n", parental.getLimitDays());
            writer.printf("Settings,PlaytimePassword,%s%n", parental.getPlaytimePassword());
            
            System.out.println("Game data saved to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------
    // LOAD
    // ------------------------------------------------
    public static void loadGame(Pet pet,
                                Inventory inventory,
                                ParentalControl parental,
                                String filename) {

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No save file found. Starting fresh...");
            return;
        }

        // Clear inventory
        inventory.getItems().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip header line
            String line = reader.readLine(); // e.g., "Section,Key,Value"

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length != 3) {
                    continue;
                }

                String section = parts[0].trim();  // e.g. "Stats", "Inventory", "Settings"
                String key     = parts[1].trim();  // e.g. "Hunger", "TimePlayed", ...
                String value   = parts[2].trim();  // e.g. "50", "A", "Password123"

                switch (section) {
                    case "Stats":
                        switch (key) {
                            case "Type":
                                pet.setPetType(value);
                                break;
                            case "Name":
                                pet.setName(value);
                                break;
                            case "Hunger":
                                pet.setHunger(Integer.parseInt(value));
                                break;
                            case "Sleepiness":
                                pet.setSleepiness(Integer.parseInt(value));
                                break;
                            case "Happiness":
                                pet.setHappiness(Integer.parseInt(value));
                                break;
                            case "Health":
                                pet.setHealth(Integer.parseInt(value));
                                break;
                            case "TotalPoints":
                                pet.setTotalPoints(Long.parseLong(value));
                                break;
                            case "NextSleepAllowed":
                                pet.setNextSleepAllowed(Long.parseLong(value));
                                break;
                            case "NextExerciseAllowed":
                                pet.setNextExerciseAllowed(Long.parseLong(value));
                                break;
                        }
                        break;

                    case "Inventory":
                        // itemName = key, quantity = value
                        inventory.getItems().put(key, Integer.parseInt(value));
                        break;

                    case "Settings":
                        switch (key) {
                            case "TotalTimePlayed":
                                parental.setTotalTimePlayed(Long.parseLong(value));
                                break;
                            case "SessionCount":
                                parental.setSessionCount(Integer.parseInt(value));
                                break;
                            case "AveragePlaytime":
                                parental.setAveragePlaytime(Long.parseLong(value));
                                break;
                            case "LimitHours":
                                parental.setLimitHours(Integer.parseInt(value));
                                break;
                            case "LimitDays":
                                parental.setLimitDays(Integer.parseInt(value));
                                break;
                            case "PlaytimePassword":
                                parental.setPlaytimePassword(value);
                                break;
                        }
                        break;
                }
            }

            System.out.println("Game data loaded from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------
    // Demo
    // ------------------------------------------------
    public static void main(String[] args) {
        // Create an example pet
        Pet pet = new Pet("A", "Fluffy", 50, 10, 100, 90, 2000);

        // Create inventory
        Inventory inventory = new Inventory();
        inventory.addItem("Apple", 5);
        inventory.addItem("Cookie", 2);

        // Create parental control
        ParentalControl parental = new ParentalControl();
        parental.setLimitHours(2);
        parental.setLimitDays(7);
        parental.setPlaytimePassword("secret123");

        // Start a session
        parental.startSession();
        // ... pretend some time passes ...
        parental.endSession();  // updates totalTimePlayed, sessionCount, averageTime

        // Save
        saveGame(pet, inventory, parental, "my_pet_save.csv");

        // Reset to prove loading works
        pet.setPetType("Z");
        pet.setName("Unknown");
        pet.setHunger(0);
        pet.setSleepiness(0);
        pet.setHappiness(0);
        pet.setHealth(0);
        pet.setTotalPoints(0);
        pet.setNextSleepAllowed(0);
        pet.setNextExerciseAllowed(0);
        inventory.getItems().clear();
        parental.setTotalTimePlayed(0);
        parental.setSessionCount(0);
        parental.setAveragePlaytime(0);
        parental.setLimitHours(0);
        parental.setLimitDays(0);
        parental.setPlaytimePassword("");

        // Load
        loadGame(pet, inventory, parental, "my_pet_save.csv");

        // Confirm loaded
        System.out.println("PetType: " + pet.getPetType());
        System.out.println("PetName: " + pet.getName());
        System.out.println("TotalPoints: " + pet.getTotalPoints());
        System.out.println("NextSleepAllowed: " + pet.getNextSleepAllowed());
        System.out.println("NextExerciseAllowed: " + pet.getNextExerciseAllowed());
        System.out.println("Parental totalTimePlayed: " + parental.getTotalTimePlayed());
        System.out.println("Parental sessionCount: " + parental.getSessionCount());
        System.out.println("Parental averagePlaytime: " + parental.getAveragePlaytime());
        System.out.println("Parental limitHours: " + parental.getLimitHours());
        System.out.println("Parental limitDays: " + parental.getLimitDays());
        System.out.println("Parental password: " + parental.getPlaytimePassword());
        System.out.println("Inventory items: " + inventory.getItems());
    }
}*/