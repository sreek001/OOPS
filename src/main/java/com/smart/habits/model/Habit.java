package com.smart.habits.model;

public class Habit {
    private int id;
    private int userId;
    private String name;
    private String frequency;

    public Habit(int id, int userId, String name, String frequency) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getFrequency() { return frequency; }
}