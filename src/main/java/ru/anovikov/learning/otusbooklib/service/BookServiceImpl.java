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
    public Book insert() {
        try {
            Author author = authorService.findByName();

            if (author == null) {
                throw  new DataInputException();
            }

            Genre genre = genreService.findByName();
            if (genre == null) {
                throw  new DataInputException();
            }

            String title = consoleService.readString("read.book.title");
            Book book = new Book(0, author, genre, title);
            book = bookDao.insert(book);
            return book;
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.book.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.book.input", "");
        }
        return null;
    }

    @Override
    public Book update() {
        try {
            long id = consoleService.readLong("read.book.id");
            Author author = authorService.findByName();
            if (author == null) {
                throw  new DataInputException();
            }

            Genre genre = genreService.findByName();
            if (genre == null) {
                throw  new DataInputException();
            }

            String title = consoleService.readString("read.book.title");
            Book book = new Book(0, author, genre, title);
            book = bookDao.update(book, id);
            return book;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.book.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.book.input", "");
        }
        return null;
    }

    @Override
    public void delete() {
        try {
            long id = consoleService.readLong("read.book.id");
            bookDao.deleteById(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @Override
    public Book findById() {
        try {
            long id = consoleService.readLong("read.book.id");
            Book book = bookDao.getById(id);
            return book;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    };

    @Override
    public Book findByTitle() {
        try {
            String title = consoleService.readString("read.book.title");
            Book book = bookDao.getByTitle(title);
            return book;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.book.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    };

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    };

    @Override
    public void print(Book book) {
        if (book != null) {
            consoleService.writeString("table.book", book.toString());
        }
    };

}