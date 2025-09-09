package com.smart.habits;

import com.smart.habits.service.*;
import com.smart.habits.model.*;
import com.smart.habits.util.*;

import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final HabitService habitService = new HabitService();
    private static final GamificationService gameService = new GamificationService();

    public static void main(String[] args) {
        System.out.println("=== Welcome to Smart Habit Tracker ===");
        while (true) {
            System.out.println("1) Register  2) Login  0) Exit");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> register();
                case "2" -> login();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void register() {
        System.out.println("== Register ==");
        String name = Inputs.prompt("Name: ");
        String email = Inputs.prompt("Email: ");
        String password = Inputs.prompt("Password: ");
        if (authService.register(name, email, password)) {
            System.out.println("Registered successfully!");
        } else {
            System.out.println("Email already exists.");
        }
    }

    private static void login() {
        System.out.println("== Login ==");
        String email = Inputs.prompt("Email: ");
        String password = Inputs.prompt("Password: ");
        User user = authService.login(email, password);
        if (user != null) {
            System.out.println("Welcome, " + user.getName() + "!");
            home(user);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void home(User user) {
        while (true) {
            System.out.println("\n=== Home ===");
            System.out.println("1) Add Habit  2) List Habits  3) Log Habit Completion  4) View Stats  0) Logout");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> habitService.addHabit(user);
                case "2" -> habitService.listHabits(user);
                case "3" -> habitService.logCompletion(user);
                case "4" -> gameService.viewStats(user);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}