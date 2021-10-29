package ua.com.alevel;

import java.util.Scanner;

public class UserFriendlyMain {

    public static void main(String[] args) {
        String firstInput = "Choose a variety of reverse:\n";
        String welcome = """
                            (1)ReverseString;
                            (2)ReverseSubstring;
                            (3)ReverseByIndexes;
                            (4)ReverseBySymbols;
                            (5)ReverseByStrings;\n""" ;

        String appear = "Choose a variety of reverse again or click on 0 for exit";
        System.out.print(firstInput+welcome);
        Scanner in = new Scanner(System.in);
        String text,subtext,event;
        String first,second;
        int a,b;
        while ((event = in.nextLine()) != null) {
            switch (event) {
                case "1" -> {
                    System.out.print("Type your text: ");
                    text = in.nextLine();
                    StringBuffer x = StringReverseUtil.reverseString(text);
                    System.out.println(x);
                    System.out.println(welcome+appear);
                }
                case "2" -> {
                    System.out.print("Type your text: ");
                    text = in.nextLine();
                    System.out.print("Type your subtext: ");
                    subtext = in.nextLine();
                    String x = StringReverseUtil.reverseSubstring(text,subtext);
                    System.out.println(x);
                    System.out.println(welcome+appear);
                }
                case "3" -> {
                    System.out.print("Type your text: ");
                    text = in.nextLine();
                    System.out.print("Type firstIndex: ");
                    a = in.nextInt();
                    System.out.print("Type lastIndex: ");
                    b = in.nextInt();
                    String x = StringReverseUtil.reverseByIndexes(text,a,b);
                    System.out.println(x);
                    System.out.println(welcome+appear);
                }
                case "4" -> {
                    System.out.print("Type your text: ");
                    text = in.nextLine();
                    System.out.print("Type firstSymbol: ");
                    first = in.nextLine();
                    System.out.print("Type lastSymbol: ");
                    second = in.nextLine();
                    String x = StringReverseUtil.reverseBySymbols(text,first.charAt(0),second.charAt(0));
                    System.out.println(x);
                    System.out.println(welcome+appear);
                }
                case "5" -> {
                    System.out.print("Type your text: ");
                    text = in.nextLine();
                    System.out.print("Type firstString: ");
                    first = in.nextLine();
                    System.out.print("Type lastString: ");
                    second = in.nextLine();
                    String x = StringReverseUtil.reverseByStrings(text,first,second);
                    System.out.println(x);
                    System.out.println(welcome+appear);
                }
                case "0" -> System.exit(0);
            }
        }
    }
}
