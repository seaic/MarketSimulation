package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Noah Mannhart
 * @version 1.0
 * Main Method from our Application, which asked the user what he wants and where exactly to find the data
 */

public class Main extends Application {
    public static final Scanner in = new Scanner(System.in);
    public static Integer startBudget = 1;
    public static void main(String[] args) throws IOException {
        System.out.println("*".repeat(45));
        System.out.println("WELCOME TO MARKET SIMULATION");
        System.out.println("*".repeat(45));
        System.out.println("select the illustration:");
        System.out.println("1) Print chart only");
        System.out.println("2) Print chart as animation");
        System.out.println("> ");
        String input = in.nextLine();

        //Checks if User Input is 1 or 2
        if (input.equals("1") || input.equals("2")) {
            if (!FileManager.readFile()) {
                System.out.println("*".repeat(45));
                System.out.println("DATA COULD NOT BE READ SUCCESSFULLY!");
                System.out.println("*".repeat(45));
            }

            System.out.println("*".repeat(45));
            System.out.println("DATA COULD BE READ SUCCESSFULLY!");
            System.out.println("*".repeat(45));
            System.out.println("Select the start amount for the simulation in USD (e.g. 1000):");
            System.out.println("> ");
            startBudget = Integer.valueOf(in.nextLine());

            //If UserInput is 1 Line Chart will not be animated
            if (input.equals("1")) {
                CreateLineChart.main(args, String.valueOf(startBudget));
            }

            //if UserInput is 2 Line Chart will be animated
            if (input.equals("2")) {
                CreateLineChartAnimation.main(args, String.valueOf(startBudget));
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
    }
}
