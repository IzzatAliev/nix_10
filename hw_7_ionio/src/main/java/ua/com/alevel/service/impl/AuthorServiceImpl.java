package ua.com.alevel.service.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDAO = new AuthorDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public void create(Author author) {
        if (author.getFirstname().matches("[A-Za-z]+$")) {
            if (author.getLastname().matches("[A-Za-z]+$")) {
                authorDAO.create(author);
            }
        }
    }

    @Override
    public void update(Author author) {
        if (author.getFirstname().matches("[A-Za-z]+$")) {
            if (author.getLastname().matches("[A-Za-z]+$")) {
                if (isAuthorDeleted(author)) {
                    return;
                }
            }
        }
        authorDAO.update(author);
    }

    @Override
    public void delete(Author author) {
        if (isAuthorDeleted(author)) {
            return;
        }
        authorDAO.delete(author);
    }

    @Override
    public Set<Author> findAllAuthors() {
        return authorDAO.findAllAuthors().stream().filter(author -> !isAuthorDeleted(author)).collect(Collectors.toSet());

    }

    @Override
    public Author findAuthorById(Number id) {
        try {
            Author author = authorDAO.findAuthorById(id);
            if (isAuthorDeleted(author)) {
                return null;
            }
            return author;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        if (author == null) {
            System.err.println("No author with this id");
            return;
        }
        if (book == null) {
            System.err.println("No book with this id");
            return;
        }
        if (author.getBooks().contains(book.getId())) {
            return;
        }
        if (book.getName().matches("[A-Za-z]+$")) {
            authorDAO.addBookToAuthor(author, book);
        }
    }


    @Override
    public Set<Book> readBooks(Author author) {
        if (author == null) {
            System.err.println("No author with this id");
            return null;
        }
        Set<Book> books = new HashSet<>();
        author.getBooks().forEach(bookId -> books.add(bookDao.findBookById(bookId)));
        return books;
    }

    private boolean isAuthorDeleted(Author author) {
        return !author.getAvailable();
    }
}
