package ru.anovikov.learning.otusbooklib.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Repository JPA for books")
@DataJpaTest
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
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
    private AuthorRepositoryJpa authorRepository;

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Autowired
    private TestEntityManager em;

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        sessionFactory.getStatistics().clear();
    }

    @Test
    void shouldSaveAndLoadCorrectBook() {
        Author author = authorRepository.findById(FIELD_INS_AUTHORID);
        Genre genre = genreRepository.findById(FIELD_INS_GENREID);
        Book book = new Book(author, genre, FIELD_INS_TITLE);
        bookRepositoryJpa.save(book);
        assertThat(bookRepositoryJpa.findById(book.getId()))
                .hasFieldOrPropertyWithValue("title", FIELD_INS_TITLE);
    }

    @Test
    void shouldUpdateBook() {
        Author author = authorRepository.findById(FIELD_UPD_AUTHORID);
        Genre genre = genreRepository.findById(FIELD_UPD_GENREID);
        Book book = new Book(FIELD_UPD_ID, author, genre, FIELD_UPD_TITLE);
        bookRepositoryJpa.save(book);
        assertThat(bookRepositoryJpa.findById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("title", FIELD_UPD_TITLE);
    }

    @Test
    void shouldDeleteBook() {
        bookRepositoryJpa.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            bookRepositoryJpa.findById(FIELD_DEL_ID);});
    }
}