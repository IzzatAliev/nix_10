package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.db.DateBase;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.Set;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void create(Author author) {
        DateBase.getInstance().createAuthor(author);
    }

    @Override
    public void update(Author author) {
        DateBase.getInstance().updateAuthor(author);
    }

    @Override
    public void delete(Author author) {
        DateBase.getInstance().deleteAuthor(author);
    }

    @Override
    public Set<Author> findAllAuthors() {
        return DateBase.getInstance().findAllAuthors();
    }

    @Override
    public Author findAuthorById(Number id) {
        return DateBase.getInstance().findAuthorById((Integer) id);
    }

    @Override
    public void addBookToAuthor(Author author, Book book) {
        DateBase.getInstance().createAuthorBook(author, book);
    }
}
