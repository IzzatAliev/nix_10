package ua.com.alevel.task1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertDate {

    public static final String FIRST_FORMAT = "yyyy/mm/dd";
    public static final String SECOND_FORMAT = "dd/mm/yyyy";
    public static final String THIRD_FORMAT = "mm-dd-yyyy";

    public static ArrayList<String> getListOfStringFromNumbers(ArrayList<DatesMenu> dates) {
        ArrayList<String> stringsOfNumbers = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).getDateFormat().equals(FIRST_FORMAT)) {
                if (checkFirstFormat(dates.get(i).getDateValue())) {
                    stringsOfNumbers.add(convertToStringFromNumbers(dates.get(i).getDateValue()));
                }
            } else if (dates.get(i).getDateFormat().equals(SECOND_FORMAT)) {
                if (checkSecondFormat(dates.get(i).getDateValue())) {
                    stringsOfNumbers.add(convertToStringFromNumbers(dates.get(i).getDateValue()));
                }
            } else if (dates.get(i).getDateFormat().equals(THIRD_FORMAT)) {
                if (checkThirdFormat(dates.get(i).getDateValue())) {
                    stringsOfNumbers.add(convertToStringFromNumbers(dates.get(i).getDateValue()));
                }
            }
        }
        return stringsOfNumbers;
    }

    private static String convertToStringFromNumbers(String dateValue) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dateValue);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    private static boolean checkFirstFormat(String stringDate) {
        String[] dateParts = new String[4];
        dateParts = stringDate.split("[/ ]", 4);
        if (dateParts.length >= 3) {
            long day;
            if (dateParts[2].equals("")) {
                day = -1;
            } else {
                day = Long.parseLong(dateParts[2]);
            }
            int month;
            if (dateParts[1].equals("")) {
                month = -1;
            } else {
                month = Integer.parseInt(dateParts[1]);
            }
            long year;
            if (dateParts[0].equals("")) {
                year = -1;
            } else {
                year = Long.parseLong(dateParts[0]);
            }
            if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                return isValidDate(year, month, day);
            }
            return false;
        }
        return false;
    }

    private static boolean checkSecondFormat(String stringDate) {
        if (stringDate.contains("/")) {
            String[] dateTimeParts = new String[4];
            dateTimeParts = stringDate.split("[/ ]", 4);
            if (dateTimeParts.length >= 3) {
                long day;
                if (dateTimeParts[0].equals("")) {
                    day = -1;
                } else {
                    day = Long.parseLong(dateTimeParts[0]);
                }
                int month;
                if (dateTimeParts[1].equals("")) {
                    month = -1;
                } else {
                    month = Integer.parseInt(dateTimeParts[1]);
                }
                long year;
                if (dateTimeParts[2].equals("")) {
                    year = -1;
                } else {
                    year = Long.parseLong(dateTimeParts[2]);
                }
                if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                    return isValidDate(year, month, day);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static boolean checkThirdFormat(String stringDate) {
        if (stringDate.contains("-")) {
            String[] dateTimeParts = new String[4];
            dateTimeParts = stringDate.split("[- ]", 4);
            if (dateTimeParts.length >= 3) {
                long day;
                if (dateTimeParts[1].equals("")) {
                    day = -1;
                } else {
                    day = Long.parseLong(dateTimeParts[1]);
                }
                int month;
                if (dateTimeParts[0].equals("")) {
                    month = -1;
                } else {
                    month = Integer.parseInt(dateTimeParts[0]);
                }
                long year;
                if (dateTimeParts[2].equals("")) {
                    year = -1;
                } else {
                    year = Long.parseLong(dateTimeParts[2]);
                }
                if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                    return isValidDate(year, month, day);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isLeapYear(long year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }

    public static boolean isValidDate(long year, int month, long day) {
        if ((month - 1) < 0 || (month - 1) >= 12) {
            return false;
        }
        int maxDate = daysInMonth[(month - 1)];
        if ((month - 1) == 1 && isLeapYear(year)) {
            maxDate = 29;
        }
        return day >= 1 && day <= maxDate;
    }
}