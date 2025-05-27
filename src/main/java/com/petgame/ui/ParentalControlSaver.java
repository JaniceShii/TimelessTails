package com.petgame.ui;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The {@code ParentalControlSaver} class provides a utility method to save parental control settings
 * from a {@link ParentalControl} instance to a text file.
 * 
 * The file will be formatted with key-value pairs corresponding to parental control settings.
 * 
 * Example:
 * <pre>
 * enabled=true
 * allowedStart=08:00
 * allowedEnd=22:00
 * totalPlayTime=3600000
 * sessionCount=10
 * lastSessionTime=180000
 * </pre>
 * 
 * @version 1.0
 * @author Jugraj Khangura
 */

public class ParentalControlSaver {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    /**
     * Saves parental control data from the given ParentalControl object into a text file.
     *
     * @param filePath the path to the parental control file (e.g., "saves/parentalControl.txt")
     * @param pc the ParentalControl instance containing the data to be saved
     */
    public static void saveParentalControl(String filePath, ParentalControl pc) {
        Properties properties = new Properties();

        properties.setProperty("enabled", Boolean.toString(pc.isEnabled()));
        properties.setProperty("allowedStart", pc.getAllowedStart().format(TIME_FORMATTER));
        properties.setProperty("allowedEnd", pc.getAllowedEnd().format(TIME_FORMATTER));
        properties.setProperty("totalPlayTime", Long.toString(pc.getTotalPlayTime()));
        properties.setProperty("sessionCount", Integer.toString(pc.getSessionCount()));
        properties.setProperty("lastSessionTime", Long.toString(pc.getLastSessionTime()));
        
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, "Parental Control Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}