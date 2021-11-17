package ua.com.alevel.service;

import ua.com.alevel.converter.ConvertDateToMilliseconds;
import ua.com.alevel.util.MyDate;

import java.util.ArrayList;

public class CheckFormat {

    private static final String[] MONTHS = {"january", "february",
            "march", "april", "may", "june", "july",
            "august", "september", "october", "november", "december"};

    public static boolean check(String date, String choice) {
        boolean isValid = false;
        switch (choice) {
            case "1" -> isValid = firstFormat(date);
            case "2" -> isValid = secondFormat(date);
            case "3" -> isValid = thirdFormat(date);
            case "4" -> isValid = fourthFormat(date);
            default -> System.out.println("Incorrect input");
        }
        return !isValid;
    }

    public static boolean firstFormat(String stringDate) {
        try {
            if (stringDate.contains("/") || stringDate.contains("-")) {
                String delimiter;
                if (stringDate.contains("/")) {
                    delimiter = "/";
                } else {
                    delimiter = "-";
                }
                String[] splitArr = switch (delimiter) {
                    case "/" -> stringDate.split("[/ ]", 4);
                    case "-" -> stringDate.split("[- ]", 4);
                    default -> new String[4];
                };
                if (splitArr.length >= 3) {
                    int day;
                    if (splitArr[0].equals("")) {
                        day = 1;
                    } else {
                        day = Integer.parseInt(splitArr[0]);
                    }
                    int month;
                    if (splitArr[1].equals("")) {
                        month = 1;
                    } else {
                        month = Integer.parseInt(splitArr[1]);
                    }
                    int year;
                    if (splitArr[2].equals("")) {
                        year = 0;
                    } else {
                        year = Integer.parseInt(splitArr[2]);
                    }
                    if ((splitArr[0].length() == 2 || splitArr[0].matches(""))
                            && (splitArr[1].length() == 2 || splitArr[1].matches(""))) {
                        if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                            return MyDate.isValidDate(year, month, day);
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean secondFormat(String stringDate) {
        try {
            if (stringDate.contains("/") || stringDate.contains("-")) {
                String delimiter;
                if (stringDate.contains("/")) {
                    delimiter = "/";
                } else {
                    delimiter = "-";
                }
                String[] splitArr = switch (delimiter) {
                    case "/" -> stringDate.split("[/ ]", 4);
                    case "-" -> stringDate.split("[- ]", 4);
                    default -> new String[4];
                };
                if (splitArr.length >= 3) {
                    int day;
                    if (splitArr[1].equals("")) {
                        day = 1;
                    } else {
                        day = Integer.parseInt(splitArr[1]);
                    }
                    int month;
                    if (splitArr[0].equals("")) {
                        month = 1;
                    } else {
                        month = Integer.parseInt(splitArr[0]);
                    }
                    int year;
                    if (splitArr[2].equals("")) {
                        year = 0;
                    } else {
                        year = Integer.parseInt(splitArr[2]);
                    }
                    if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                        return MyDate.isValidDate(year, month, day);
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean thirdFormat(String stringDate) {
        try {
            int month;
            String[] splitArr;
            if (stringDate.contains("-")) {
                splitArr = stringDate.split("[- ]", 4);
            } else {
                splitArr = stringDate.split("[ ]", 4);
            }
            if (splitArr.length >= 3) {
                int day;
                if (splitArr[1].equals("")) {
                    day = 1;
                } else {
                    day = Integer.parseInt(splitArr[1]);
                }
                int year;
                if (splitArr[2].equals("")) {
                    year = 0;
                } else {
                    year = Integer.parseInt(splitArr[2]);
                }
                if (splitArr[0].equals("")) {
                    month = 1;
                    return MyDate.isValidDate(year, month, day);
                } else {
                    for (int i = 0; i < MONTHS.length; i++) {
                        if (splitArr[0].equalsIgnoreCase(MONTHS[i])) {
                            month = i + 1;
                            return MyDate.isValidDate(year, month, day);
                        }
                    }
                }
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean fourthFormat(String stringDate) {
        try {
            String[] splitArr;
            if (stringDate.contains("-")) {
                splitArr = stringDate.split("[- ]", 4);
            } else {
                splitArr = stringDate.split("[ ]", 4);
            }
            if (splitArr.length >= 3) {
                int day;
                if (splitArr[0].equals("")) {
                    day = 1;
                } else {
                    day = Integer.parseInt(splitArr[0]);
                }
                int year;
                if (splitArr[2].equals("")) {
                    year = 0;
                } else {
                    year = Integer.parseInt(splitArr[2]);
                }
                int month;
                if (splitArr[1].equals("")) {
                    month = 1;
                    return MyDate.isValidDate(year, month, day);
                } else {
                    if (splitArr[0].length() == 2 || splitArr[0].matches("")) {
                        for (int i = 0; i < MONTHS.length; i++) {
                            if (splitArr[1].equalsIgnoreCase(MONTHS[i])) {
                                month = i + 1;
                                return MyDate.isValidDate(year, month, day);
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkFormatOfTime(String input) {
        boolean flag = true;
        try {
            if (input.contains("/") || input.contains("-")) {
                String delimiter;
                if (input.contains("/")) delimiter = "/";
                else delimiter = "-";
                String[] split = switch (delimiter) {
                    case "/" -> input.split("[/ ]");
                    case "-" -> input.split("[- ]");
                    default -> new String[4];
                };
                if (split.length > 3) {
                    String[] splitTime = split[3].split(":");
                    if (!splitTime[0].equals("")) {
                        if (Integer.parseInt(splitTime[0]) > 23 || Integer.parseInt(splitTime[0]) < 0) {
                            flag = false;
                        }
                    }
                    if (splitTime.length > 1) {
                        if (Integer.parseInt(splitTime[1]) > 59 || Integer.parseInt(splitTime[1]) < 0) {
                            flag = false;
                        }
                    }
                    if (splitTime.length > 2) {
                        if (Integer.parseInt(splitTime[2]) > 59 || Integer.parseInt(splitTime[2]) < 0) {
                            flag = false;
                        }
                    }
                    if (splitTime.length > 3) {
                        if (Integer.parseInt(splitTime[3]) > 999 || Integer.parseInt(splitTime[3]) < 0) {
                            flag = false;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return !flag;
    }

    public static ArrayList<MyDate> sortDates(ArrayList<MyDate> dates, String choice) {
        boolean isSorted;
        int size = dates.size();
        switch (choice) {
            case "1":
                System.out.println("Descending sort");
                do {
                    isSorted = true;
                    for (int i = 0; i < size - 1; i++) {
                        if (ConvertDateToMilliseconds.dateIntoMilliseconds(dates.get(i)) < ConvertDateToMilliseconds.dateIntoMilliseconds(dates.get(i + 1))) {
                            MyDate date = dates.get(i);
                            dates.set(i, dates.get(i + 1));
                            dates.set(i + 1, date);
                            isSorted = false;
                        }
                    }
                } while (!isSorted);
                return dates;
            case "2":
                System.out.println("Ascending sort");
                do {
                    isSorted = true;
                    for (int i = 0; i < size - 1; i++) {
                        if (ConvertDateToMilliseconds.dateIntoMilliseconds(dates.get(i)) > ConvertDateToMilliseconds.dateIntoMilliseconds(dates.get(i + 1))) {
                            MyDate date = dates.get(i);
                            dates.set(i, dates.get(i + 1));
                            dates.set(i + 1, date);
                            isSorted = false;
                        }
                    }
                } while (!isSorted);
                return dates;
            default:
                return dates;
        }
    }
}