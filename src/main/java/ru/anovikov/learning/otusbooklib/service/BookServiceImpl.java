package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.repository.BookRepository;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

@Service
public class BookServiceImpl implements BookService{

    BookRepository bookRepository;
    AuthorService authorService;
    GenreService genreService;
    ConsoleService consoleService;

    @Autowired
    public BookServiceImpl (BookRepository bookRepository, AuthorService authorService, GenreService genreService, ConsoleService consoleService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.consoleService = consoleService;
    }

    @Override
    public Book insert(Author author, Genre genre, String title) {
        Book book = new Book(0, author, genre, title);
        book = bookRepository.insert(book);
        return book;
    }

    @Override
    public Book update(long id, Author author, Genre genre, String title) {
        Book book = new Book(id, author, genre, title);
        book = bookRepository.update(book);
        return book;
    }

    @Override
    public void delete(long id) {
        bookRepository.delete(id);
    }

    @Override
    public Book findById(long id) {
        Book book = bookRepository.getById(id);
        return book;
    };

    @Override
    public Book findByTitle(String title) {
        Book book = bookRepository.getByTitle(title);
        return book;
    };

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    };

}