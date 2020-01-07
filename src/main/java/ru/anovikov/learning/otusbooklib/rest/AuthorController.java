package ru.anovikov.learning.otusbooklib.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/api/author")
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @GetMapping(value = "/api/author/{authorId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author getAuthor(@PathVariable String authorId) {
        return authorService.findById(authorId);
    }

    @PostMapping(value = "/api/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAuthor(@RequestBody Author author) {
        authorService.insert(author.getFirstName(), author.getLastName());
    }

    @PutMapping(value = "/api/author/{authorId}")
    public void updateAuthor(@PathVariable String authorId, @ModelAttribute Author author) {
        authorService.update(author);
    }

    @DeleteMapping(value = "/api/author/{authorId}")
    public void deleteAuthor(@PathVariable String authorId, @ModelAttribute Author author) {
        authorService.delete(authorId);
    }

}
