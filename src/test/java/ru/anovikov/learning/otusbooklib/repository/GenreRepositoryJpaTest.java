package ru.anovikov.learning.otusbooklib.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Repository JPA for authors")
@DataJpaTest
@Import({GenreRepositoryJpa.class})
class GenreRepositoryJpaTest {

    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_GENRENAME = "genre2";

    private static final long FIELD_DEL_ID = 3;

    public static final String FIELD_INSDUP_GENRENAME = "genre3";
    public static final String FIELD_UPDDUP_GENRENAME = "genre5";

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

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
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(FIELD_INS_GENRENAME);
        genreRepositoryJpa.save(genre);
        assertThat(genreRepositoryJpa.getById(genre.getId()))
                .hasFieldOrPropertyWithValue("genreName", FIELD_INS_GENRENAME);
    }

    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre(FIELD_UPD_ID, FIELD_UPD_GENRENAME);
        genreRepositoryJpa.save(genre);
        assertThat(genreRepositoryJpa.getById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("genreName", FIELD_UPD_GENRENAME);
    }

    @Test
    void shouldDeleteGenre() {
        genreRepositoryJpa.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            genreRepositoryJpa.getById(FIELD_DEL_ID);});
    }

    @Test
    void shouldCheckDuplicateInsertGenre() {
        Genre genre = new Genre(FIELD_INSDUP_GENRENAME);
        genre = genreRepositoryJpa.save(genre);
        assertThrows(DuplicateValueException.class, () -> {
            genreRepositoryJpa.save(new Genre(FIELD_INSDUP_GENRENAME));
        });
    }

    @Test
    void shouldCheckDuplicateUpdateGenre() {
        Genre genre = new Genre(FIELD_UPDDUP_GENRENAME);
        genre = genreRepositoryJpa.save(genre);
        assertThrows(DuplicateValueException.class, () -> {
            genreRepositoryJpa.save(new Genre(FIELD_UPD_ID, FIELD_UPDDUP_GENRENAME));
        });
    }
}