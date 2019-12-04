package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.service.DuplicateValueException;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.*;

@ShellComponent
public class BookShell {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ConsoleService consoleService;
    private final MessageService messageService;

    @Autowired
    public BookShell(BookService bookService, AuthorService authorService,
                     GenreService genreService, ConsoleService consoleService,
                     MessageService messageService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.consoleService = consoleService;
        this.messageService = messageService;
    }

    @ShellMethod(value = "Add book", key = {"add-book", "ab"})
    public void addBook() {
        try {

            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = null;
            try {
                author = authorService.findByName(firstName, lastName);
            }
            catch (NoDataFoundException e) {
                throw new DataInputException(messageService.getMessage("error.author.notexists"));
            }

            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = null;
            try {
                genre = genreService.findByName(genreName);
            }
            catch (NoDataFoundException e) {
                throw new DataInputException(messageService.getMessage("error.genre.notexists"));
            }

            String title = consoleService.readString("read.book.title");

            Book book = bookService.insert(author, genre, title);
            printBook(book);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.book.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.book.input", ": " + e.getMessage());
        }
    }

    @ShellMethod(value = "Update book", key = {"update-book", "ub"})
    public void updateBook() {
        try {
            long id = consoleService.readLong("read.book.id");

            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = null;
            try {
                author = authorService.findByName(firstName, lastName);
            }
            catch (NoDataFoundException e) {
                throw new DataInputException(messageService.getMessage("error.author.notexists"));
            }

            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = null;
            try {
                genre = genreService.findByName(genreName);
            }
            catch (NoDataFoundException e) {
                throw new DataInputException(messageService.getMessage("error.genre.notexists"));
            }

            String title = consoleService.readString("read.book.title");

            Book book = bookService.update(id, author, genre, title);
            printBook(book);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.book.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.book.input", ": " + e.getMessage());
        }
    }

    @ShellMethod(value = "Delete book", key = {"delete-book", "db"})
    public void deleteBook() {
        try {
            long id = consoleService.readLong("read.book.id");
            bookService.delete(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find book by id", key = {"find-book-id", "fbi"})
    public void findBookById() {
        try {
            long id = consoleService.readLong("read.book.id");
            Book book = bookService.findById(id);
            printBook(book);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find book by name", key = {"find-book-title", "fbt"})
    public void findBookByTitle() {
        try {
            String title = consoleService.readString("read.book.title");
            Book book = bookService.findByTitle(title);
            printBook(book);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    private void printBook(Book book) {
        consoleService.writeString("table.book", book.toString());
    }

}
