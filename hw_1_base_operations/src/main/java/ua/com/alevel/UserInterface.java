package ua.com.alevel;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SumOfNumbers first = new SumOfNumbers();
        AmountOfLetters second = new AmountOfLetters();
        EndOfLesson third = new EndOfLesson();

        String welcome = ("""
                             Choose your event:
                             (1)SumOfNumbers;
                             (2)AmountOfLetters;
                             (3)EndOfLesson;""");
        System.out.println(welcome);

        String event;
            while ((event = scanner.nextLine()) != null) {
                switch (event) {
                    case "1" -> {
                        first.sumsUp();
                        System.out.println("Choose your event again or click on 0 for exit");
                    }
                    case "2" -> {
                        second.count();
                        System.out.println("Choose your event again or click on 0 for exit");
                    }
                    case "3" -> {
                        third.bell();
                        System.out.println("Choose your event again or click on 0 for exit");
                    }
                    case "0" -> System.exit(0);
                }
            }

    }
}