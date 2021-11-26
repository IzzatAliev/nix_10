package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public interface BookDao {

    void create(Book book);

    void update(Book book);

    void delete(Book book);

    Set<Book> findAllBooks();

    Book findBookById(Number id);

    void addAuthorToBook(Book book, Author author);
}
