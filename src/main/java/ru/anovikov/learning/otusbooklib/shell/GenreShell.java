package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.GenreService;

@ShellComponent
public class GenreShell {

    private GenreService genreService;

    @Autowired
    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "Add genre", key = {"add-genre", "ag"})
    public void addGenre() {
        Genre genre = genreService.insert();
        genreService.print(genre);
    }

    @ShellMethod(value = "Update genre", key = {"update-genre", "ug"})
    public void updateGenre() {
        Genre genre = genreService.update();
        genreService.print(genre);
    }

    @ShellMethod(value = "Delete genre", key = {"delete-genre", "dg"})
    public void deleteGenre() {
        genreService.delete();
    }

    @ShellMethod(value = "Find genre by id", key = {"find-genre-id", "fgi"})
    public void findGenrebyId() {
        Genre genre = genreService.findById();
        genreService.print(genre);
    }

    @ShellMethod(value = "Find genre by name", key = {"find-genre-name", "fgn"})
    public void findGenreByName() {
        Genre genre = genreService.findByName();
        genreService.print(genre);
    }

}
