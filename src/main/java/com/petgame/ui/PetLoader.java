package com.petgame.ui;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.petgame.game.GameManager;

/**
 * The class is responsible for loading pet data from save files and checking if a pet is dead.
 * It reads the pet's attributes from a file in key-value format and constructs a pet object accordingly.
 * Supported pet types are: Furfrou, Tyrunt, and Magearna.
 * 
 * @version 1.0
 * @author Jugraj
 */

public class PetLoader {
    /**
     * Loads pet data from a text file in key=value format into the given Pet instance.
     * Lines beginning with '#' are ignored as comments.
     *
     * @param filePath the path to the save file
     * @param gm the code used for screen navigation and game state management
     */
    public static void loadPet(String filePath, GameManager gm) {

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Pet file " + filePath + " does not exist.");
            return;
        }
        
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Loading pet data from " + filePath);
        

        Pet pet;

        String name = properties.getProperty("name", "Unnamed").trim();
        int points = Integer.parseInt(properties.getProperty("points", "0").trim());
        int healthCur = Integer.parseInt(properties.getProperty("healthCur", "0").trim());
        int healthMax = Integer.parseInt(properties.getProperty("healthMax", "0").trim());
        int fullnessCur = Integer.parseInt(properties.getProperty("fullnessCur", "0").trim());
        int fullnessMax = Integer.parseInt(properties.getProperty("fullnessMax", "0").trim());
        int sleepCur = Integer.parseInt(properties.getProperty("sleepCur", "0").trim());
        int sleepMax = Integer.parseInt(properties.getProperty("sleepMax", "0").trim());
        String happinessCurRaw = properties.getProperty("happinessCur");
        int happinessCur = Integer.parseInt(happinessCurRaw.trim());
        int happinessMax = Integer.parseInt(properties.getProperty("happinessMax", "0").trim());
        String state = properties.getProperty("state", "normal").trim();                             
        Long lastVet = Long.parseLong(properties.getProperty("lastVet", "0").trim());
        Long lastPlay = Long.parseLong(properties.getProperty("lastPlay", "0").trim());
        Long lastExercise = Long.parseLong(properties.getProperty("lastExercise", "0").trim());
        String petType =  properties.getProperty("petType", "invalid").trim();   


        if (petType.equals("Furfrou")) {
            pet = new Furfrou(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        } else if (petType.equals("Tyrunt")) {
            pet = new Tyrunt(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        } else if (petType.equals("Magearna")) {
            pet = new Magearna(name, points, healthCur, healthMax, fullnessCur, fullnessMax, sleepCur, state, sleepMax, happinessCur, happinessMax, lastVet, lastExercise, lastPlay, gm);
        } else {
            System.out.println("Unknown pet type: " + petType);
            return;
        }
        gm.setCurrentPet(pet);
    
    }

    /**
     * Checks if a pet is dead by reading its state from save files
     * 
     * @param filePath The path to store the game file
     * @param gm the code used for screen navigation and game state management
     * @return true if the pet's state is "dead", otherwise false}.
     */
    public static boolean checkDeath(String filePath, GameManager gm) {

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Pet file " + filePath + " does not exist.");
            return false;
        }
        
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        String state = properties.getProperty("state", "normal").trim();  

        if (state.equals("dead")) {
            return true;
        } else {
            return false;
        }
    }

}