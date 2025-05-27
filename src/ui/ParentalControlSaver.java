package ui;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

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
    
    // Main method for testing.
    public static void main(String[] args) {
        String filePath = "saves" + File.separator + "parentalControl.txt";
        File saveDir = new File("saves");
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        ParentalControl pc = new ParentalControl();
        // Set some sample parental control settings.
        pc.setEnabled(true);
        pc.setAllowedStart(java.time.LocalTime.of(18, 0));
        pc.setAllowedEnd(java.time.LocalTime.of(22, 0));
        // For example, simulate a session of 10 minutes (600,000 ms).
        pc.updateSessionTime(600000);
        
        saveParentalControl(filePath, pc);
        System.out.println("Parental Control data saved: " + pc);
    }
}