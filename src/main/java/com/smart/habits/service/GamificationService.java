package com.smart.habits.service;

import com.smart.habits.db.Database;
import com.smart.habits.model.User;

import java.sql.*;

public class GamificationService {
    public void viewStats(User user) {
        try (Connection conn = Database.connect()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as total FROM completions WHERE habit_id IN (SELECT id FROM habits WHERE user_id=?)");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            int total = rs.getInt("total");
            System.out.println("== Stats ==");
            System.out.println("Total completions: " + total);
            System.out.println("Points: " + (total * 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}