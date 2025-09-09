package com.smart.habits.util;

import java.util.Scanner;

public class Inputs {
    private static final Scanner sc = new Scanner(System.in);
    public static String prompt(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}