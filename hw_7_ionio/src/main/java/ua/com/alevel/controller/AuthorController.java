package ua.com.alevel.controller;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AuthorController {

    private static AuthorController instance = null;
    private final Scanner in = new Scanner(System.in);
    private final AuthorService authorService = new AuthorServiceImpl();

    private AuthorController() {
    }

    public static AuthorController getInstance() {
        if (instance == null) {
            instance = new AuthorController();
        }
        return instance;
    }

    public void createAuthor() {
        Author author = new Author();
        System.out.print("Enter name: ");
        author.setFirstname(in.nextLine());
        System.out.print("Enter surname: ");
        author.setLastname(in.nextLine());
        authorService.create(author);
        System.out.println("Author id: " + author.getId().toString());
        System.out.println("Author created!");
    }

    public void updateAuthor() {
        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }
        Author author = authorService.findAuthorById(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        System.out.println(author);
        System.out.print("Enter new name: ");
        author.setFirstname(in.nextLine());
        System.out.print("Enter new surname: ");
        author.setLastname(in.nextLine());
        authorService.update(author);
        System.out.println("Author updated!");
    }

    public void deleteAuthor() {
        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }
        Author author = authorService.findAuthorById(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        System.out.println(author);

        authorService.delete(author);
        System.out.println("Author deleted!");
    }

    public void findAuthorById() {
        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }
        Author author = authorService.findAuthorById(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        System.out.println(author);
    }

    public void findAllAuthors() {
        List<Author> authors = new ArrayList<>(authorService.findAllAuthors());
        authors.sort(Comparator.comparing(Author::getId));
        AsciiTable authorTable = new AsciiTable();
        authorTable.addRule();
        authorTable.addRow(null, null, null, "AUTHORS").setTextAlignment(TextAlignment.CENTER);
        authorTable.addRule();
        authorTable.addRow("ID", "NAME", "SURNAME", "BOOKS_ID");
        authorTable.addRule();
        for (Author author : authors) {
            authorTable.addRow(author.getId(), author.getFirstname(), author.getLastname(), author.getBooks().toString());
            authorTable.addRule();
        }
        System.out.println(authorTable.render());
    }

    public void findAuthorBooks() {
        Integer id;
        System.out.print("Enter author id: ");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Id must be number");
            return;
        }
        Author author = authorService.findAuthorById(id);
        if (author == null) {
            System.err.println("No author with current id!");
            return;
        }
        List<Book> books = new ArrayList<>(authorService.readBooks(author));
        books.sort(Comparator.comparing(Book::getId));
        AsciiTable bookTable = new AsciiTable();
        bookTable.addRule();
        bookTable.addRow(null, null, author.getFirstname() + " " + author.getLastname() + " books").setTextAlignment(TextAlignment.CENTER);
        bookTable.addRule();
        bookTable.addRow("ID", "NAME", "PRICES");
        bookTable.addRule();
        for (Book book : books) {
            bookTable.addRow(book.getId(), book.getName(), book.getPrice());
            bookTable.addRule();
        }
        System.out.println(bookTable.render());
    }
}
