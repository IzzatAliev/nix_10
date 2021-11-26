package ua.com.alevel.service.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.dao.impl.AuthorDaoImpl;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private final AuthorDao authorDAO = new AuthorDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public void create(Book book) {
        if (book.getName().matches("[A-Za-z]+$")) {
            bookDao.create(book);
        }
    }

    @Override
    public void update(Book book) {
        if (book.getName().matches("[A-Za-z]+$")) {
            if (isBookDeleted(book)) {
                return;
            }
        }
        bookDao.update(book);
    }

    @Override
    public void delete(Book book) {
        if (isBookDeleted(book)) {
            return;
        }
        bookDao.delete(book);
    }

    @Override
    public Set<Book> findAllBooks() {
        return bookDao.findAllBooks().stream().filter(book -> !isBookDeleted(book)).collect(Collectors.toSet());
    }

    @Override
    public Book findBookById(Number id) {
        try {
            Book book = bookDao.findBookById(id);
            if (isBookDeleted(book)) {
                return null;
            }
            return book;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public void addAuthorToBook(Book book, Author author) {
        if (author == null) {
            System.err.println("No author with this id");
            return;
        }
        if (book == null) {
            System.err.println("No book with current id");
            return;
        }
        if (author.getBooks().contains(book.getId())) {
            return;
        }
        if (author.getFirstname().matches("[A-Za-z]+$")) {
            if (author.getLastname().matches("[A-Za-z]+$")) {
                authorDAO.addBookToAuthor(author, book);
            }
        }
    }


    @Override
    public Set<Author> readAuthors(Book book) {
        Set<Author> authors = new HashSet<>();
        book.getAuthors().forEach(authorId -> authors.add(authorDAO.findAuthorById(authorId)));
        return authors;
    }

    private boolean isBookDeleted(Book book) {
        return !book.getAvailable();
    }
}
