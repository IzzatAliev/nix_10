package ua.com.alevel.task1;

import java.util.ArrayList;
import java.util.Scanner;

public class DatesMenu {

    String dateValue;
    String dateFormat;

    public void menu() {
        Scanner scanChoice = new Scanner(System.in);
        System.out.println("enter how many dates you want to enter?");
        int size = getSize();
        ArrayList<DatesMenu> dates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String dateFormat = chooseInputDateFormat();
            System.out.println("Enter date");
            String date = scanChoice.nextLine();
            DatesMenu datesMenu = new DatesMenu(date, dateFormat);
            dates.add(datesMenu);
        }
        ArrayList<String> stringsOfNumbers = ConvertDate.getListOfStringFromNumbers(dates);
        System.out.println("Result:");
        if (!stringsOfNumbers.isEmpty()) {
            for (String stringsOfNumber : stringsOfNumbers) {
                System.out.println(stringsOfNumber);
            }
        } else System.out.println("All dates were incorrect");
    }

    private String chooseInputDateFormat() {
        String dateFormat;
        Scanner scanChoiceFormat = new Scanner(System.in);
        System.out.println("Select date format(default format = yyyy/mm/dd:)");
        System.out.println("1 - yyyy/mm/dd (2020/04/05)");
        System.out.println("2 - dd/mm/yy (05/04/2020)");
        System.out.println("3 - mm-dd-yyyy (04-05-2020)");
        String choice = scanChoiceFormat.nextLine();
        switch (choice) {
            case "1" -> {
                dateFormat = "yyyy/mm/dd";
            }
            case "2" -> {
                dateFormat = "dd/mm/yyyy";
            }
            case "3" -> {
                dateFormat = "mm-dd-yyyy";
            }
            default -> dateFormat = "yyyy/mm/dd";
        }
        return dateFormat;
    }

    public int getSize() {
        Scanner scanChoice = new Scanner(System.in);
        int size;
        do {
            while (!scanChoice.hasNextInt()) {
                scanChoice.next();
                System.out.println("You have entered incorrect value, try again");
            }
            size = scanChoice.nextInt();
            if (size <= 0) {
                System.out.println("You have entered incorrect value, try again");
            }
        } while (size <= 0);
        return size;
    }

    public DatesMenu() {
    }
    public DatesMenu(String dateValue, String dateFormat) {
        this.dateValue = dateValue;
        this.dateFormat = dateFormat;
    }

    public String getDateValue() {
        return dateValue;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "dateFormat='" + dateFormat + '\'' +
                ", dateValue='" + dateValue + '\'' +
                '}';
    }
}