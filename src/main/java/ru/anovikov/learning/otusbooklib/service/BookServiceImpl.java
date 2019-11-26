package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.BookDao;
import ru.anovikov.learning.otusbooklib.dao.DuplicateValueException;
import ru.anovikov.learning.otusbooklib.dao.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

@Service
public class BookServiceImpl implements BookService{

    BookDao bookDao;
    AuthorService authorService;
    GenreService genreService;
    ConsoleService consoleService;

    @Autowired
    public BookServiceImpl (BookDao bookDao, AuthorService authorService, GenreService genreService, ConsoleService consoleService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
        this.consoleService = consoleService;
    }

    @Override
    public Book insert(Author author, Genre genre, String title) {
        Book book = new Book(0, author, genre, title);
        book = bookDao.insert(book);
        return book;
    }

    @Override
    public Book update(long id, Author author, Genre genre, String title) {
        Book book = new Book(id, author, genre, title);
        book = bookDao.update(book);
        return book;
    }

    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }

    @Override
    public Book findById(long id) {
        Book book = bookDao.getById(id);
        return book;
    };

    @Override
    public Book findByTitle(String title) {
        Book book = bookDao.getByTitle(title);
        return book;
    };

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    };

}