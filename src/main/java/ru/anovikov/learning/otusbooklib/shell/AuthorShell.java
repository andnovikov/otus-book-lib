package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.service.DuplicateValueException;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.ConsoleService;

@ShellComponent
public class AuthorShell {

    private AuthorService authorService;
    private ConsoleService consoleService;

    @Autowired
    public AuthorShell(AuthorService authorService, ConsoleService consoleService) {
        this.authorService = authorService;
        this.consoleService = consoleService;
    }

    @ShellMethod(value = "Add author", key = {"add-author", "aa"})
    public void addAuthor() {
        try {
            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = authorService.insert(firstName, lastName);
            printAuthor(author);
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.author.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Update author", key = {"update-author", "ua"})
    public void updateAuthor() {
        try {
            long id = consoleService.readLong("read.author.id");
            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = authorService.update(id, firstName, lastName);
            printAuthor(author);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.author.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Delete author", key = {"delete-author", "da"})
    public void deleteAuthor() {
        try {
            long id = consoleService.readLong("read.author.id");
            authorService.delete(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find author by id", key = {"find-author-id", "fai"})
    public void findAuthorById() {
        try {
            long id = consoleService.readLong("read.author.id");
            Author author = authorService.findById(id);
            printAuthor(author);

        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find author by name", key = {"find-author-name", "fan"})
    public void findAuthorByName() {
        try {
            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = authorService.findByName(firstName, lastName);
            printAuthor(author);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    private void printAuthor(Author author) {
        consoleService.writeString("table.author", author.toString());
    }

}
