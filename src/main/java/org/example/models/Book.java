package org.example.models;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class Book {

    private int bookId;
    @NotEmpty(message = "У книги должно быть название")
    private String name;
    @NotEmpty(message = "У книги должен быть автор")
    private String author;
    
    private int yearProduction;

    public Book() {

    }

    public Book(String name, String author, int yearProduction) {
        this.name = name;
        this.author = author;
        this.yearProduction = yearProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
