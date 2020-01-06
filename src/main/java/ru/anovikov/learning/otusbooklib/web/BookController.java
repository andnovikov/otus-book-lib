package ru.anovikov.learning.otusbooklib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.*;

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

    @GetMapping(value = "/books")
    public String getBooks(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);

        // to create new book
        Book book = new Book();
        model.addAttribute("book", book);

        getBookAttributes(model);
        return "books";
    }

    @GetMapping(value = "/book/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Book book = bookService.findById(bookId);
        model.addAttribute("book", book);
        getBookAttributes(model);
        return "book";
    }

    @PostMapping(value = "/book")
    public String addBook(@ModelAttribute Book book) {
        bookService.insert(book.getAuthor(), book.getGenre(), book.getTitle());
        return "redirect:/books";
    }

    @PostMapping(value = "/book/{bookId}")
    public String updateBook(@PathVariable String bookId, @ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/books";
    }

    @PostMapping(value = "/book/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId, @ModelAttribute Book book) {
        bookService.delete(bookId);
        return "redirect:/books";
    }

    private void getBookAttributes(Model model) {
        List<Genre> genres = genreService.getAll();
        List<Author> authors = authorService.getAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
    }

}
