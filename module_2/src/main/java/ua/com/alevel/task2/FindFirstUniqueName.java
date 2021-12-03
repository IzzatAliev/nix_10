package ua.com.alevel.task2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FindFirstUniqueName {

    public static String findFirstUniqueName(ArrayList<String> names) {
        Integer ZERO = 0;
        final LinkedHashMap<String, Integer> map = new LinkedHashMap<>(names.size());
        for (String s : names) {
            Integer count = map.getOrDefault(s, ZERO);
            map.put(s, count + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return "";
    }
}
