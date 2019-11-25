package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.service.AuthorService;

@ShellComponent
public class AuthorShell {

    private AuthorService authorService;

    @Autowired
    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "Add author", key = {"add-author", "aa"})
    public void addAuthor() {
        Author author = authorService.insert();
        authorService.print(author);
    }

    @ShellMethod(value = "Update author", key = {"update-author", "ua"})
    public void updateAuthor() {
        Author author = authorService.update();
        authorService.print(author);
    }

    @ShellMethod(value = "Delete author", key = {"delete-author", "da"})
    public void deleteAuthor() {
        authorService.delete();
    }

    @ShellMethod(value = "Find author by id", key = {"find-author-id", "fai"})
    public void findAuthorById() {
        Author author = authorService.findById();
        authorService.print(author);
    }

    @ShellMethod(value = "Find author by name", key = {"find-author-name", "fan"})
    public void findAuthorByName() {
        Author author = authorService.findByName();
        authorService.print(author);
    }

}
