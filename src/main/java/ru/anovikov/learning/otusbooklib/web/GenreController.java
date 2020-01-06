package ru.anovikov.learning.otusbooklib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping(value = "/genres")
    public String getGenres(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @GetMapping(value = "/genre/{genreId}")
    public String getGenre(@PathVariable String genreId, Model model) {
        Genre genre = genreService.findById(genreId);
        model.addAttribute("genre", genre);
        return "genre";
    }

    @PostMapping(value="/genre")
    public String addGenre(@ModelAttribute  Genre genre) {
        genreService.insert(genre.getGenreName());
        return "redirect:/genres";
    }

    @PutMapping(value="/genre/{genreId}")
    public String updateGenre(@ModelAttribute  Genre genre) {
        genreService.update(genre);
        return "redirect:/genres";
    }

    @PostMapping(value = "/genre/delete/{genreId}")
    public String deleteGenre(@PathVariable String genreId, @ModelAttribute Genre genre) {
        genreService.delete(genreId);
        return "redirect:/genres";
    }

}
