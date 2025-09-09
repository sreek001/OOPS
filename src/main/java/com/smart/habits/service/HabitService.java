package com.smart.habits.service;

import com.smart.habits.db.Database;
import com.smart.habits.model.Habit;
import com.smart.habits.model.User;
import com.smart.habits.util.Inputs;

import java.sql.*;
import java.util.Scanner;

public class HabitService {
    private final Scanner sc = new Scanner(System.in);

    public void addHabit(User user) {
        String name = Inputs.prompt("Habit name: ");
        String freq = Inputs.prompt("Frequency (DAILY/WEEKLY): ");
        try (Connection conn = Database.connect()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO habits(user_id,name,frequency) VALUES(?,?,?)");
            ps.setInt(1, user.getId());
            ps.setString(2, name);
            ps.setString(3, freq);
            ps.executeUpdate();
            System.out.println("Habit added.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listHabits(User user) {
        try (Connection conn = Database.connect()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM habits WHERE user_id=?");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            System.out.println("== Your Habits ==");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ") " + rs.getString("name") + " [" + rs.getString("frequency") + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logCompletion(User user) {
        int id = Integer.parseInt(Inputs.prompt("Enter habit id to log: "));
        try (Connection conn = Database.connect()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO completions(habit_id, date) VALUES(?,date('now'))");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Logged!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}