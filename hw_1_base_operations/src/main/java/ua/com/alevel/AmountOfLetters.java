package ua.com.alevel;

import java.util.*;

public class AmountOfLetters {
    public void count() {

        System.out.print("Type in: ");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();

        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < x.length(); i++) {
            char a = x.charAt(i);
            if (Character.isLetter(a)) {
                Integer periodicity  = map.get(a);
                map.put(a, periodicity == null ? 1 : periodicity + 1);
            }
        }
        for (Map.Entry<Character, Integer> me : map.entrySet()) {
            System.out.print(me.getKey() + " - ");
            System.out.println(me.getValue());
        }
    }

}

