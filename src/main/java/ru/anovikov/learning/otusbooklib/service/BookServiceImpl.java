package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.repository.BookRepository;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.Optional;

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
        Book book = new Book(author, genre, title);
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public Book update(long id, Author author, Genre genre, String title) {
        Book book = new Book(id, author, genre, title);
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public void delete(long id) {
        bookRepository.delete(id);
    }

    @Override
    public Book findById(long id) {
        Optional<Book> foundEntity = bookRepository.findById(id);

        if (!foundEntity.isPresent()) {
            //TODO: throw new NoDataFoundException();
        }

        return foundEntity.get();
    };

    @Override
    public Book findByTitle(String title) {
        Optional<Book> foundEntity = bookRepository.findByTitle(title);

        if (!foundEntity.isPresent()) {
            //TODO: throw new NoDataFoundException();
        }

        return foundEntity.get();
    };
}