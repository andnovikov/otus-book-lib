package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.dao.DuplicateValueException;
import ru.anovikov.learning.otusbooklib.dao.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.ConsoleService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

@ShellComponent
public class GenreShell {

    private GenreService genreService;
    private ConsoleService consoleService;

    @Autowired
    public GenreShell(GenreService genreService, ConsoleService consoleService) {
        this.genreService = genreService;
        this.consoleService = consoleService;
    }

    @ShellMethod(value = "Add genre", key = {"add-genre", "ag"})
    public void addGenre() {
        try {
            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = genreService.insert(genreName);
            printGenre(genre);
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.genre.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Update genre", key = {"update-genre", "ug"})
    public void updateGenre() {
        try {
            long id = consoleService.readLong("read.genre.id");
            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = genreService.update(id, genreName);
            printGenre(genre);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.genre.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Delete genre", key = {"delete-genre", "dg"})
    public void deleteGenre() {
        try {
            long id = consoleService.readLong("read.genre.id");
            genreService.delete(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }

    }

    @ShellMethod(value = "Find genre by id", key = {"find-genre-id", "fgi"})
    public void findGenrebyId() {
        try {
            long id = consoleService.readLong("read.genre.id");
            Genre genre = genreService.findById(id);
            printGenre(genre);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find genre by name", key = {"find-genre-name", "fgn"})
    public void findGenreByName() {
        try {
            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = genreService.findByName(genreName);
            printGenre(genre);

        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    private void printGenre(Genre genre) {
        if (genre != null) {
            consoleService.writeString("table.genre", genre.toString());
        }
    };

}
