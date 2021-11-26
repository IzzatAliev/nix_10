package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public interface AuthorService {
    void create(Author author);
    void update (Author author);
    void delete(Author author);
    Set<Author> findAllAuthors();
    Author findAuthorById(Number id);
    void addBookToAuthor(Author author, Book book);
    Set<Book> readBooks(Author author);
}
