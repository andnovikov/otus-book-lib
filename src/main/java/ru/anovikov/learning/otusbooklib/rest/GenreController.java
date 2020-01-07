package ru.anovikov.learning.otusbooklib.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import java.util.List;

@Controller
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController (GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/api/genre")
    public List<Genre> getGenres() {
        return genreService.getAll();
    }

    @GetMapping(value = "/api/genre/{genreId}")
    public Genre getGenre(@PathVariable String genreId) {
        return genreService.findById(genreId);
    }

    @PostMapping(value="/api/genre")
    public String addGenre(@RequestBody  Genre genre) {
        genreService.insert(genre.getGenreName());
        return "redirect:/genres";
    }

    @PutMapping(value="/api/genre/{genreId}")
    public void updateGenre(@RequestBody  Genre genre) {
        genreService.update(genre);
    }

    @DeleteMapping(value = "/genre/delete/{genreId}")
    public void deleteGenre(@PathVariable String genreId) {
        genreService.delete(genreId);
    }

}
