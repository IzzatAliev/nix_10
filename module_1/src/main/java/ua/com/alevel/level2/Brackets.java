package ua.com.alevel.level2;

import ua.com.alevel.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Brackets {

    private static boolean balanceOfTwoBrackets(String bracket1, String bracket2) {
        return bracket1.equals("(") && bracket2.equals(")")
                || bracket1.equals("{") && bracket2.equals("}")
                || bracket1.equals("[") && bracket2.equals("]");
    }

    public static void caller() {
        System.out.println("Enter your string with brackets");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Result: " + Brackets.balanced(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserInterface.SelectSecondLevel();
    }

    public static boolean balanced(String expr) {
        if (expr == null) return false;
        if (expr.isBlank()) return true;
        Stack<String> stack = new Stack<>();
        String[] rawBrackets = expr.split("[^\\[{(\\])}]");
        String openBrackets = "{([";
        StringBuilder builder = new StringBuilder();
        for (String s : rawBrackets) {
            builder.append(s);
        }
        String[] brackets = builder.toString().split("");
        for (String bracket : brackets) {
            if (openBrackets.contains(bracket)) {
                stack.push(bracket);
            } else {
                String lastBracket = stack.pop();
                if (!balanceOfTwoBrackets(lastBracket, bracket))
                    return false;
            }
        }
        return true;
    }
}