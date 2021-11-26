package ua.com.alevel.entity;

public class Connection {

    private Integer id;
    private Integer authorId;
    private Integer bookId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection authorBook = (Connection) o;
        if (getAuthorId() != null ? !getAuthorId().equals(authorBook.getAuthorId()) : authorBook.getAuthorId() != null)
            return false;
        return getBookId() != null ? getBookId().equals(authorBook.getBookId()) : authorBook.getBookId() == null;
    }

    @Override
    public int hashCode() {
        int result = getAuthorId() != null ? getAuthorId().hashCode() : 0;
        result = 31 * result + (getBookId() != null ? getBookId().hashCode() : 0);
        return result;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", bookId=" + bookId +
                '}';
    }
}
