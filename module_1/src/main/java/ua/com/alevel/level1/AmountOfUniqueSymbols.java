package ua.com.alevel.level1;

import ua.com.alevel.UserInterface;

import java.util.Arrays;
import java.util.Scanner;

public class AmountOfUniqueSymbols {

    public static void count() {
        System.out.println("Type your string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.replaceAll("[^\\d.]", "");
        char[] array = input.toCharArray();
        int count = 0;
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != array[i + 1]) {
                ++count;
            }
        }
        count++;
        System.out.println("Count of unique symbols = " + count);
        UserInterface.SelectFirstLevel();
    }
}

