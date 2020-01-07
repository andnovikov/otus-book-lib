package ru.anovikov.learning.otusbooklib.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.BookService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService,
                          GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping(value = "/api/book")
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @GetMapping(value = "/api/book/{bookId}")
    public Book getBook(@PathVariable String bookId) {
        return bookService.findById(bookId);
    }

    @PostMapping(value = "/api/book")
    public void addBook(@RequestBody Book book) {
        bookService.insert(book.getAuthor(), book.getGenre(), book.getTitle());
    }

    @PutMapping(value = "/api/book/{bookId}")
    public void updateBook(@PathVariable String bookId, @RequestBody Book book) {
        bookService.update(book);
    }

    @PostMapping(value = "/api/book/{bookId}")
    public void deleteBook(@PathVariable String bookId) {
        bookService.delete(bookId);
    }

    /*
    private void getBookAttributes(Model model) {
        List<Genre> genres = genreService.getAll();
        List<Author> authors = authorService.getAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
    }
    */

}
