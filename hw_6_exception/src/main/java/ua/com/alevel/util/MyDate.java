package ua.com.alevel.util;

import ua.com.alevel.converter.ConvertDateToMilliseconds;
import ua.com.alevel.converter.ConvertMillisecondsToDate;

public class MyDate {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int millisecond;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hour;
    }

    public void setHours(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minute;
    }

    public void setMinutes(int minute) {
        this.minute = minute;
    }

    public int getSeconds() {
        return second;
    }

    public void setSeconds(int second) {
        this.second = second;
    }

    public int getMilliseconds() {
        return millisecond;
    }

    public void setMilliseconds(int millisecond) {
        this.millisecond = millisecond;
    }

    private static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public MyDate() {
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }

    public static boolean isValidDate(int year, int month, int day) {
        boolean isValid = true;
        int m = month - 1;

        if (m < 0 || m >= 12) {
            isValid = false;
            System.out.println("date is not valid");
        }

        int maxDate = daysInMonth[m];
        if (m == 1 && isLeapYear(year)) {
            maxDate = 29;
        }

        if (day < 1 || day > maxDate) {
            isValid = false;
            System.out.println("date is not valid, max date in this month is " + maxDate);
        }
        return isValid;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year + " " +
                hour + ":" + minute + ":" + second + ":" + millisecond;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyDate)) {
            return false;
        }
        MyDate date = (MyDate) obj;
        return (this.year == date.year) && (this.month == date.month) && (this.day == date.day);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + year;
        result = 31 * result + month;
        result = 31 * result + day;

        return result;
    }

    public static long differenceBetweenDates(MyDate firstDate, MyDate secondDate) {
        long firstDateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(firstDate);
        long secondDateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(secondDate);
        return Math.abs(firstDateIntoMilliseconds - secondDateIntoMilliseconds);
    }

    public static MyDate addMilliseconds(MyDate date, long millisecond) {
        long dateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(date);
        return ConvertMillisecondsToDate.millisecondsToDate(dateIntoMilliseconds + millisecond);
    }

    public static MyDate subtractMilliseconds(MyDate date, long millisecond) {
        long dateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(date);
        return ConvertMillisecondsToDate.millisecondsToDate(dateIntoMilliseconds - millisecond);
    }
}
