package ua.com.alevel;

import ua.com.alevel.task1.DatesMenu;
import ua.com.alevel.task2.FindFirstUniqueName;

import java.util.ArrayList;
import java.util.Scanner;

public class UserFriendly {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner mainMenuChoice = new Scanner(System.in);
        while (true) {
            Scanner scanValue = new Scanner(System.in);
            printMenuText();
            String choice2 = mainMenuChoice.nextLine();
            switch (choice2) {
                case "1" -> {
                    DatesMenu datesMenu = new DatesMenu();
                    datesMenu.menu();
                }
                case "2" -> {
                    System.out.print("Enter amount of names:");
                    int size = getSize();
                    ArrayList<String> names = new ArrayList<>();
                    System.out.print("Enter names:");
                    for (int i = 0; i < size; i++) {
                        names.add(scanValue.nextLine());
                    }
                    String name = FindFirstUniqueName.findFirstUniqueName(names);
                    if (name.equals("")) {
                        System.out.println("There are no unique names in the string");
                    } else System.out.println(name);
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("You entered an invalid value. Enter again !!!");
            }
        }
    }

    public static int getSize() {
        Scanner scanChoice = new Scanner(System.in);
        int size;
        do {
            while (!scanChoice.hasNextInt()) {
                scanChoice.next();
                System.out.println("You have write incorrect value, try again");
            }
            size = scanChoice.nextInt();
            if (size <= 0) {
                System.out.println("You have write incorrect value, try again");
            }
        } while (size <= 0);
        return size;
    }

    private static void printMenuText() {
        System.out.println("Menu");
        System.out.println("Select");
        System.out.println("(1)Convert dates to numbers");
        System.out.println("(2)Find first unique name");
        System.out.println("(0)Exit");
    }
}
