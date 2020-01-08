package ru.anovikov.learning.otusbooklib.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import java.util.List;

@Slf4j
@RestController
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController (GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/api/genre")
    public ResponseEntity<List<Genre>> getGenres() {
        log.debug("REST request to get all genres");
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/genre/{genreId}")
    public ResponseEntity<Genre> getGenre(@PathVariable String genreId) {
        log.debug("REST request to get genre: " + genreId);
        return new ResponseEntity<>(genreService.findById(genreId), HttpStatus.OK);
    }

    @PostMapping(value="/api/genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Genre> addGenre(@RequestBody  Genre genre) {
        log.debug("REST request to add genre: ", genre);
        return new ResponseEntity<>(genreService.insert(genre), HttpStatus.CREATED);
    }

    @PutMapping(value="/api/genre/{genreId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Genre> updateGenre(@RequestBody  Genre genre) {
        log.debug("REST request to update genre: ", genre);
        return new ResponseEntity<>(genreService.update(genre), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/api/genre/{genreId}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable String genreId) {
        log.debug("REST request to delete genre: " + genreId);
        genreService.delete(genreId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
