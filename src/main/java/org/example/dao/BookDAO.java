package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksOfPerson(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonByBook(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Person.person_id = Book.person_id WHERE book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, yearProduction) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYearProduction());
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, yearProduction=? WHERE book_id=?", book.getName(), book.getAuthor(), book.getYearProduction(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void freeBook(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", bookId);
    }

    public void appointBook(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", personId, bookId);
    }
}
