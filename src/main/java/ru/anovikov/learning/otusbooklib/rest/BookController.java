package ru.anovikov.learning.otusbooklib.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.BookService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import java.util.List;

@Slf4j
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
    public ResponseEntity<List<Book>> getBooks() {
        log.debug("REST request to get all books");
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/book/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable String bookId) {
        log.debug("REST request to get genre: " + bookId);
        return new ResponseEntity<>(bookService.findById(bookId), HttpStatus.OK);
    }

    @PostMapping(value = "/api/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        log.debug("REST request to add genre: " + book.getId());
        return new ResponseEntity<>(bookService.insert(book.getAuthor(), book.getGenre(), book.getTitle()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/book/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book book) {
        log.debug("REST request to update genre: " + book.getId());
        return new ResponseEntity<>(bookService.update(book), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/api/book/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable String bookId) {
        log.debug("REST request to delete genre: " + bookId);
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
