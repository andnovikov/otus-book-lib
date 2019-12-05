package ru.anovikov.learning.otusbooklib.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.service.BookService;
import ru.anovikov.learning.otusbooklib.service.CommentService;
import ru.anovikov.learning.otusbooklib.service.ConsoleService;
import ru.anovikov.learning.otusbooklib.service.MessageService;

import java.util.List;

@ShellComponent
public class CommentShell {

    private final CommentService commentService;
    private final BookService bookService;
    private final ConsoleService consoleService;
    private final MessageService messageService;

    @Autowired
    public CommentShell(CommentService commentService, BookService bookService,
                        ConsoleService consoleService, MessageService messageService) {
        this.commentService = commentService;
        this.bookService = bookService;
        this.consoleService = consoleService;
        this.messageService = messageService;
    }

    @ShellMethod(value = "Add book comment", key = {"add-book-cmnt", "abc"})
    public void addBookComment() {
        try {
            long id = consoleService.readLong("read.book.id");
            Book book = bookService.findById(id);

            String commentText = consoleService.readString("read.comment.text");

            Comment comment = commentService.insert(book, commentText);
            printComment(comment);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Del book comment", key = {"del-book-cmnt", "dbc"})
    public void delBookComment() {
        try {
            long id = consoleService.readLong("read.comment.id");
            commentService.delete(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.comment.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find comment by id", key = {"find-comment-id", "fci"})
    public void findCommentById() {
        try {
            long id = consoleService.readLong("read.comment.id");
            Comment comment = commentService.findById(id);
            printComment(comment);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.comment.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @ShellMethod(value = "Find comment by book", key = {"find-comment-book", "fcb"})
    public void findCommentByBook() {
        try {
            long id = consoleService.readLong("read.book.id");
            Book book = bookService.findById(id);

            List<Comment> comments = commentService.findByBook(book);
            for (Comment c: comments) {
                printComment(c);
            }
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.comment.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    private void printComment(Comment comment) {
        consoleService.writeString("table.comment", comment.toString());
    }
}
