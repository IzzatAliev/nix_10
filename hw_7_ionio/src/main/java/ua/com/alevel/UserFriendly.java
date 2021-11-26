package ua.com.alevel;

import ua.com.alevel.controller.AuthorController;
import ua.com.alevel.controller.BookController;
import ua.com.alevel.controller.ConnectionController;

import java.util.Scanner;

public class UserFriendly {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean continueEnter = true;
        String userChoice;
        while (continueEnter) {
            mainMenu();
            System.out.print("Enter number: ");
            userChoice = in.nextLine();
            switch (userChoice) {
                case "1" -> AuthorController.getInstance().createAuthor();
                case "2" -> {
                    AuthorController.getInstance().findAllAuthors();
                    AuthorController.getInstance().updateAuthor();
                }
                case "3" -> {
                    AuthorController.getInstance().findAllAuthors();
                    AuthorController.getInstance().findAuthorBooks();
                }
                case "4" -> AuthorController.getInstance().findAllAuthors();
                case "5" -> {
                    AuthorController.getInstance().findAllAuthors();
                    AuthorController.getInstance().deleteAuthor();
                }
                case "6" -> BookController.getInstance().createBook();
                case "7" -> {
                    BookController.getInstance().findAllBooks();
                    BookController.getInstance().updateBook();
                }
                case "8" -> {
                    BookController.getInstance().findAllBooks();
                    BookController.getInstance().findBookAuthors();
                }
                case "9" -> BookController.getInstance().findAllBooks();
                case "10" -> {
                    BookController.getInstance().findAllBooks();
                    BookController.getInstance().deleteBook();
                }
                case "11" -> {
                    AuthorController.getInstance().findAllAuthors();
                    ConnectionController.addBook();
                }
                case "12" -> {
                    BookController.getInstance().findAllBooks();
                    ConnectionController.addAuthor();
                }
                case "0" -> continueEnter = false;
                default -> System.out.println("Incorrect input! Please, try again.");
            }
        }
    }

    public static void mainMenu() {
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("(1)Create author");
        System.out.println("(2)Update author");
        System.out.println("(3)Get author`s books");
        System.out.println("(4)Get all authors");
        System.out.println("(5)Delete author");
        System.out.println("(6)Create book");
        System.out.println("(7)Update book");
        System.out.println("(8)Get book`s authors");
        System.out.println("(9)Get all books");
        System.out.println("(10)Delete book");
        System.out.println("(11)Add books to author");
        System.out.println("(12)Add authors to book");
        System.out.println("(0)Exit");
        System.out.println();
    }
}
