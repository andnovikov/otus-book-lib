package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.repository.BookRepository;

import java.util.List;
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
        // check for duplicate values
        Optional<Book> foundBook = bookRepository.findByAuthorAndGenreAndTitle(author, genre, title);
        if (foundBook.isPresent()) {
            throw new DuplicateValueException();
        }
        Book book = new Book(author, genre, title);
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public Book update(String id, Author author, Genre genre, String title) {
        //chek if exists
        if (!bookRepository.existsById(id)) {
            throw new NoDataFoundException();
        }
        // check for duplicate values
        Optional<Book> foundBook = bookRepository.findByAuthorAndGenreAndTitle(author, genre, title);
        if (foundBook.isPresent() && (!foundBook.get().getId().equalsIgnoreCase(id))) {
            throw new DuplicateValueException();
        }

        Book book = new Book(id, author, genre, title);
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        //chek if exists
        if (!bookRepository.existsById(book.getId())) {
            throw new NoDataFoundException();
        }
        // check for duplicate values
        Optional<Book> foundBook = bookRepository.findByAuthorAndGenreAndTitle(book.getAuthor(), book.getGenre(), book.getTitle());
        if (foundBook.isPresent() && (!foundBook.get().getId().equalsIgnoreCase(book.getId()))) {
            throw new DuplicateValueException();
        }
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public void delete(String id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (!foundBook.isPresent()) {
            throw new NoDataFoundException();
        }
        bookRepository.delete(foundBook.get());
    }

    @Override
    public Book findById(String id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (!foundBook.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundBook.get();
    };

    @Override
    public Book findByTitle(String title) {
        Optional<Book> foundBook = bookRepository.findByTitle(title);;
        if (!foundBook.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundBook.get();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    ;
}