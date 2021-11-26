package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.util.CsvWriterReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DateBase {

    private static DateBase dateBase = null;
    private CsvWriterReader authorWriter;
    private CsvWriterReader bookWriter;
    private CsvWriterReader authorReader;
    private CsvWriterReader bookReader;
    private CsvWriterReader authorBookWriter;
    private CsvWriterReader authorBookReader;
    private final List<String[]> authorCsv = new ArrayList<>();
    private final List<String[]> bookCsv = new ArrayList<>();
    private final List<String[]> authorBookCsv = new ArrayList<>();
    private final String[] authorTableHeader = {"id", "name", "last_name", "available"};
    private final String[] bookTableHeader = {"id", "name", "price", "available"};
    private final String[] authorBookTableHeader = {"id", "author_id", "book_id"};
    private final Integer AUTHOR_TABLE_COLUMNS_AMOUNT = authorTableHeader.length;
    private final Integer BOOK_TABLE_COLUMNS_AMOUNT = bookTableHeader.length;
    private final Integer BOOK_REGISTRATION_TABLE_COLUMNS_AMOUNT = authorBookTableHeader.length;
    private final String authorFilePath = "src/main/resources/author.csv";
    private final String bookFilePath = "src/main/resources/book.csv";
    private final String authorBookFilePath = "src/main/resources/ authorBook.csv";

    private void setAuthorReader() {
        authorReader = new CsvWriterReader(authorFilePath);
    }

    private void getAuthorWriter() {
        authorWriter = new CsvWriterReader(authorFilePath);
    }

    private void setBookReader() {
        bookReader = new CsvWriterReader(bookFilePath);
    }

    private void getBookWriter() {
        bookWriter = new CsvWriterReader(bookFilePath);
    }

    private void setAuthorBookReader() {
        authorBookReader = new CsvWriterReader(authorBookFilePath);
    }

    private void getAuthorBookWriter() {
        authorBookWriter = new CsvWriterReader(authorBookFilePath);
    }

    private DateBase() {
        getAuthorWriter();
        setAuthorReader();
        getBookWriter();
        setBookReader();
        getAuthorBookWriter();
        setAuthorBookReader();
        boolean authorIsEmpty = authorReader.readFromCSV().isEmpty();
        boolean bookIsEmpty = bookReader.readFromCSV().isEmpty();
        boolean bookRegistrationIsEmpty = authorBookReader.readFromCSV().isEmpty();
        if (authorIsEmpty || bookIsEmpty || bookRegistrationIsEmpty) {
            initAuthorTable();
            initBookTable();
            initAuthorBookTable();
        } else {
            setAuthorBookReader();
            authorCsv.addAll(authorReader.readFromCSV());
            setBookReader();
            bookCsv.addAll(bookReader.readFromCSV());
            setAuthorBookReader();
            authorBookCsv.addAll(authorBookReader.readFromCSV());
        }
    }

    private void initAuthorTable() {
        getAuthorWriter();
        setAuthorReader();
        authorCsv.add(authorTableHeader);
        authorWriter.writeToCSV(authorCsv);
    }

    private void initBookTable() {
        getBookWriter();
        setBookReader();
        bookCsv.add(bookTableHeader);
        bookWriter.writeToCSV(bookCsv);
    }

    private void initAuthorBookTable() {
        getAuthorBookWriter();
        setAuthorBookReader();
        authorBookCsv.add(authorBookTableHeader);
        authorBookWriter.writeToCSV(authorBookCsv);
    }

    public static DateBase getInstance() {
        if (dateBase == null) {
            dateBase = new DateBase();
        }
        return dateBase;
    }

    public void createAuthor(Author author) {
        author.setId(authorCsv.size());
        String[] authorData = new String[AUTHOR_TABLE_COLUMNS_AMOUNT];
        authorData[0] = String.valueOf(author.getId());
        authorData[1] = author.getFirstname();
        authorData[2] = author.getLastname();
        authorData[3] = String.valueOf(author.getAvailable());
        authorCsv.add(authorData);
        getAuthorWriter();
        authorWriter.writeToCSV(this.authorCsv);
    }

    public void createBook(Book book) {
        book.setId(bookCsv.size());
        String[] bookData = new String[BOOK_TABLE_COLUMNS_AMOUNT];
        bookData[0] = String.valueOf(book.getId());
        bookData[1] = book.getName();
        bookData[2] = String.valueOf(book.getPrice());
        bookData[3] = String.valueOf(book.getAvailable());
        bookCsv.add(bookData);
        getBookWriter();
        bookWriter.writeToCSV(this.bookCsv);
    }

    public void createAuthorBook(Author author, Book book) {
        String[] authorBookData = new String[BOOK_REGISTRATION_TABLE_COLUMNS_AMOUNT];
        authorBookData[0] = String.valueOf(authorBookCsv.size());
        authorBookData[1] = String.valueOf(author.getId());
        authorBookData[2] = String.valueOf(book.getId());
        authorBookCsv.add(authorBookData);
        getAuthorBookWriter();
        authorBookWriter.writeToCSV(this.authorBookCsv);
    }

    public void updateAuthor(Author author) {
        int updatedAuthorIndex = 0;
        for (int i = 1; i < this.authorCsv.size(); i++) {
            if (Integer.parseInt(authorCsv.get(i)[0]) == author.getId()) {
                updatedAuthorIndex = i;
            }
        }
        String[] updatedRow = new String[AUTHOR_TABLE_COLUMNS_AMOUNT];
        updatedRow[0] = String.valueOf(author.getId());
        updatedRow[1] = author.getFirstname();
        updatedRow[2] = author.getLastname();
        updatedRow[3] = String.valueOf(author.getAvailable());
        authorCsv.set(updatedAuthorIndex, updatedRow);
        getAuthorWriter();
        authorWriter.writeToCSV(authorCsv);
    }

    public void updateBook(Book book) {
        int updatedBookIndex = 0;
        for (int i = 1; i < this.bookCsv.size(); i++) {
            if (Integer.parseInt(bookCsv.get(i)[0]) == book.getId()) {
                updatedBookIndex = i;
            }
        }
        String[] updatedRow = new String[BOOK_TABLE_COLUMNS_AMOUNT];
        updatedRow[0] = String.valueOf(book.getId());
        updatedRow[1] = book.getName();
        updatedRow[2] = String.valueOf(book.getPrice());
        updatedRow[3] = String.valueOf(book.getAvailable());

        bookCsv.set(updatedBookIndex, updatedRow);
        getBookWriter();
        bookWriter.writeToCSV(bookCsv);
    }

    public void deleteAuthor(Author author) {
        int deletedAuthorIndex = 0;
        for (int i = 1; i < this.authorCsv.size(); i++) {
            if (Integer.parseInt(authorCsv.get(i)[0]) == author.getId()) {
                deletedAuthorIndex = i;
                break;
            }
        }
        authorCsv.get(deletedAuthorIndex)[3] = "false";
        getAuthorWriter();
        authorWriter.writeToCSV(authorCsv);
    }

    public void deleteBook(Book book) {
        int deletedBookRowIndex = 0;
        for (int i = 1; i < this.bookCsv.size(); i++) {
            if (Integer.parseInt(bookCsv.get(i)[0]) == book.getId()) {
                deletedBookRowIndex = i;
            }
        }
        bookCsv.get(deletedBookRowIndex)[3] = "false";
        getBookWriter();
        bookWriter.writeToCSV(bookCsv);
    }

    public Author findAuthorById(Integer id) {
        String[] authorRow = authorCsv.stream()
                .skip(1)
                .filter(author -> Integer.parseInt(author[0]) == id)
                .findFirst().get();
        Author author = new Author();
        author.setId(Integer.parseInt(authorRow[0]));
        author.setFirstname(authorRow[1]);
        author.setLastname(authorRow[2]);
        author.setBooks(findAuthorBooks(id));
        return author;
    }

    public Book findBookById(Integer id) {
        String[] bookRow = bookCsv.stream()
                .skip(1)
                .filter(book -> Integer.parseInt(book[0]) == id)
                .findFirst().get();
        Book book = new Book();
        book.setId(Integer.parseInt(bookRow[0]));
        book.setName(bookRow[1]);
        book.setPrice(Integer.parseInt(bookRow[2]));
        book.setAuthors(findBookAuthors(id));
        return book;
    }

    public Set<Integer> findAuthorBooks(Integer authorId) {
        Set<Integer> booksIds = new HashSet<>();
        List<String[]> authorBooksRows = authorBookCsv.stream()
                .skip(1)
                .filter(authorBooksData -> Integer.parseInt(authorBooksData[1]) == authorId)
                .collect(Collectors.toList());
        for (String[] authorBookSdata : authorBooksRows) {
            booksIds.add(Integer.parseInt(authorBookSdata[2]));
        }
        return booksIds;
    }

    public Set<Integer> findBookAuthors(Integer bookId) {
        Set<Integer> authorsIds = new HashSet<>();
        List<String[]> bookRegistrationRows = authorBookCsv.stream()
                .skip(1)
                .filter(bookAuthorsData -> Integer.parseInt(bookAuthorsData[2]) == bookId)
                .collect(Collectors.toList());
        for (String[] bookAuthordata : bookRegistrationRows) {
            authorsIds.add(Integer.parseInt(bookAuthordata[1]));
        }
        return authorsIds;
    }

    public Set<Author> findAllAuthors() {
        Set<Author> authors = new HashSet<>();
        for (int i = 1; i < authorCsv.size(); i++) {
            int id = Integer.parseInt(authorCsv.get(i)[0]);
            Author author = findAuthorById(id);
            authors.add(author);
        }
        return authors;
    }

    public Set<Book> findAllBooks() {
        Set<Book> books = new HashSet<>();
        for (int i = 1; i < bookCsv.size(); i++) {
            int id = Integer.parseInt(bookCsv.get(i)[0]);
            Book book = findBookById(id);
            books.add(book);
        }
        return books;
    }
}


