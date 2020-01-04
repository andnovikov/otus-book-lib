package ru.anovikov.learning.otusbooklib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.service.AuthorService;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors")
    public String getAuthors(Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping(value = "/author/{authorId}")
    public String getAuthor(@PathVariable String authorId, Model model) {
        Author author = authorService.findById(authorId);
        model.addAttribute("author", author);
        return "author";
    }

    @PostMapping(value = "/author")
    public String addAuthor(@ModelAttribute Author author) {
        authorService.insert(author.getFirstName(), author.getLastName());
        return "redirect:/authors";
    }

    @PostMapping(value = "/author/{authorId}")
    public String updateAuthor(@PathVariable String authorId, @ModelAttribute Author author) {
        authorService.update(author);
        return "redirect:/authors";
    }

    @PostMapping(value = "/author/delete/{authorId}")
    public String deleteAuthor(@PathVariable String authorId, @ModelAttribute Author author) {
        authorService.delete(authorId);
        return "redirect:/authors";
    }

}
