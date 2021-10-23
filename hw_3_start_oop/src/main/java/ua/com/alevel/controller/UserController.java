package ua.com.alevel.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {

    private final UserService userService = new UserService();

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select the option:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("""
                (1)Create a user;
                (2)Update a user;
                (3)Delete the user;
                (4)Find the user by Id;
                (5)Return all users;
                (0)Exit;""");
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll(reader);
                break;
        }
        runNavigation();
        if (position.equals("0")) {
            System.exit(0);
        }
    }

    private void create(BufferedReader reader) {
        try {
            System.out.print("Enter the name:");
            String name = reader.readLine();
            System.out.print("Enter the age: ");
            String age = reader.readLine();
            System.out.print("Enter the email: ");
            String email = reader.readLine();
            User user = new User();
            user.setName(name);
            user.setAge(age);
            user.setEmail(email);
            userService.create(user);
        } catch (IOException e) {
            System.out.println("Problem is: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.print("Enter the Id: ");
            String id = reader.readLine();
            System.out.print("Enter the name: ");
            String name = reader.readLine();
            System.out.print("Enter the age: ");
            String age = reader.readLine();
            System.out.print("Enter the email: ");
            String email = reader.readLine();
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setEmail(email);
            userService.update(user);
        } catch (IOException e) {
            System.out.println("Problem is: " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.print("Enter the Id: ");
            String id = reader.readLine();
            userService.delete(id);
        } catch (IOException e) {
            System.out.println("Problem is: " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.print("Enter the Id: ");
            String id = reader.readLine();
            User user = userService.findById(id);
            if (user != null) {
                System.out.println("User: " + user);
            } else {
                System.out.println("There is not such user!");
            }
        } catch (IOException e) {
            System.out.println("Problem is: " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        User[] users = userService.findAll();
        for (User user : users) {
            if (user != null) {
                System.out.println("User: " + user);
            }
        }
        if (users.length == 0) {
            System.out.println("There is no such user!");
        }
    }
}
