package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
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
        genreService.insert();
    }

    @ShellMethod(value = "Update genre", key = {"update-genre", "ug"})
    public void updateGenre() {
        genreService.update();
    }

    @ShellMethod(value = "Delete genre", key = {"delete-genre", "dg"})
    public void deleteGenre() {
        genreService.delete();
    }

    @ShellMethod(value = "Find genre", key = {"find-genre", "fg"})
    public void printGenre() {
        // genreService.findByName();
    }

}
