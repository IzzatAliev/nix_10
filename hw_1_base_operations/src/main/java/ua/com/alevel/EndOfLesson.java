package ua.com.alevel;

import java.util.Scanner;

public class EndOfLesson {
    public void bell() {

        int lessonTime = 45;
        int longBreak = 15;
        int shortBreak = 5;
        int oneHour = 60;
        int startLesson = 9;

        System.out.print("Type in a number from 1 to 10: ");
        Scanner sc = new Scanner(System.in);
        int lesson = sc.nextInt();

        lesson = lesson * lessonTime + (lesson/2) * shortBreak + ((lesson+1) / 2-1) * longBreak;
        int hours = lesson / oneHour + startLesson;
        int minutes = lesson % oneHour;
        System.out.println(hours + ":" + minutes);
    }
}
