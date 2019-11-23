package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.BookService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

@ShellComponent
public class OtusBookLibCommands {

    private GenreService genreService;
    private BookService bookService;

    @Autowired
    public OtusBookLibCommands(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }



    @ShellMethod(value = "Add genre", key = {"addgnr"})
    public void addGenre() {
        // TODO: write method
    }

    @ShellMethod(value = "Find genre", key = {"fndgnr"})
    public void findGenre() {
        // TODO: write method
    }

    @ShellMethod(value = "Add book", key = {"addbook"})
    public void addBook() {
        // TODO: write method
    }

    @ShellMethod(value = "Find book", key = {"fndbook"})
    public void findBook() {
        // TODO: write method
    }

/*
        добавить книгу
        найти книгу
*/

}
