package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.DateBase;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public class BookDaoImpl implements BookDao {

    @Override
    public void create(Book book) {
        DateBase.getInstance().createBook(book);
    }

    @Override
    public void update(Book book) {
        DateBase.getInstance().updateBook(book);
    }

    @Override
    public void delete(Book book) {
        DateBase.getInstance().deleteBook(book);
    }

    @Override
    public Set<Book> findAllBooks() {
        return DateBase.getInstance().findAllBooks();
    }

    @Override
    public Book findBookById(Number id) {
        return DateBase.getInstance().findBookById((Integer) id);
    }

    @Override
    public void addAuthorToBook(Book book, Author author) {
        DateBase.getInstance().createAuthorBook(author, book);
    }
}
