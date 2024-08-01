package org.example.controllers;

import org.example.models.Person;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Hotfix
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
        return "people";
    }

    @GetMapping("/{id}")
    public String person(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "person";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", bookDAO.getBooksOfPerson(id));
        return "edit";
    }

    @PostMapping
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
