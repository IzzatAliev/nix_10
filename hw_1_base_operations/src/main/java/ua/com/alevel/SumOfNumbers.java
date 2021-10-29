package ua.com.alevel;

import java.util.Scanner;

public class SumOfNumbers {

    public void sumsUp() {
        System.out.print("Type in: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int sum = 0;
        for(int i = 0; i<input.length(); i++){
            char a = input.charAt(i);
            if(Character.isDigit(a))
                sum = sum + Character.getNumericValue(a);
        }
        System.out.println(sum);
    }
}
