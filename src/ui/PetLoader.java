package ui;
  
import game.GameManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PetLoader {
    /**
     * Loads pet data from a text file in key=value format into the given Pet instance.
     * Lines beginning with '#' are ignored as comments.
     *
     * @param filePath the path to the save file
     * @param pet the Pet instance to load data into
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
        // if (happinessCurRaw == null) {
        //     System.out.println("Error: happinessCur not found!");
        // }
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

    // checks if the pet is dead or not
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