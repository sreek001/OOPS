package com.smart.habits.db;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:smart_habits.db";

    static {
        try (Connection conn = connect()) {
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT UNIQUE, password TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS habits(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, name TEXT, frequency TEXT)");
            st.execute("CREATE TABLE IF NOT EXISTS completions(id INTEGER PRIMARY KEY AUTOINCREMENT, habit_id INTEGER, date TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}