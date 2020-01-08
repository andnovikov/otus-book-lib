package ru.anovikov.learning.otusbooklib.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.service.AuthorService;

import java.util.List;

@Slf4j
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/api/author")
    public ResponseEntity<List<Author>> getAuthors() {
        log.debug("REST request to get all authors");
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/author/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable String authorId) {
        log.debug("REST request to get author: " + authorId);
        return new ResponseEntity<>(authorService.findById(authorId), HttpStatus.OK);
    }

    @PostMapping(value = "/api/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        log.debug("REST request to insert author: ", author);
        return new ResponseEntity<>(authorService.insert(author.getFirstName(), author.getLastName()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/author/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable String authorId, @RequestBody Author author) {
        log.debug("REST request to update author: ", authorId);
        return new ResponseEntity<>(authorService.update(author), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/api/author/{authorId}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable String authorId) {
        log.debug("REST request to delete author: ", authorId);
        authorService.delete(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
