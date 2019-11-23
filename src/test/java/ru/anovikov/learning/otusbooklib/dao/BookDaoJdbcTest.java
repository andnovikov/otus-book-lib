package ru.anovikov.learning.otusbooklib.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookDaoJdbcTest {

    private static final long FIELD_INS_ID = 100000;
    private static final long FIELD_INS_GENREID = 1;
    private static final long FIELD_INS_AUTHORID = 1;
    private static final String FIELD_INS_TITLE = "book1";

    private static final long FIELD_UPD_ID = 1;
    private static final long FIELD_UPD_GENREID = 1;
    private static final long FIELD_UPD_AUTHORID = 1;
    private static final String FIELD_UPD_TITLE = "book2";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Test
    void shouldSaveAndLoadCorrectBook() {
        Book book = new Book(FIELD_INS_ID,
                             new Author(FIELD_INS_AUTHORID, "",""),
                             new Genre(FIELD_INS_GENREID, ""), FIELD_INS_TITLE);
        bookDaoJdbc.insert(book);
        assertThat(bookDaoJdbc.getById(FIELD_INS_ID))
                .hasFieldOrPropertyWithValue("title", FIELD_INS_TITLE);
    }

    @Test
    void shouldUpdateBook() {
        Author author = authorService.getById(FIELD_UPD_AUTHORID);
        Genre genre = genreService.getGenre(FIELD_UPD_GENREID);
        Book book = new Book(FIELD_UPD_ID, author, genre, FIELD_UPD_TITLE);
        bookDaoJdbc.update(book, FIELD_UPD_ID);
        assertThat(bookDaoJdbc.getById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("title", FIELD_UPD_TITLE);
    }

    @Test
    void shouldDeleteBook() {
        bookDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(bookDaoJdbc.getById(FIELD_DEL_ID)).isNull();
    }
}