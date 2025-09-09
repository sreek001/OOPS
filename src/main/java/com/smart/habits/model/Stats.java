package com.smart.habits.model;

public class Stats {
    private int points;
    private int streak;

    public Stats(int points, int streak) {
        this.points = points;
        this.streak = streak;
    }

    public int getPoints() { return points; }
    public int getStreak() { return streak; }
}