package org.example.controllers;

import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;
    private BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String people(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String person(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", bookDAO.getBooksOfPerson(id));
        return "people/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

    @PostMapping()
    public String post(@ModelAttribute Person person) {
        personDAO.addPerson(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String change(@PathVariable("id") int id, @ModelAttribute Person person) {
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}
