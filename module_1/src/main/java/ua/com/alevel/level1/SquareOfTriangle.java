package ua.com.alevel.level1;

import ua.com.alevel.UserInterface;

import java.util.Scanner;

public class SquareOfTriangle {

    public static void getSquare() {
        Scanner console = new Scanner(System.in);
        System.out.println("Type the coordinates of the point A");
        System.out.print("Type ax coordinate: ");
        int ax = console.nextInt();
        System.out.print("Type ay coordinate: ");
        int ay = console.nextInt();
        System.out.println("Type the coordinates of the point B");
        System.out.print("Type bx coordinate: ");
        int bx = console.nextInt();
        System.out.print("Type by coordinate: ");
        int by = console.nextInt();
        System.out.println("Type the coordinates of the point C");
        System.out.print("Type cx coordinate: ");
        int cx = console.nextInt();
        System.out.print("Type cy coordinate: ");
        int cy = console.nextInt();
        int AB = (int) Math.sqrt(Math.pow((bx - ax), 2) * Math.pow((by - ay), 2));
        int AC = (int) Math.sqrt(Math.pow((cx - ax), 2) * Math.pow((cy - ay), 2));
        int BC = (int) Math.sqrt(Math.pow((cx - bx), 2) * Math.pow((cy - by), 2));
        if ((AB + AC > BC) || (AC + BC > AB) || (AB + BC > AC)) {
            double Square = Math.abs((ax - bx) * (cy - by) - (cx - bx) * (ay - by)) / 2.0;
            System.out.println("Square of triangle ABC = " + Square);
        } else
            System.out.println("Idiot,the triangle with such coordinates does not exist");
        UserInterface.SelectFirstLevel();
    }
}
