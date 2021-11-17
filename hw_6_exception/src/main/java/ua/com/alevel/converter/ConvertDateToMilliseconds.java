package ua.com.alevel.converter;

import ua.com.alevel.util.MyDate;

public class ConvertDateToMilliseconds {

    private static final long SECOND = 1000;
    private static final long MINUTE = SECOND * 60;
    private static final long HOUR = MINUTE * 60;
    private static final long DAY = HOUR * 24;
    private static final long YEAR = DAY * 365;
    private static final long LEAP_YEAR = YEAR + DAY;

    public static long secondsToMilliseconds(int seconds) {
        return seconds * SECOND;
    }

    public static long minutesToMilliseconds(int minutes) {
        return minutes * MINUTE;
    }

    public static long hoursToMilliseconds(int hours) {
        return hours * HOUR;
    }

    public static long dayToMilliseconds(int day) {
        return day * DAY;
    }

    public static long yearToMilliseconds(int year) {
        int leapYears = year / 4 - (year / 100) + year / 400;
        int commonYears = year - leapYears;
        long leapMilli = LEAP_YEAR * leapYears;
        long commonMilli = YEAR * commonYears;
        return commonMilli + leapMilli;
    }

    private static long monthToMilliseconds(int month, int year) {
        if (month != 0) {
            long monthMilliseconds = 0;
            month--;
            switch (month) {
                case 2:
                    if (isLeapYear(year)) {
                        monthMilliseconds += ConvertDateToMilliseconds.dayToMilliseconds(29);
                    } else {
                        monthMilliseconds += ConvertDateToMilliseconds.dayToMilliseconds(28);
                    }
                    monthMilliseconds += monthToMilliseconds(month, year);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    monthMilliseconds += ConvertDateToMilliseconds.dayToMilliseconds(30);
                    monthMilliseconds += monthToMilliseconds(month, year);
                    break;
                case 0:
                    break;
                default:
                    monthMilliseconds += ConvertDateToMilliseconds.dayToMilliseconds(31);
                    monthMilliseconds += monthToMilliseconds(month, year);
            }
            return monthMilliseconds;
        } else {
            return 0;
        }
    }

    public static long dateIntoMilliseconds(MyDate date) {
        long resultMilliseconds = 0;
        resultMilliseconds += yearToMilliseconds(date.getYear());
        resultMilliseconds += monthToMilliseconds(date.getMonth(), date.getYear());
        resultMilliseconds += dayToMilliseconds(date.getDay());
        resultMilliseconds += hoursToMilliseconds(date.getHours());
        resultMilliseconds += minutesToMilliseconds(date.getMinutes());
        resultMilliseconds += secondsToMilliseconds(date.getSeconds());
        resultMilliseconds += date.getMilliseconds();
        return resultMilliseconds;
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }
}
