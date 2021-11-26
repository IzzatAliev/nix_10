package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.Scanner;

public class ConnectionController {

    private static final Scanner in = new Scanner(System.in);
    private static final AuthorService authorService = new AuthorServiceImpl();
    private static final BookService bookService = new BookServiceImpl();

    public static void addBook() {
        Integer authorId;
        String bookId = null;
        try {
            System.out.print("Enter author id: ");
            authorId = Integer.parseInt(in.nextLine());
            BookController.getInstance().findAllBooks();
            System.out.print("Enter books ids: ");
            bookId = in.nextLine();
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }
        Author author = authorService.findAuthorById(authorId);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        bookId = bookId.replaceAll("\\s", "");
        String[] idbs = bookId.split(",");
        Book book = null;
        for (String s : idbs) {
            book = bookService.findBookById(Integer.parseInt(s));
            if (book == null) {
                System.err.println("No book with current id!");
                return;
            }
            authorService.addBookToAuthor(author, book);
        }
        System.out.println("Books was added to author");
    }

    public static void addAuthor() {
        Integer bookId;
        String authorIds = null;
        try {
            System.out.print("Enter book id: ");
            bookId = Integer.parseInt(in.nextLine());
            AuthorController.getInstance().findAllAuthors();
            System.out.print("Enter authors ids: ");
            authorIds = in.nextLine();
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }
        Book book = bookService.findBookById(bookId);
        if (book == null) {
            System.err.println("No book with current id!");
            return;
        }
        authorIds = authorIds.replaceAll("\\s", "");
        String[] ids = authorIds.split(",");
        Author authors = null;
        for (String s : ids) {
            authors = authorService.findAuthorById(Integer.parseInt(s));
            if (authors == null) {
                System.err.println("No authors with current id!");
                return;
            }
            bookService.addAuthorToBook(book, authors);
        }
        System.out.println("Authors was added to book");
    }
}