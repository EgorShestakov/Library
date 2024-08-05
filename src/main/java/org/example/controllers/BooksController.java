package org.example.controllers;

import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/books")
public class BooksController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String books(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String book(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDAO.getBook(id));
        model.addAttribute("person", bookDAO.getPersonByBook(id));
        return "books/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/edit";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("newBook", new Book());
        return "books/form";
    }

    @PostMapping()
    public String addBook(@ModelAttribute Book book) {
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String freeBook(@PathVariable int id) {
        return "redirect"; // ?????
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
