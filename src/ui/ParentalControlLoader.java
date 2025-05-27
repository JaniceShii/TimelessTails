package ui;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ParentalControlLoader {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    /**
     * Loads parental control data from a text file into the given ParentalControl instance.
     *
     * @param filePath the path to the parental control file (e.g., "saves/parentalControl.txt")
     * @param pc the ParentalControl instance to update
     */
    public static void loadParentalControl(String filePath, ParentalControl pc) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Parental Control file " + filePath + " does not exist. Using default values.");
            return;
        }
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
            pc.setEnabled(Boolean.parseBoolean(properties.getProperty("enabled", "false").trim()));
            String startStr = properties.getProperty("allowedStart", "00:00").trim();
            String endStr = properties.getProperty("allowedEnd", "23:59").trim();
            pc.setAllowedStart(LocalTime.parse(startStr, TIME_FORMATTER));
            pc.setAllowedEnd(LocalTime.parse(endStr, TIME_FORMATTER));
            pc.setTotalPlayTime(Long.parseLong(properties.getProperty("totalPlayTime", "0").trim()));
            pc.setSessionCount(Integer.parseInt(properties.getProperty("sessionCount", "0").trim()));
            pc.setLastSessionTime(Long.parseLong(properties.getProperty("lastSessionTime", "0").trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}