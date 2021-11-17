package ua.com.alevel.converter;

import ua.com.alevel.util.MyDate;

public class ConvertStringToDate {

    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    public MyDate stringToDate(String date, String choice) {
        MyDate myDate = new MyDate();
        switch (choice) {
            case "1" -> myDate = firstFormat(date);
            case "2" -> myDate = secondFormat(date);
            case "3" -> myDate = thirdFormat(date);
            case "4" -> myDate = fourthFormat(date);
            default -> System.out.println("Incorrect input");
        }
        return myDate;
    }

    public static MyDate firstFormat(String stringDate) {
        MyDate myDate = new MyDate();
        String delimiter;
        if (stringDate.contains("/")) {
            delimiter = "/";
        } else {
            delimiter = "-";
        }
        String[] split = switch (delimiter) {
            case "/" -> stringDate.split("[/ ]", 4);
            case "-" -> stringDate.split("[- ]", 4);
            default -> new String[4];
        };
        try {
            if (!split[0].equals("")) {
                myDate.setDay(Integer.parseInt(split[0]));
            } else {
                myDate.setDay(1);
            }
            if (!split[1].equals("")) {
                myDate.setMonth(Integer.parseInt(split[1]));
            } else {
                myDate.setMonth(1);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }
            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public static MyDate secondFormat(String stringDate) {
        MyDate myDate = new MyDate();
        String delimiter;
        if (stringDate.contains("/")) {
            delimiter = "/";
        } else {
            delimiter = "-";
        }
        String[] split = switch (delimiter) {
            case "/" -> stringDate.split("[/ ]", 4);
            case "-" -> stringDate.split("[- ]", 4);
            default -> new String[4];
        };
        try {
            if (!split[0].equals("")) {
                myDate.setMonth(Integer.parseInt(split[0]));
            } else {
                myDate.setMonth(1);
            }
            if (!split[1].equals("")) {
                myDate.setDay(Integer.parseInt(split[1]));
            } else {
                myDate.setDay(1);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }
            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public static MyDate thirdFormat(String stringDate) {
        MyDate myDate = new MyDate();
        String[] split;
        if (stringDate.contains("-")) {
            split = stringDate.split("[- ]", 4);
        } else {
            split = stringDate.split("[ ]", 4);
        }
        try {
            int month;
            if (!split[0].equals("")) {
                for (int i = 0; i < MONTHS.length; i++) {
                    if (split[0].equals(MONTHS[i])) {
                        month = i + 1;
                        myDate.setMonth(month);
                    }
                }
            } else {
                myDate.setMonth(1);
            }
            if (!split[1].equals("")) {
                myDate.setDay(Integer.parseInt(split[1]));
            } else {
                myDate.setDay(1);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }

            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public static MyDate fourthFormat(String stringDate) {
        MyDate myDate = new MyDate();
        String[] split;
        if (stringDate.contains("-")) {
            split = stringDate.split("[- ]", 4);
        } else {
            split = stringDate.split("[ ]", 4);
        }
        try {
            if (!split[0].equals("")) {
                myDate.setDay(Integer.parseInt(split[0]));
            } else {
                myDate.setDay(1);
            }
            int month;
            if (!split[1].equals("")) {
                for (int i = 0; i < MONTHS.length; i++) {
                    if (split[1].equals(MONTHS[i])) {
                        month = i + 1;
                        myDate.setMonth(month);
                    }
                }
            } else {
                myDate.setMonth(1);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }
            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public static void setTime(MyDate date, String time) {
        String[] splitter = time.split(":");
        try {
            for (int i = 0; i < splitter.length; i++) {
                switch (i) {
                    case 0 -> date.setHours(Integer.parseInt(splitter[0]));
                    case 1 -> date.setMinutes(Integer.parseInt(splitter[1]));
                    case 2 -> date.setSeconds(Integer.parseInt(splitter[2]));
                    case 3 -> date.setMilliseconds(Integer.parseInt(splitter[3]));
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}