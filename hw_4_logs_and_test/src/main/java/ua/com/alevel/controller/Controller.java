package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            runNavigation();
            while ((input = reader.readLine()) != null) {
                crud(input);
                input = reader.readLine();
                if (input.equals("0")) {
                    System.exit(0);
                }
                crud(input);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("Select your option");
        System.out.println("(1)Student_CRUD_operations");
        System.out.println("(2)Course_CRUD_operations");
        System.out.println("(0)Exit");
        System.out.println();
    }

    private void crud(String input) {
        switch (input) {
            case "1" -> new StudentController().start();
            case "2" -> new CourseController().start();
            case "0" -> System.exit(0);
        }
        runNavigation();
    }
}