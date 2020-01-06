package ru.anovikov.learning.otusbooklib.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.repository.BookRepository;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    public static final String FIELD_AUTHOR_ID = "";
    public static final String FIELD_AUTHOR_FIRSTNAME = "testAuthotFirstname";
    public static final String FIELD_AUTHOR_LASTNAME = "testAuthorLastname";
    public static final String FIELD_GENRE_ID = "";
    public static final String FIELD_GENRE_NAME = "testGenre";

    public static final String FIELD_READ_TITLE = "read.book.title";
    public static final String FIELD_READ_ID = "read.book.id";
    public static final String FIELD_BOOK_ID = "123";
    public static final String FIELD_BOOK_DUP_ID = "456";
    public static final String FIELD_BOOK_TITLE = "testBookTitle";

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    AuthorService authorService;

    @Mock
    GenreService genreService;

    @Mock
    BookRepository bookRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnCorrectBook() {
        Author author = new Author(FIELD_AUTHOR_ID, FIELD_AUTHOR_FIRSTNAME, FIELD_AUTHOR_LASTNAME);
        when(authorService.findByName(anyString(), anyString())).thenReturn(author);

        Genre genre = new Genre(FIELD_GENRE_ID, FIELD_GENRE_NAME);
        when(genreService.findByName(anyString())).thenReturn(genre);

        Book book = new Book(author, genre, FIELD_BOOK_TITLE);

        when(bookRepository.save(any())).thenReturn(book);

        assertEquals(bookService.insert(author, genre, FIELD_BOOK_TITLE), book);
    }

    @Test
    void shouldReturnUpdatedBook() {
        Author author = new Author(FIELD_AUTHOR_ID, FIELD_AUTHOR_FIRSTNAME, FIELD_AUTHOR_LASTNAME);
        Genre genre = new Genre(FIELD_GENRE_ID, FIELD_GENRE_NAME);
        Book book = new Book(author, genre, FIELD_BOOK_TITLE);

        when(authorService.findByName(anyString(), anyString())).thenReturn(author);
        when(genreService.findByName(anyString())).thenReturn(genre);
        when(bookRepository.existsById(anyString())).thenReturn(true);
        when(bookRepository.save(any())).thenReturn(book);

        assertEquals(bookService.update(FIELD_BOOK_ID, author, genre, FIELD_BOOK_TITLE), book);
    }

    @Test
    void shouldCheckDuplicateInsertBook() {
        Author author = new Author(FIELD_AUTHOR_ID, FIELD_AUTHOR_FIRSTNAME, FIELD_AUTHOR_LASTNAME);
        Genre genre = new Genre(FIELD_GENRE_ID, FIELD_GENRE_NAME);
        Book book = new Book(author, genre, FIELD_BOOK_TITLE);

        when(bookRepository.findByAuthorAndGenreAndTitle(any(), any(), anyString())).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);

        assertThrows(DuplicateValueException.class, () -> {
            bookService.insert(author, genre, FIELD_BOOK_TITLE);
        });
    }

    @Test
    void shouldCheckDuplicateUpdateBook() {
        Author author = new Author(FIELD_AUTHOR_ID, FIELD_AUTHOR_FIRSTNAME, FIELD_AUTHOR_LASTNAME);
        Genre genre = new Genre(FIELD_GENRE_ID, FIELD_GENRE_NAME);
        Book book = new Book(FIELD_BOOK_ID, author, genre, FIELD_BOOK_TITLE);

        when(bookRepository.findByAuthorAndGenreAndTitle(any(), any(), anyString())).thenReturn(Optional.of(book));
        when(bookRepository.existsById(anyString())).thenReturn(true);
        when(bookRepository.save(any())).thenReturn(book);

        assertThrows(DuplicateValueException.class, () -> {
            bookService.update(FIELD_BOOK_DUP_ID, author, genre, FIELD_BOOK_TITLE);
        });
    }
}