package com.marmuz.controllers;

import com.marmuz.models.Person;
import com.marmuz.services.PeopleService;
import com.marmuz.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "create";
    }

    @PostMapping("")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "create";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findPersonBy(id));
        model.addAttribute("books", peopleService.findBookByPersonId(id));

        return "show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findPersonBy(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }


}
