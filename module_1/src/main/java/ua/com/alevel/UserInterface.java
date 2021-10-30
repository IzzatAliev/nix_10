package ua.com.alevel;

import ua.com.alevel.level1.AmountOfUniqueSymbols;
import ua.com.alevel.level1.HorseSteps;
import ua.com.alevel.level1.SquareOfTriangle;
import ua.com.alevel.level2.BinaryTree;
import ua.com.alevel.level2.Brackets;
import ua.com.alevel.level3.GameOfLife;

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
        UserInterface.SelectLevel();
    }

    public static void SelectLevel() {
        Scanner input = new Scanner(System.in);
        String level;
        System.out.println("Select a task level");
        System.out.println("(1)Level 1");
        System.out.println("(2)Level 2");
        System.out.println("(3)Level 3");
        System.out.println("(0)Exit");
        while ((level = input.nextLine()) != null) {
            switch (level) {
                case "1" -> UserInterface.SelectFirstLevel();

                case "2" -> UserInterface.SelectSecondLevel();

                case "3" -> UserInterface.SelectThirdLevel();

                case "0" -> System.exit(0);
            }
        }
    }

    public static void SelectFirstLevel() {
        Scanner input = new Scanner(System.in);
        String task;
        System.out.println();
        System.out.println("Select a level task");
        System.out.println("(1)AmountOfUniqueSymbol");
        System.out.println("(2)HorseSteps");
        System.out.println("(3)SquareOfTriangle");
        System.out.println("(0)Return to select a task level");
        while ((task = input.nextLine()) != null) {
            switch (task) {
                case "1" -> AmountOfUniqueSymbols.count();

                case "2" -> HorseSteps.reply();

                case "3" -> SquareOfTriangle.getSquare();

                case "0" -> UserInterface.SelectLevel();
            }
        }
    }

    public static void SelectSecondLevel() {
        Scanner input = new Scanner(System.in);
        String task;
        System.out.println();
        System.out.println("Select a level task");
        System.out.println("(1)Brackets");
        System.out.println("(2)Tree");
        System.out.println("(0)Return to select a task level");
        while ((task = input.nextLine()) != null) {
            switch (task) {
                case "1" -> Brackets.caller();

                case "2" -> BinaryTree.createBinaryTree();

                case "0" -> UserInterface.SelectLevel();
            }
        }
    }

    public static void SelectThirdLevel() {
        Scanner input = new Scanner(System.in);
        String task;
        System.out.println();
        System.out.println("Select a level task");
        System.out.println("(1)GameOfLife");
        System.out.println("(0)Return to select a task level");
        while ((task = input.nextLine()) != null) {
            switch (task) {
                case "1" -> GameOfLife.start();

                case "0" -> UserInterface.SelectLevel();
            }
        }
    }
}


