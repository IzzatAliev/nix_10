package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;


public class Book {

    private String name;
    private int price;
    private Set<Integer> authors;
    private Integer id;
    private Boolean available;

    public Book() {
        super();
        this.authors = new HashSet<>();
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Integer> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Integer> authors) {
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}