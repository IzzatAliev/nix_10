package ua.com.alevel;

import java.util.Scanner;

public class UserFriendly {

    private static final MathSet<Integer> numbers = new MathSet<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        runMainMenu();
    }

    public static void runMainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMainMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addNumbers(numbers);
                case 2 -> sortSet();
                case 3 -> findMaxOrMinValue();
                case 4 -> findAverageOrMedianValue();
                case 5 -> findJoinOrIntersectionOfSet();
                case 6 -> cutTheSet();
                case 7 -> clearTheSet();
                case 8 -> printTheSet();
                case 0 -> System.exit(0);
            }
        }
    }

    public static void printMainMenu() {
        System.out.println();
        System.out.println("Choose the main function:");
        System.out.println("(1)Add the numbers in set");
        System.out.println("(2)Sort set of numbers");
        System.out.println("(3)Find max or min value");
        System.out.println("(4)Find average or median");
        System.out.println("(5)Join or intersection of set");
        System.out.println("(6)Cut a part of set");
        System.out.println("(7)Clear set");
        System.out.println("(8)Print set");
        System.out.println("(0)Exit");
    }

    private static void addNumbers(MathSet<Integer> mathSet) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter the count of numbers: ");
            String count = sc.nextLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.print("Enter the number: ");
                String num = sc.nextLine();
                Integer entered = Integer.parseInt(num);
                mathSet.add(entered);
            }
            printTheSet();
        } catch (NumberFormatException e) {
            System.out.println("Invalid!!!\n" + e.getMessage());
        }
    }

    private static void sortSet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Sort by descending");
        System.out.println("(2)Sort by ascending");
        System.out.println("(0)Return to select main functions");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("(1)Sort fully");
                System.out.println("(2)Sort by two indices of the set");
                System.out.println("(0)Return to select sortSet");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> sortByDescending();
                    case 2 -> sortByDescendingBetweenIndices();
                    case 0 -> sortSet();
                }
            }
            case 2 -> {
                System.out.println("(1)Sort fully");
                System.out.println("(2)Sort by two indices of the set");
                System.out.println("(0)Return to select main functions");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> sortByAscending();
                    case 2 -> sortByAscendingBetweenIndices();
                    case 0 -> sortSet();
                }
            }
            case 0 -> runMainMenu();
        }
        runMainMenu();
    }

    private static void sortByDescending() {
        System.out.println("Your set: ");
        printTheSet();
        numbers.sortDesc();
        System.out.println("Sorted set: ");
        printTheSet();
    }

    private static void sortByDescendingBetweenIndices() {
        System.out.println("Your set: ");
        printTheSet();
        System.out.print("Enter the first index: ");
        int first = in.nextInt();
        System.out.print("Enter the second index: ");
        int second = in.nextInt();
        numbers.sortDesc(first, second);
        System.out.println("Sorted set: ");
        printTheSet();
    }

    private static void sortByAscending() {
        System.out.println("Your set: ");
        printTheSet();
        numbers.sortAsc();
        System.out.println("Sorted set: ");
        printTheSet();
    }

    private static void sortByAscendingBetweenIndices() {
        System.out.println("Your set: ");
        printTheSet();
        System.out.print("Enter the first index:");
        int first = in.nextInt();
        System.out.print("Enter the second index:");
        int second = in.nextInt();
        numbers.sortAsc(first, second);
        System.out.println("Sorted set: ");
        printTheSet();
    }

    private static void findMaxOrMinValue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Max value");
        System.out.println("(2)Min value");
        System.out.println("(0)Return to select main functions");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> System.out.println("Max value: " + numbers.getMax());
            case 2 -> System.out.println("Min value: " + numbers.getMin());
            case 0 -> runMainMenu();
        }
        runMainMenu();
    }

    private static void findAverageOrMedianValue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Find average");
        System.out.println("(2)Find median");
        System.out.println("(0)Return to select main functions");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> System.out.println("Average = " + numbers.getAverage());
            case 2 -> System.out.println("Median = " + numbers.getMedian());
            case 0 -> runMainMenu();
        }
        runMainMenu();
    }

    private static void findJoinOrIntersectionOfSet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1)Join");
        System.out.println("(2)Intersection");
        System.out.println("(0)Return to select main functions");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> joinMathSet();
            case 2 -> intersectionMathSet();
            case 0 -> runMainMenu();
        }
        runMainMenu();
    }

    private static void joinMathSet() {
        System.out.println("Create the new set");
        MathSet<Integer> ms = new MathSet<>();
        addNumbers(ms);
        numbers.join(new MathSet<Integer>(ms));
        printTheSet();
    }

    private static void intersectionMathSet() {
        System.out.println("Create new set");
        MathSet<Integer> ms = new MathSet<>();
        addNumbers(ms);
        numbers.intersection(new MathSet<Integer>(ms));
        printTheSet();
    }

    private static void cutTheSet() {
        try {
            printTheSet();
            System.out.print("Enter the first index: ");
            int first = in.nextInt();
            System.out.print("Enter the second index: ");
            int second = in.nextInt();
            System.out.print("Cut set: ");
            MathSet cutSet = numbers.cut(first, second);
            for (int i = 0; i < cutSet.getSize(); i++) {
                System.out.print(cutSet.get(i) + ",");
            }
            System.out.print("\b;");
            numbers.intersection(cutSet);
            System.out.println();
            printTheSet();
        } catch (Exception e) {
            System.out.println("Invalid!!! \n" + e.getMessage());
        }
        runMainMenu();
    }

    private static void clearTheSet() {
        numbers.clear();
        printTheSet();
        runMainMenu();
    }

    private static void printTheSet() {
        if (numbers.getSize() != 0) {
            System.out.println(numbers);
        } else {
            System.out.println("Set is empty!!!");
        }
    }
}