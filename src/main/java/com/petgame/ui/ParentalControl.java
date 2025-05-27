package com.petgame.ui;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The ParentalControl class provides functionality for controlling and tracking playtime.
 * It supports enabling or disabling restrictions, setting allowed time windows,
 * and keeping statistics on playtime sessions.
 * 
 * @version 1.0
 * @author Advaith Thakur
 */
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
    
    /**
     * Constructs a ParentalControl object with default settings (disabled, full day allowed).
     */
    public ParentalControl() {
        // Default values: disabled and full day allowed
        this.enabled = false;
        this.allowedStart = LocalTime.of(0, 0);
        this.allowedEnd = LocalTime.of(23, 59);
        this.totalPlayTime = 0;
        this.sessionCount = 0;
        this.lastSessionTime = 0;
    }
    
    /**
     * Checks if the parental control feature is enabled.
     * @return true if enabled, false otherwise.
     */
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Enables or disables the parental control feature.
     * @param enabled true to enable, false to disable.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * Gets the allowed start time for play.
     * @return The allowed start time.
     */
    public LocalTime getAllowedStart() {
        return allowedStart;
    }

    /**
     * Sets the allowed start time for play.
     * @param allowedStart The start time to set.
     */
    public void setAllowedStart(LocalTime allowedStart) {
        this.allowedStart = allowedStart;
    }

    /**
     * Gets the allowed end time for play.
     * @return The allowed end time.
     */
    public LocalTime getAllowedEnd() {
        return allowedEnd;
    }

    /**
     * Sets the allowed end time for play.
     * @param allowedEnd The end time to set.
     */
    public void setAllowedEnd(LocalTime allowedEnd) {
        this.allowedEnd = allowedEnd;
    }

    /**
     * Gets the total playtime recorded.
     * @return Total playtime in milliseconds.
     */
    public long getTotalPlayTime() {
        return totalPlayTime;
    }

    /**
     * Sets the total playtime.
     * @param totalPlayTime Total playtime to set in milliseconds.
     */
    public void setTotalPlayTime(long totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }

    /**
     * Gets the total number of play sessions recorded.
     * @return The total session count.
     */
    public int getSessionCount() {
        return sessionCount;
    }

    /**
     * Sets the total number of play sessions.
     * @param sessionCount The session count to set.
     */
    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    /**
     * Gets the duration of the most recent session.
     * @return The last session duration in milliseconds.
     */
    public long getLastSessionTime() {
        return lastSessionTime;
    }

    /**
     * Sets the duration of the most recent session.
     * @param lastSessionTime The duration of the last session in milliseconds.
     */
    public void setLastSessionTime(long lastSessionTime) {
        this.lastSessionTime = lastSessionTime;
    }


    /**
     * Updates playtime statistics when a session ends.
     * @param sessionDuration The duration of the session in milliseconds.
     */
    public void updateSessionTime(long sessionDuration) {
        this.totalPlayTime += sessionDuration;
        this.sessionCount++;
        this.lastSessionTime = sessionDuration;
    }
    
    /**
     * Calculates the average playtime per session.
     * @return The average playtime in milliseconds.
     */
    public long getAveragePlayTime() {
        return sessionCount == 0 ? 0 : totalPlayTime / sessionCount;
    }
    
    /**
     * Resets all playtime statistics to zero.
     */
    public void resetPlayTime() {
        this.totalPlayTime = 0;
        this.sessionCount = 0;
        this.lastSessionTime = 0;
    }
    
    /**
     * Returns a string representation of the ParentalControl object.
     * @return A string with the state of the parental control.
     */
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