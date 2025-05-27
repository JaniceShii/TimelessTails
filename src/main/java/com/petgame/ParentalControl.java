package com.petgame;

public class ParentalControl {
    private long totalTimePlayed;    // total milliseconds played
    private int sessionCount;
    private long averagePlaytime;    // in milliseconds, or store in seconds if you like
    private int limitHours;          // daily hour limit, for example
    private int limitDays;           // maybe maximum consecutive days?
    private String playtimePassword;

    private long currentSessionStart;  // track when the current session began

    public ParentalControl() {
        this.totalTimePlayed = 0;
        this.sessionCount = 0;
        this.averagePlaytime = 0;
        this.limitHours = 0;
        this.limitDays = 0;
        this.playtimePassword = "";
        this.currentSessionStart = 0;
    }

    // If you want a constructor with all fields:
    public ParentalControl(long totalTimePlayed, int sessionCount, long averagePlaytime,
                           int limitHours, int limitDays, String playtimePassword) {
        this.totalTimePlayed = totalTimePlayed;
        this.sessionCount = sessionCount;
        this.averagePlaytime = averagePlaytime;
        this.limitHours = limitHours;
        this.limitDays = limitDays;
        this.playtimePassword = playtimePassword;
        this.currentSessionStart = 0;
    }

    // Start the session
    public void startSession() {
        this.currentSessionStart = System.currentTimeMillis();
    }

    // End the session and update stats
    public void endSession() {
        long now = System.currentTimeMillis();
        if (currentSessionStart > 0) {
            long sessionDuration = now - currentSessionStart;
            this.totalTimePlayed += sessionDuration;
            this.sessionCount++;
            this.averagePlaytime = this.totalTimePlayed / this.sessionCount;
            this.currentSessionStart = 0;  // reset
        }
    }

    // Getters and setters
    public long getTotalTimePlayed() { return totalTimePlayed; }
    public void setTotalTimePlayed(long totalTimePlayed) { this.totalTimePlayed = totalTimePlayed; }

    public int getSessionCount() { return sessionCount; }
    public void setSessionCount(int sessionCount) { this.sessionCount = sessionCount; }

    public long getAveragePlaytime() { return averagePlaytime; }
    public void setAveragePlaytime(long averagePlaytime) { this.averagePlaytime = averagePlaytime; }

    public int getLimitHours() { return limitHours; }
    public void setLimitHours(int limitHours) { this.limitHours = limitHours; }

    public int getLimitDays() { return limitDays; }
    public void setLimitDays(int limitDays) { this.limitDays = limitDays; }

    public String getPlaytimePassword() { return playtimePassword; }
    public void setPlaytimePassword(String playtimePassword) { this.playtimePassword = playtimePassword; }
}
