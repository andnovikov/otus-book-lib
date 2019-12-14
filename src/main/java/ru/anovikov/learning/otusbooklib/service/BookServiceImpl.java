package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.repository.BookRepository;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;

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
        // check for duplicate values
        try {
            Book foundBook = bookRepository.findByParam(author.getId(), genre.getId(), title);
            if (foundBook != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // do nothing
        }

        Book book = new Book(author, genre, title);
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public Book update(long id, Author author, Genre genre, String title) {
        //chek if exists
        bookRepository.findById(id);
        // check for duplicate values
        try {
            Book foundBook = bookRepository.findByParam(author.getId(), genre.getId(), title);
            if (foundBook != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // do nothing
        }

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
        return bookRepository.findById(id);
    };

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    };
}