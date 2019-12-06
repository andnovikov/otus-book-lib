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

@DisplayName("Repository for genre")
@DataJpaTest
@Import({GenreRepository.class})
class GenreRepositoryTest {

    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_GENRENAME = "genre2";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private GenreRepository genreRepository;

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
    /*
    @Test
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(FIELD_INS_GENRENAME);
        genreRepositoryJpa.save(genre);
        assertThat(genreRepositoryJpa.findById(genre.getId()))
                .hasFieldOrPropertyWithValue("genreName", FIELD_INS_GENRENAME);
    }

    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre(FIELD_UPD_ID, FIELD_UPD_GENRENAME);
        genreRepositoryJpa.save(genre);
        assertThat(genreRepositoryJpa.findById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("genreName", FIELD_UPD_GENRENAME);
    }

    @Test
    void shouldDeleteGenre() {
        genreRepositoryJpa.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            genreRepositoryJpa.findById(FIELD_DEL_ID);});
    }
    */
}