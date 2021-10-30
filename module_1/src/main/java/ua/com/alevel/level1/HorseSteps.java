package ua.com.alevel.level1;

import ua.com.alevel.UserInterface;

import java.util.Scanner;

public class HorseSteps {

    public static void reply() {
        System.out.println("There is a chess table 8x8, you should to choose some position on this table!");
        HorseSteps.steps();
    }

    public static void steps() {
        Scanner console = new Scanner(System.in);
        System.out.println("Type your current position of horse on this chess table!");
        System.out.print("Enter x: ");
        int x1 = console.nextInt();
        System.out.print("Enter y: ");
        int y1 = console.nextInt();
        System.out.println("Type the position where you want to go");
        System.out.print("Enter x: ");
        int x2 = console.nextInt();
        System.out.print("Enter y: ");
        int y2 = console.nextInt();
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
            System.out.println("Movement is possible");
        } else {
            System.out.println("Movement is not possible\ntry again");
            System.out.println();
            HorseSteps.reply();
        }
        UserInterface.SelectFirstLevel();
    }
}
