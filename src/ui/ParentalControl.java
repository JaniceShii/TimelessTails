package ui;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ParentalControl {
    // Parental control feature
    private boolean enabled;
    private LocalTime allowedStart;
    private LocalTime allowedEnd;
    
    // Play time statistics (in milliseconds)
    private long totalPlayTime;
    private int sessionCount;
    private long lastSessionTime;  // Duration of the most recent session, in ms.
    
    // Formatter for allowed times (HH:mm format)
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    
    public ParentalControl() {
        // Default values: disabled and full day allowed
        this.enabled = false;
        this.allowedStart = LocalTime.of(0, 0);
        this.allowedEnd = LocalTime.of(23, 59);
        this.totalPlayTime = 0;
        this.sessionCount = 0;
        this.lastSessionTime = 0;
    }
    
    // Getters and Setters
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public LocalTime getAllowedStart() {
        return allowedStart;
    }
    public void setAllowedStart(LocalTime allowedStart) {
        this.allowedStart = allowedStart;
    }
    public LocalTime getAllowedEnd() {
        return allowedEnd;
    }
    public void setAllowedEnd(LocalTime allowedEnd) {
        this.allowedEnd = allowedEnd;
    }
    public long getTotalPlayTime() {
        return totalPlayTime;
    }
    public void setTotalPlayTime(long totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }
    public int getSessionCount() {
        return sessionCount;
    }
    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public long getLastSessionTime() {
        return lastSessionTime;
    }
    public void setLastSessionTime(long lastSessionTime) {
        this.lastSessionTime = lastSessionTime;
    }


    /**
     * Call this method when a session ends. It adds the session's duration (in ms)
     * to the total play time, increments the session count, and stores the session's duration.
     */
    public void updateSessionTime(long sessionDuration) {
        this.totalPlayTime += sessionDuration;
        this.sessionCount++;
        this.lastSessionTime = sessionDuration;
    }
    
    // Compute average play time in milliseconds
    public long getAveragePlayTime() {
        return sessionCount == 0 ? 0 : totalPlayTime / sessionCount;
    }
    
    // Reset play time statistics
    public void resetPlayTime() {
        this.totalPlayTime = 0;
        this.sessionCount = 0;
        this.lastSessionTime = 0;
    }
    
    @Override
    public String toString() {
        return "ParentalControl{" +
               "enabled=" + enabled +
               ", allowedStart=" + allowedStart.format(TIME_FORMATTER) +
               ", allowedEnd=" + allowedEnd.format(TIME_FORMATTER) +
               ", totalPlayTime=" + totalPlayTime +
               ", sessionCount=" + sessionCount +
               ", averagePlayTime=" + getAveragePlayTime() +
               ", lastSessionTime=" + lastSessionTime +
               '}';
    }
}