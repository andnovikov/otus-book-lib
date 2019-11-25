package ru.anovikov.learning.otusbooklib.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.dao.BookDao;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    public static final long FIELD_AUTHOR_ID = 0;
    public static final String FIELD_AUTHOR_FIRSTNAME = "testAuthotFirstname";
    public static final String FIELD_AUTHOR_LASTNAME = "testAuthorLastname";
    public static final long FIELD_GENRE_ID = 0;
    public static final String FIELD_GENRE_NAME = "testGenre";

    public static final String FIELD_READ_TITLE = "read.book.title";
    public static final String FIELD_READ_ID = "read.book.id";
    public static final long FIELD_BOOK_ID = 0;
    public static final String FIELD_BOOK_TITLE = "testBookTitle";


    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    AuthorService authorService;

    @Mock
    GenreService genreService;

    @Mock
    ConsoleService consoleService;

    @Mock
    BookDao bookDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnCorrectBook() {
        Author author = new Author(FIELD_AUTHOR_ID, FIELD_AUTHOR_FIRSTNAME, FIELD_AUTHOR_LASTNAME);
        when(authorService.findByName()).thenReturn(author);

        Genre genre = new Genre(FIELD_GENRE_ID, FIELD_GENRE_NAME);
        when(genreService.findByName()).thenReturn(genre);

        when(consoleService.readString(FIELD_READ_TITLE)).thenReturn(FIELD_BOOK_TITLE);

        Book book = new Book(FIELD_BOOK_ID, author, genre, FIELD_BOOK_TITLE);

        when(bookDao.insert(any())).thenReturn(book);

        assertEquals(bookService.insert(), book);
    }

    @Test
    void shouldReturnUpdatedBook() {
        Author author = new Author(FIELD_AUTHOR_ID, FIELD_AUTHOR_FIRSTNAME, FIELD_AUTHOR_LASTNAME);
        when(authorService.findByName()).thenReturn(author);

        Genre genre = new Genre(FIELD_GENRE_ID, FIELD_GENRE_NAME);
        when(genreService.findByName()).thenReturn(genre);

        when(consoleService.readLong(FIELD_READ_ID)).thenReturn(FIELD_BOOK_ID);
        when(consoleService.readString(FIELD_READ_TITLE)).thenReturn(FIELD_BOOK_TITLE);

        Book book = new Book(FIELD_BOOK_ID, author, genre, FIELD_BOOK_TITLE);

        when(bookDao.update(any())).thenReturn(book);

        assertEquals(bookService.update(), book);
    }

}