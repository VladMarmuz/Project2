package com.marmuz.controllers;

import com.marmuz.models.Book;
import com.marmuz.models.Person;
import com.marmuz.services.BooksService;
import com.marmuz.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public String allBooks(Model model,
                           @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                           @RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "books_page", required = false) Integer bookPage) {

        if (page == null || bookPage == null) {
            model.addAttribute("books", booksService.findAll(sortByYear));
        } else {
            model.addAttribute("books", booksService.findWithPagination(page, bookPage, sortByYear));
        }
        return "book/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/create";
    }

    @PostMapping("")
    public String createBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/create";
        else
            booksService.save(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findBookById(id));

        Person owner = booksService.getBookOwner(id);
        if (owner != null)
            model.addAttribute("person", owner);
        else
            model.addAttribute("people", peopleService.findAll());
        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String findBookForEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findBookById(id));
        return "book/update";
    }

    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/update";
        else
            booksService.updateBook(id, book);
        return "redirect:/books";
    }

    @PostMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        booksService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assignBook(id, person);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "book/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("books", booksService.searchByTitle(query));
        return "book/search";
    }


}
