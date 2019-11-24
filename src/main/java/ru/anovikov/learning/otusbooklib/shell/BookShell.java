package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.service.BookService;

@ShellComponent
public class BookShell {

    private BookService bookService;

    @Autowired
    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Add book", key = {"add-book", "ab"})
    public void addBook() {
        Book book = bookService.insert();
        bookService.print(book);
    }

    @ShellMethod(value = "Update book", key = {"update-book", "ub"})
    public void updateBook() {
        Book book = bookService.update();
        bookService.print(book);
    }

    @ShellMethod(value = "Delete book", key = {"delete-book", "db"})
    public void deleteBook() {
        bookService.delete();
    }

    @ShellMethod(value = "Find book by id", key = {"find-book-id", "fbi"})
    public void findBookById() {
        Book book = bookService.findById();
        bookService.print(book);
    }

    @ShellMethod(value = "Find book by name", key = {"find-book-title", "fbt"})
    public void findBookByTitle() {
        Book book = bookService.findByTitle();
        bookService.print(book);
    }

}
