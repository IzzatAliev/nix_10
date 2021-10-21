package ua.com.alevel;

public final class StringReverseUtil {

     private StringReverseUtil() { }

    public static StringBuffer reverseString(String str) {
        StringBuffer sb = new StringBuffer(str.length());
        for (int i = str.length() - 1; i >= 0; i--) sb.append(str.charAt(i));
        return sb;
    }

    public static String reverseSubstring(String string, String substring) {
        String reverseSubstring = String.valueOf(reverseString(String.valueOf(substring)));
        return string.replaceAll((String.valueOf(substring)), reverseSubstring);
    }

    public static String reverseByIndexes(String string, int firstIndex, int lastIndex) {
        StringBuilder substring = new StringBuilder(string.length());
        for (int i = firstIndex; i <= lastIndex; i++) substring.append(string.charAt(i));
        return string.replaceAll((String.valueOf(substring)), String.valueOf(reverseString(String.valueOf(substring))));
    }
    public static String reverseBySymbols(String src, char firstSymbol, char lastSymbol) {
        int firstIndex = src.indexOf(firstSymbol);
        int lastIndex = src.indexOf(lastSymbol, firstIndex);
        String reversed = src;
        if (lastIndex != -1) {
            reversed = reverseByIndexes(src, firstIndex, lastIndex);
        }
        return reversed;
    }
    public static String reverseByStrings(String src, String firstString, String lastString) {
        int firstIndex = src.indexOf(firstString);
        int lastIndex = src.indexOf(lastString, firstIndex) + lastString.length() ;
        return reverseByIndexes(src, firstIndex, lastIndex);
    }
}
