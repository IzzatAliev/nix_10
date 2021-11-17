package ua.com.alevel;

import ua.com.alevel.converter.ConvertDateToMilliseconds;
import ua.com.alevel.converter.ConvertDateToString;
import ua.com.alevel.converter.ConvertMillisecondsToDate;
import ua.com.alevel.converter.ConvertStringToDate;
import ua.com.alevel.service.CheckFormat;
import ua.com.alevel.util.MyDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import static ua.com.alevel.service.CheckFormat.sortDates;
import static ua.com.alevel.util.MyDate.addMilliseconds;
import static ua.com.alevel.util.MyDate.subtractMilliseconds;

public class UserFriendly {

    private static final Scanner SC = new Scanner(System.in);
    private static final ConvertStringToDate CONVERT = new ConvertStringToDate();
    private static final ConvertDateToString CONVERT_DATE = new ConvertDateToString();

    public static void main(String[] args) {
        new UserFriendly().run();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose your event:");
        String position;
        try {
            printMainMenu();
            while ((position = reader.readLine()) != null) {
                runMainMenu(position);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                runMainMenu(position);
            }
        } catch (IOException e) {
            System.out.println("Exception: = " + e.getMessage());
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("<<MAIN MENU>>");
        System.out.println("(1)Difference between dates");
        System.out.println("(2)Addition");
        System.out.println("(3)Subtraction");
        System.out.println("(4)Sorting");
        System.out.println("(0)Exit");
        System.out.println();
    }

    private static void printFormat() {
        System.out.println("""
                Choose date input format:
                (1)dd/mm/yyyy (or dd-mm-yyyy)
                (2)m/d/yyyy (or m-d-yyyy)
                (3)mmm-d-yy (or Month d yyyy)
                (4)dd-mmm-yyyy (or dd Month yyyy)""");
    }

    private static void printAnotherFormat() {
        System.out.println("""
                Choose format of date:
                (1)dd/mm/yy 00:00:00:000
                (2)m/d/yy 00:00:00:000
                (3)Month d yyyy 00:00:00:000
                (4)dd Month yy 00:00:00:000""");
    }

    private void runMainMenu(String position) {
        switch (position) {
            case "1" -> differenceBetweenDates();
            case "2" -> addition();
            case "3" -> subtraction();
            case "4" -> sorting();
            case "0" -> System.exit(0);
        }
        printMainMenu();
    }

    public static void differenceBetweenDates() {
        String format;
        do {
            printFormat();
            format = SC.nextLine();
        } while (Integer.parseInt(format) > 4 || Integer.parseInt(format) <= 0);
        System.out.print("Enter the first date:");
        String firstDate;
        boolean isValid = true;
        do {
            if (!isValid) {
                System.out.println("Incorrect input, try again");
                System.out.print("Enter the first date:");
            }
            firstDate = SC.nextLine();
            isValid = false;
        } while (CheckFormat.check(firstDate, format) || CheckFormat.checkFormatOfTime(firstDate));
        MyDate myFirstDate = null;
        try {
            myFirstDate = CONVERT.stringToDate(firstDate, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Enter the second date:");
        String secondDate;
        boolean isValidValue = true;
        do {
            if (!isValidValue) {
                System.out.println("Incorrect input, try again");
                System.out.print("Enter the second date:");
            }
            secondDate = SC.nextLine();
            isValidValue = false;
        } while (CheckFormat.check(secondDate, format) || CheckFormat.checkFormatOfTime(secondDate));
        MyDate mySecondDate = null;
        try {
            mySecondDate = CONVERT.stringToDate(secondDate, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long result = MyDate.differenceBetweenDates(myFirstDate, mySecondDate);
        System.out.println("Result:");
        System.out.println("Years: " + (int) ConvertMillisecondsToDate.millisecondsToYears(result));
        System.out.println("Days: " + (int) ConvertMillisecondsToDate.millisecondsToDays(result));
        System.out.println("Hours:" + (int) ConvertMillisecondsToDate.millisecondsToHours(result));
        System.out.println("Minutes:" + (int) ConvertMillisecondsToDate.millisecondsToMinutes(result));
        System.out.println("Seconds:" + (int) ConvertMillisecondsToDate.millisecondsToSeconds(result));
        System.out.println();
    }

    public static void addition() {
        String format;
        do {
            printFormat();
            format = SC.nextLine();
        } while (Integer.parseInt(format) > 4 || Integer.parseInt(format) <= 0);
        System.out.print("Enter the date:");
        String date;
        boolean isValid = true;
        do {
            if (!isValid) {
                System.out.println("Incorrect input, try again");
                System.out.print("Enter the date:");
            }
            date = SC.nextLine();
            isValid = false;
        } while (CheckFormat.check(date, format) || CheckFormat.checkFormatOfTime(date));
        MyDate myDate = null;
        try {
            myDate = CONVERT.stringToDate(date, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            boolean flag = true;
            while (flag) {
                System.out.println();
                System.out.println("Select what you want to add:");
                choosingElement();
                long mil;
                String choice = SC.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter amount of years:");
                        String years = SC.nextLine();
                        mil = ConvertDateToMilliseconds.yearToMilliseconds(Integer.parseInt(years));
                        myDate = addMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "2" -> {
                        System.out.print("Enter amount of days:");
                        String days = SC.nextLine();
                        mil = ConvertDateToMilliseconds.dayToMilliseconds(Integer.parseInt(days));
                        myDate = addMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "3" -> {
                        System.out.print("Enter amount of hours:");
                        String hours = SC.nextLine();
                        mil = ConvertDateToMilliseconds.hoursToMilliseconds(Integer.parseInt(hours));
                        myDate = addMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "4" -> {
                        System.out.print("Enter amount of minutes:");
                        String minutes = SC.nextLine();
                        mil = ConvertDateToMilliseconds.minutesToMilliseconds(Integer.parseInt(minutes));
                        myDate = addMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "5" -> {
                        System.out.print("Enter amount of seconds:");
                        String seconds = SC.nextLine();
                        mil = ConvertDateToMilliseconds.secondsToMilliseconds(Integer.parseInt(seconds));
                        myDate = addMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "6" -> {
                        System.out.print("Enter amount of milliseconds:");
                        String milliseconds = SC.nextLine();
                        myDate = addMilliseconds(myDate, Integer.parseInt(milliseconds));
                        printDateFormat(myDate);
                    }
                    case "0" -> flag = false;
                    default -> System.out.println("Incorrect input!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Invalid!!!");
        }
    }

    public static void subtraction() {
        String format;
        do {
            printFormat();
            format = SC.nextLine();
        } while (Integer.parseInt(format) > 4 || Integer.parseInt(format) <= 0);
        System.out.print("Enter the date:");
        String date;
        boolean isValid = true;
        do {
            if (!isValid) {
                System.out.println("Incorrect input, try again");
                System.out.print("Enter the date:");
            }
            date = SC.nextLine();
            isValid = false;
        } while (CheckFormat.check(date, format) || CheckFormat.checkFormatOfTime(date));
        MyDate myDate = null;
        try {
            myDate = CONVERT.stringToDate(date, format);
            System.out.println(myDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            boolean flag = true;
            while (flag) {
                System.out.println();
                System.out.println("Select what you want to subtract:");
                choosingElement();
                long mil;
                String choice = SC.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter amount of years:");
                        String years = SC.nextLine();
                        mil = ConvertDateToMilliseconds.yearToMilliseconds(Integer.parseInt(years));
                        myDate = subtractMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "2" -> {
                        System.out.print("Enter amount of days:");
                        String days = SC.nextLine();
                        mil = ConvertDateToMilliseconds.dayToMilliseconds(Integer.parseInt(days));
                        myDate = subtractMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "3" -> {
                        System.out.print("Enter amount of hours:");
                        String hours = SC.nextLine();
                        mil = ConvertDateToMilliseconds.hoursToMilliseconds(Integer.parseInt(hours));
                        myDate = subtractMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "4" -> {
                        System.out.print("Enter amount of minutes:");
                        String minutes = SC.nextLine();
                        mil = ConvertDateToMilliseconds.minutesToMilliseconds(Integer.parseInt(minutes));
                        myDate = subtractMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "5" -> {
                        System.out.print("Enter amount of seconds:");
                        String seconds = SC.nextLine();
                        mil = ConvertDateToMilliseconds.secondsToMilliseconds(Integer.parseInt(seconds));
                        myDate = subtractMilliseconds(myDate, mil);
                        printDateFormat(myDate);
                    }
                    case "6" -> {
                        System.out.print("Enter amount of milliseconds:");
                        String milliseconds = SC.nextLine();
                        myDate = subtractMilliseconds(myDate, Integer.parseInt(milliseconds));
                        printDateFormat(myDate);
                    }
                    case "0" -> flag = false;
                    default -> System.out.println("Incorrect input!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Invalid!!!");
        }
    }

    private static void choosingElement() {
        System.out.println();
        System.out.println("(1)Years");
        System.out.println("(2)Days");
        System.out.println("(3)Hours");
        System.out.println("(4)Minutes");
        System.out.println("(5)Seconds");
        System.out.println("(6)Milliseconds");
        System.out.println("(0)Return to MainMenu");
        System.out.println();
    }

    private static void printDateFormat(MyDate date) {
        printAnotherFormat();
        String format = SC.nextLine();
        System.out.println("Date:");
        System.out.print(CONVERT_DATE.dateToString(date, format));
    }

    public static void sorting() throws NumberFormatException {
        ArrayList<MyDate> dates = new ArrayList<>();
        String format;
        do {
            printFormat();
            format = SC.nextLine();
        } while (Integer.parseInt(format) > 4 || Integer.parseInt(format) <= 0);
        try {
            System.out.print("Enter amount of dates:");
            String count = SC.nextLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.print("Enter the date:");
                String date;
                boolean isValid = true;
                do {
                    if (!isValid) {
                        System.out.println("Incorrect input, try again");
                        System.out.print("Enter amount of dates:");
                    }
                    date = SC.nextLine();
                    isValid = false;
                } while (CheckFormat.check(date, format) || CheckFormat.checkFormatOfTime(date));
                MyDate myDate = null;
                try {
                    myDate = CONVERT.stringToDate(date, format);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dates.add(myDate);
            }
            System.out.println("(1)Descending sort");
            System.out.println("(2)Ascending sort");
            String choiceSort = SC.nextLine();
            ArrayList<MyDate> sortedDates = sortDates(dates, choiceSort);
            printAnotherFormat();
            ;
            String choiceFormat = SC.nextLine();
            System.out.println("The dates:");
            for (MyDate d : sortedDates) {
                System.out.print(CONVERT_DATE.dateToString(d, choiceFormat));
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Invalid!!!");
        }
    }
}