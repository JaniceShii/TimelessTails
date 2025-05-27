package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PetSaver {
    /**
     * Saves the pet data to a text file in key=value format.
     * The file will contain entries such as:
     * name=Fido
     * points=150
     * healthCur=85
     * ...
     *
     * @param filePath the path to the output text file (e.g., "pet.txt")
     * @param pet the Pet object whose data is to be saved
     */
    public static void savePet(String filePath, Pet pet) {
        Properties properties = new Properties();
        File file = new File("../pet1.txt");

        if (file.exists()) {
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        properties.setProperty("name", pet.getName());
        properties.setProperty("points", Integer.toString(pet.getPoints()));
        properties.setProperty("healthCur", Integer.toString(pet.getHealth()));
        properties.setProperty("healthMax", Integer.toString(pet.getMaxHealth()));
        properties.setProperty("fullnessCur", Integer.toString(pet.getFullness()));
        properties.setProperty("fullnessMax", Integer.toString(pet.getMaxFullness()));
        properties.setProperty("sleepCur", Integer.toString(pet.getSleep()));
        properties.setProperty("sleepMax", Integer.toString(pet.getMaxSleep()));
        properties.setProperty("happinessCur", Integer.toString(pet.getHappiness()));
        properties.setProperty("happinessMax", Integer.toString(pet.getMaxHappiness()));
        properties.setProperty("state", pet.getState());
        // Note: if Pet doesn’t have a getter for startTime, consider adding one.
      //  properties.setProperty("startTime", Long.toString(pet.startTime));
        properties.setProperty("lastVet", Long.toString(pet.getLastVet()));
        properties.setProperty("lastPlay", Long.toString(pet.getLastPlay()));
        properties.setProperty("lastExercise", Long.toString(pet.getLastExercise()));
        properties.setProperty("petType", pet.getPetType());

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, "Pet Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

