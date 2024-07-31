package org.example.models;

public class Book {
    private String name;
    private String author;
    private int yearProduction;

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
}
