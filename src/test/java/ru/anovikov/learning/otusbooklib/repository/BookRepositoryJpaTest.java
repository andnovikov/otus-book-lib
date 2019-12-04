package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookRepositoryJpaTest {

    private static final long FIELD_INS_GENREID = 1;
    private static final long FIELD_INS_AUTHORID = 1;
    private static final String FIELD_INS_TITLE = "book1";

    private static final long FIELD_UPD_ID = 1;
    private static final long FIELD_UPD_GENREID = 1;
    private static final long FIELD_UPD_AUTHORID = 1;
    private static final String FIELD_UPD_TITLE = "book2";

    private static final long FIELD_DEL_ID = 2;

    private static final long FIELD_INSDUP_AUTHORID = 1;
    private static final long FIELD_INSDUP_GENREID = 1;
    private static final String FIELD_INSDUP_TITLE = "book3";

    private static final long FIELD_UPDDUP_AUTHORID = 1;
    private static final long FIELD_UPDDUP_GENREID = 1;
    private static final String FIELD_UPDDUP_TITLE = "book4";

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private TestEntityManager em;

    /*
    @Test
    void shouldSaveAndLoadCorrectBook() {
        Book book = new Book(new Author(FIELD_INS_AUTHORID, "",""),
                             new Genre(FIELD_INS_GENREID, ""), FIELD_INS_TITLE);
        bookRepositoryJpa.insert(book);
        assertThat(bookRepositoryJpa.findById(book.getId()))
                .hasFieldOrPropertyWithValue("title", FIELD_INS_TITLE);
    }

    @Test
    void shouldUpdateBook() {
        Author author = authorService.findById(FIELD_UPD_AUTHORID);
        Genre genre = genreService.findById(FIELD_UPD_GENREID);
        Book book = new Book(FIELD_UPD_ID, author, genre, FIELD_UPD_TITLE);
        bookRepositoryJpa.update(book);
        assertThat(bookRepositoryJpa.findById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("title", FIELD_UPD_TITLE);
    }

    @Test
    void shouldDeleteBook() {
        bookRepositoryJpa.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            bookRepositoryJpa.findById(FIELD_DEL_ID);});
    }

    @Test
    void shouldCheckDuplicateInsertBook() {
        Book book = new Book(0,
                new Author(FIELD_INSDUP_AUTHORID, "",""),
                new Genre(FIELD_INSDUP_GENREID, ""), FIELD_INSDUP_TITLE);
        bookRepositoryJpa.insert(book);
        assertThrows(DuplicateValueException.class, () -> {
            bookRepositoryJpa.insert(new Book(0,
                    new Author(FIELD_INSDUP_AUTHORID, "",""),
                    new Genre(FIELD_INSDUP_GENREID, ""), FIELD_INSDUP_TITLE));
        });
    }

    @Test
    void shouldCheckDuplicateUpdateBook() {
        Book book = new Book(0,
                new Author(FIELD_UPDDUP_AUTHORID, "",""),
                new Genre(FIELD_UPDDUP_GENREID, ""), FIELD_UPDDUP_TITLE);
        bookRepositoryJpa.insert(book);
        assertThrows(DuplicateValueException.class, () -> {
            bookRepositoryJpa.update(new Book(FIELD_UPD_ID,
                    new Author(FIELD_UPDDUP_AUTHORID, "",""),
                    new Genre(FIELD_UPDDUP_GENREID, ""), FIELD_UPDDUP_TITLE));
        });
    }
    */
}